package de.unikoblenz.ptt.lord.ass01.client;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import de.unikoblenz.ptt.lord.ass01.api.UserView;
import de.unikoblenz.ptt.lord.ass01.core.soundcloud.User;
import de.unikoblenz.ptt.lord.ass01.core.soundcloud.UserViewFactory;

public class UserClient extends SoundCloudClient {

	private static final String BASE_PATH = "users";

	private final WebResource baseResource;

	public UserClient(final Client client, final String clientId) {
		super(client, clientId);
		this.baseResource = hostResource.path(BASE_PATH);
	}

	public UserView getUser(final String id) {
		User user = baseResource.path(id + ".json").get(User.class);
		return UserViewFactory.build(user);		
	}

	public List<UserView> getUsers(final String q) {
		final List<User> users = hostResource.path("users.json").queryParam("q", q).queryParam("limit", "10").get(new GenericType<List<User>>() {
		});
		return UserViewFactory.build(users);
	}

}
