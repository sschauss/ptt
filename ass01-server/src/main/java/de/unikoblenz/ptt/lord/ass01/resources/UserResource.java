package de.unikoblenz.ptt.lord.ass01.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass01.api.User;
import de.unikoblenz.ptt.lord.ass01.client.UserClient;

@Path("/users")
public class UserResource {

	private final UserClient userClient;

	public UserResource(UserClient userClient) {
		super();
		this.userClient = userClient;
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(@QueryParam("q") final String q) {
		return userClient.getUsers(q);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") final String id) {
		return userClient.getUser(id);
	}

}
