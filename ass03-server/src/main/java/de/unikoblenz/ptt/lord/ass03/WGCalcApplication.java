package de.unikoblenz.ptt.lord.ass03;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.core.RuntimeExceptionMapper;
import de.unikoblenz.ptt.lord.ass03.core.security.AuthTokenCache;
import de.unikoblenz.ptt.lord.ass03.core.security.AuthTokenProvider;
import de.unikoblenz.ptt.lord.ass03.jdbi.costshare.CostShareDao;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;
import de.unikoblenz.ptt.lord.ass03.resources.CostShareResource;
import de.unikoblenz.ptt.lord.ass03.resources.TokenResource;
import de.unikoblenz.ptt.lord.ass03.resources.UserResource;

public class WGCalcApplication extends Application<WGCalcConfiguration> {

	public static void main(String[] args) throws Exception {
		WGCalcApplication wgCalcApplication = new WGCalcApplication();
		wgCalcApplication.run(args);
	}

	@Override
	public void initialize(Bootstrap<WGCalcConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<WGCalcConfiguration>() {
			public DataSourceFactory getDataSourceFactory(WGCalcConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

	}

	@Override
	public void run(WGCalcConfiguration configuration, Environment environment) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

		final UserDao userDao = jdbi.onDemand(UserDao.class);
		final CostShareDao costShareDao = jdbi.onDemand(CostShareDao.class);
		final AuthTokenCache authTokenCache = new AuthTokenCache();

		final TokenResource loginResource = new TokenResource(userDao, authTokenCache);
		final UserResource userResource = new UserResource(userDao);
		final CostShareResource costShareResource = new CostShareResource(costShareDao);

		environment.jersey().register(loginResource);
		environment.jersey().register(userResource);
		environment.jersey().register(costShareResource);

		final AuthTokenProvider authTokenProvider = new AuthTokenProvider(userDao, authTokenCache);
		final RuntimeExceptionMapper runtimeExceptionMapper = new RuntimeExceptionMapper();

		environment.jersey().register(authTokenProvider);
		environment.jersey().register(runtimeExceptionMapper);

		environment.jersey().setUrlPattern("/api/*");
	}

}
