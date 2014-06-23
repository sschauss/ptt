package de.unikoblenz.ptt.lord.ass03;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
		final UserResource userResource = new UserResource(jdbi);

		environment.jersey().register(userResource);

		environment.jersey().setUrlPattern("/api/*");
	}

}