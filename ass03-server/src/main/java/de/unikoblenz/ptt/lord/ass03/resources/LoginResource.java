package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.unikoblenz.ptt.lord.ass03.api.UserCredentials;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.User;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;
import de.unikoblenz.ptt.lord.ass03.security.AuthTokenCache;

@Path("/login")
public class LoginResource {

	private UserDao userDao;
	private AuthTokenCache authTokenCache;

	public LoginResource(UserDao userDao, AuthTokenCache authTokenCache) {
		this.userDao = userDao;
		this.authTokenCache = authTokenCache;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserCredentials userCredentials) {
		User user = userDao.get(userCredentials.getEmailAddress());
		if (!userCredentials.getPassword().equals(user.getPassword())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		UUID authToken = UUID.randomUUID();
		authTokenCache.addAuthToken(user.getId(), authToken);
		return Response.status(Status.CREATED).header("x-auth-token", authToken).build();
	}

}