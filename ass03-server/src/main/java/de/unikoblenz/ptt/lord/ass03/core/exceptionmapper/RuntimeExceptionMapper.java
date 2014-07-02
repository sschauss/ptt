package de.unikoblenz.ptt.lord.ass03.core.exceptionmapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	public Response toResponse(RuntimeException runtimeException) {

		if (runtimeException instanceof WebApplicationException) {
			WebApplicationException webApplicationException = (WebApplicationException) runtimeException;
			return webApplicationException.getResponse();
		}
		
		runtimeException.printStackTrace();
		
		return Response.serverError().build();
	}
}
