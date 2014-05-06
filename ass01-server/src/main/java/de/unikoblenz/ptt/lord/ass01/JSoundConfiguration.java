package de.unikoblenz.ptt.lord.ass01;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

public class JSoundConfiguration extends Configuration {

	private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

	private String clientId;

	public JerseyClientConfiguration getJerseyClientConfiguration() {
		return jerseyClientConfiguration;
	}

	public String getClientId() {
		return clientId;
	}

}
