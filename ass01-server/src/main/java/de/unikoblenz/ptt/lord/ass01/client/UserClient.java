package de.unikoblenz.ptt.lord.ass01.client;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import de.unikoblenz.ptt.lord.ass01.core.soundcloud.User;

public class UserClient extends SoundCloudClient {

	private static final String BASE_PATH = "users";

	private final WebResource baseResource;

	public UserClient(final Client client, final String clientId) {
		super(client, clientId);
		this.baseResource = hostResource.path(BASE_PATH);
	}

	public User getUser(final String id) {
		return baseResource.path(id + ".json").get(User.class);
	}

	public List<User> getUsers(final String q) {
		return hostResource.path("users.json").queryParam("q", q).queryParam("limit", "10").get(new GenericType<List<User>>() {
		});
	}

}
