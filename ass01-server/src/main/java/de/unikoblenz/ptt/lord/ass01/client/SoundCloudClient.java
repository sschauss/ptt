package de.unikoblenz.ptt.lord.ass01.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class SoundCloudClient {

	private static final String HOST = "http://api.soundcloud.com";

	protected final WebResource hostResource;

	public SoundCloudClient(final Client client, final String clientId) {
		this.hostResource = client.resource(HOST).queryParam("client_id", clientId);
	}

}
