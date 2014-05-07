package de.unikoblenz.ptt.lord.ass01.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass01.api.TrackGraphData;
import de.unikoblenz.ptt.lord.ass01.core.TrackGraphService;

@Path("/tracks")
public class TrackResource {

	private final TrackGraphService trackGraphService;

	public TrackResource(final TrackGraphService trackGraphService) {
		this.trackGraphService = trackGraphService;
	}

	@GET
	@Path("/graphs/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrackGraphData createTrackGraphData(@PathParam("id") final String id) {
		return trackGraphService.createTrackGraphData(id);
	}
	
	
	

}
