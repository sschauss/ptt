package de.unikoblenz.ptt.lord.ass01.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass01.api.TrackView;
import de.unikoblenz.ptt.lord.ass01.client.TrackClient;

@Path("/tracks")
public class TrackResource {

	private final TrackClient trackClient;

	public TrackResource(final TrackClient trackClient) {
		this.trackClient = trackClient;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TrackView> getTracks(@QueryParam("q") final String q) {
		return trackClient.getTracks(q);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrackView getTrack(@PathParam("id") final String id) {
		return trackClient.getTrack(id);
	}

}
