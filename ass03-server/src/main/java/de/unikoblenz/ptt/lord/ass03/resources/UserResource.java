package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.core.user.User;
import de.unikoblenz.ptt.lord.ass03.core.user.UserDao;

@Path("/users")
public class UserResource {

	private final UserDao userDao;
	
	public UserResource(DBI jdbi) {
		userDao = jdbi.onDemand(UserDao.class); 
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getusers() {
		return userDao.getUsers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createUser(User user) {
		userDao.createUser(user);
	}

}
