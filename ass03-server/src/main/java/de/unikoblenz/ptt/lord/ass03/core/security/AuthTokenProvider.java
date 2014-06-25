package de.unikoblenz.ptt.lord.ass03.core.security;

import java.lang.reflect.Type;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import de.unikoblenz.ptt.lord.ass03.jdbi.user.User;
import de.unikoblenz.ptt.lord.ass03.jdbi.user.UserDao;

@Provider
public class AuthTokenProvider implements InjectableProvider<Auth, Type> {

	private UserDao userDao;
	private AuthTokenCache authTokenCache;

	public AuthTokenProvider(UserDao userDao, AuthTokenCache authTokenCache) {
		this.userDao = userDao;
		this.authTokenCache = authTokenCache;
	}

	public ComponentScope getScope() {
		return ComponentScope.PerRequest;
	}

	public Injectable<User> getInjectable(ComponentContext componentContext, Auth auth, Type type) {
		return new AuthTokenInjectable(userDao, authTokenCache);
	}

	private static class AuthTokenInjectable extends AbstractHttpContextInjectable<User> {

		private UserDao userDao;
		private AuthTokenCache authTokenCache;

		public AuthTokenInjectable(UserDao userDao, AuthTokenCache authTokenCache) {
			this.userDao = userDao;
			this.authTokenCache = authTokenCache;
		}

		@Override
		public User getValue(HttpContext httpContext) {
			User user = null;
			try {
				UUID authToken = UUID.fromString(httpContext.getRequest().getHeaderValue("x-auth-token"));
				UUID id = authTokenCache.authorized(authToken);
				user = userDao.get(id);
			} catch (IllegalArgumentException e) {

			}
			if (user == null) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
			return user;
		}
	}

}
