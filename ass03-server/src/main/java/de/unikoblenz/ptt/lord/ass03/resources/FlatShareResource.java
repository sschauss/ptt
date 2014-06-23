package de.unikoblenz.ptt.lord.ass03.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.core.flatshare.FlatShare;
import de.unikoblenz.ptt.lord.ass03.core.flatshare.FlatShareDao;

@Path("/flatshares")
public class FlatShareResource {

	private final FlatShareDao flatShareDao;
	
	public FlatShareResource(DBI jdbi) {
		flatShareDao = jdbi.onDemand(FlatShareDao.class); 
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FlatShare> getFlatShares() {
		return flatShareDao.getFlatShares();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createFlatShare(FlatShare flatShare) {

	}

}
