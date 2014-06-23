package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.jdbi.user.User;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;

@Path("/users")
public class UserResource {

	private final UserDao userDao;
	
	public UserResource(DBI jdbi) {
		userDao = jdbi.onDemand(UserDao.class); 
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() {
		return userDao.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(User user) {
		userDao.create(user);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("id") UUID id) {
		return userDao.get(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(User user) {
		userDao.update(user);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") UUID id) {
		userDao.delete(id);
	}
	

}
