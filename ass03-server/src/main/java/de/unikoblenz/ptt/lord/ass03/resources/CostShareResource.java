package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass03.jdbi.costshare.CostShare;
import de.unikoblenz.ptt.lord.ass03.jdbi.costshare.CostShareDao;

@Path("/flatshares")
public class CostShareResource {

	private final CostShareDao costShareDao;

	public CostShareResource(CostShareDao costShareDao) {
		this.costShareDao = costShareDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(CostShare flatShare) {
		costShareDao.create(flatShare);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CostShare get(@PathParam("id") UUID id) {
		return costShareDao.get(id);
	}

}
