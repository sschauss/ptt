package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.unikoblenz.ptt.lord.ass03.core.security.Auth;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.User;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;

@Path("/users")
public class UserResource {

	private final UserDao userDao;

	public UserResource(UserDao userDao) {
		this.userDao = userDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		userDao.create(user);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@Auth User user, @PathParam("id") UUID id) {
		return userDao.get(id);
	}

}
