package de.unikoblenz.ptt.lord.ass03;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.core.article.command.ArticleCommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.article.dao.ArticleViewDao;
import de.unikoblenz.ptt.lord.ass03.core.article.entity.ArticleRepository;
import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleEventHandler;
import de.unikoblenz.ptt.lord.ass03.core.articleuser.dao.ArticleUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.articleuser.event.ArticleUserEventHandler;
import de.unikoblenz.ptt.lord.ass03.core.costshare.command.CostShareCommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.costshare.dao.CostShareViewDao;
import de.unikoblenz.ptt.lord.ass03.core.costshare.entity.CostShareRepository;
import de.unikoblenz.ptt.lord.ass03.core.costshare.event.CostShareEventHandler;
import de.unikoblenz.ptt.lord.ass03.core.costshareuser.dao.CostShareUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.costshareuser.event.CostShareUserEventHandler;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventBus;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventStore;
import de.unikoblenz.ptt.lord.ass03.core.exceptionmapper.RuntimeExceptionMapper;
import de.unikoblenz.ptt.lord.ass03.core.user.command.UserCommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.user.dao.UserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.user.entity.UserRepository;
import de.unikoblenz.ptt.lord.ass03.core.user.event.UserEventHandler;
import de.unikoblenz.ptt.lord.ass03.resources.ArticleResource;
import de.unikoblenz.ptt.lord.ass03.resources.CostShareResource;
import de.unikoblenz.ptt.lord.ass03.resources.UserResource;

public class CostShareApplication extends Application<CostShareConfiguration> {

	public static void main(String[] args) throws Exception {
		CostShareApplication costShareApplication = new CostShareApplication();
		costShareApplication.run(args);
	}

	@Override
	public void initialize(Bootstrap<CostShareConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<CostShareConfiguration>() {
			public DataSourceFactory getDataSourceFactory(CostShareConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
	}

	@Override
	public void run(CostShareConfiguration configuration, Environment environment) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

		final CommandBus commandBus = new CommandBus();

		final EventBus eventBus = new EventBus();
		final EventDao eventDao = jdbi.onDemand(EventDao.class);
		final EventStore eventStore = new EventStore(eventDao);

		final ArticleUserViewDao articleUserViewDao = jdbi.onDemand(ArticleUserViewDao.class);
		final CostShareUserViewDao costShareUserViewDao = jdbi.onDemand(CostShareUserViewDao.class);

		final ArticleViewDao articleViewDao = jdbi.onDemand(ArticleViewDao.class);
		final ArticleRepository articleRepository = new ArticleRepository(eventStore);
		final ArticleCommandHandler articleCommandHandler = new ArticleCommandHandler(eventBus, articleRepository);
		final ArticleEventHandler articleEventHandler = new ArticleEventHandler(articleViewDao);
		final ArticleUserEventHandler articleUserEventHandler = new ArticleUserEventHandler(articleUserViewDao);
		final ArticleResource articleResource = new ArticleResource(commandBus);
		commandBus.subscribe(articleCommandHandler);
		eventBus.subscribe(articleEventHandler);
		eventBus.subscribe(articleUserEventHandler);
		environment.jersey().register(articleResource);

		final CostShareViewDao costShareViewDao = jdbi.onDemand(CostShareViewDao.class);
		final CostShareRepository costShareRepository = new CostShareRepository(eventStore);
		final CostShareCommandHandler costShareCommandHandler = new CostShareCommandHandler(eventBus, costShareRepository);
		final CostShareEventHandler costShareEventHandler = new CostShareEventHandler(costShareViewDao);
		final CostShareUserEventHandler costShareUserEventHandler = new CostShareUserEventHandler(costShareUserViewDao);
		final CostShareResource costShareResource = new CostShareResource(commandBus, costShareViewDao, costShareUserViewDao, articleViewDao, articleUserViewDao);
		commandBus.subscribe(costShareCommandHandler);
		eventBus.subscribe(costShareEventHandler);
		eventBus.subscribe(costShareUserEventHandler);
		environment.jersey().register(costShareResource);

		final UserViewDao userViewDao = jdbi.onDemand(UserViewDao.class);
		final UserRepository userRepository = new UserRepository(eventStore);
		final UserCommandHandler userViewCommandHandler = new UserCommandHandler(eventBus, userRepository);
		final UserEventHandler userEventHandler = new UserEventHandler(userViewDao);
		final UserResource userResource = new UserResource(commandBus, userViewDao, costShareUserViewDao);
		commandBus.subscribe(userViewCommandHandler);
		eventBus.subscribe(userEventHandler);
		environment.jersey().register(userResource);

		final RuntimeExceptionMapper runtimeExceptionMapper = new RuntimeExceptionMapper();

		environment.jersey().register(runtimeExceptionMapper);

		environment.jersey().setUrlPattern("/api/*");
	}

}
