package de.unikoblenz.ptt.lord.ass01.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass01.api.Track;
import de.unikoblenz.ptt.lord.ass01.client.TrackClient;

@Path("/tracks")
public class TrackResource {

	private final TrackClient trackClient;

	public TrackResource(final TrackClient trackClient) {
		this.trackClient = trackClient;
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Track> getTracks(@QueryParam("q") final String q) {
		return trackClient.getTracks(q);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrack(@PathParam("id") final String id) {
		return trackClient.getTrack(id);
	}

}
