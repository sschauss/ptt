package de.unikoblenz.ptt.lord.ass01;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.Duration;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.jersey.api.client.Client;

import de.unikoblenz.ptt.lord.ass01.client.TrackClient;
import de.unikoblenz.ptt.lord.ass01.core.jackson.DurationDeserializer;
import de.unikoblenz.ptt.lord.ass01.core.jackson.DurationSerializer;
import de.unikoblenz.ptt.lord.ass01.resources.TrackResource;

public class JSoundApplication extends Application<JSoundConfiguration> {

	private static final String NAME = "JSound";

	private static final String SC_CLIENT_NAME = "SoundCloud Client";

	public static void main(final String[] arguments) throws Exception {
		new JSoundApplication().run(arguments);
	}

	@Override
	public void initialize(final Bootstrap<JSoundConfiguration> bootstrap) {
		SimpleModule durationModule = new SimpleModule();
		durationModule.addDeserializer(Duration.class, new DurationDeserializer());
		durationModule.addSerializer(Duration.class, new DurationSerializer());
		bootstrap.getObjectMapper().registerModule(durationModule);
		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
	}

	@Override
	public void run(final JSoundConfiguration config, final Environment environment) throws Exception {
		JerseyClientBuilder jerseyClientBuilder = new JerseyClientBuilder(environment);
		jerseyClientBuilder.using(config.getJerseyClientConfiguration());
		final Client client = jerseyClientBuilder.build(SC_CLIENT_NAME);
		final TrackClient trackClient = new TrackClient(client, config.getClientId());
		final TrackResource trackResource = new TrackResource(trackClient);
		registerResource(environment, trackResource);
		environment.jersey().setUrlPattern("/api/*");
	}

	@Override
	public String getName() {
		return NAME;
	}

	private void registerResource(final Environment environment, final Object resource) {
		environment.jersey().register(resource);
	}

}
