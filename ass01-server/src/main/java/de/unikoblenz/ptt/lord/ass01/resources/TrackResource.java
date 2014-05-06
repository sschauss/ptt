package de.unikoblenz.ptt.lord.ass01.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrack(@PathParam("id") String id) {
		return trackClient.getTrack(id);
	}

}
