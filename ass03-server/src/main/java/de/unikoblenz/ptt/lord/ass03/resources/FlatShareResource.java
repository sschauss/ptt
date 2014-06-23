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

import de.unikoblenz.ptt.lord.ass03.jdbi.flatshare.FlatShare;
import de.unikoblenz.ptt.lord.ass03.jdbi.flatshare.FlatShareDao;

@Path("/flatshares")
public class FlatShareResource {

	private final FlatShareDao flatShareDao;

	public FlatShareResource(DBI jdbi) {
		flatShareDao = jdbi.onDemand(FlatShareDao.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FlatShare> getAll() {
		return flatShareDao.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(FlatShare flatShare) {
		flatShareDao.create(flatShare);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FlatShare get(@PathParam("id") UUID id) {
		return flatShareDao.get(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(FlatShare flatShare) {
		flatShareDao.update(flatShare);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") UUID id) {
		flatShareDao.delete(id);
	}
	
}
