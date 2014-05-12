package de.unikoblenz.ptt.lord.ass01.client;

import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import de.unikoblenz.ptt.lord.ass01.api.TrackView;
import de.unikoblenz.ptt.lord.ass01.core.soundcloud.Track;
import de.unikoblenz.ptt.lord.ass01.core.soundcloud.TrackViewFactory;

public class TrackClient extends SoundCloudClient {

	private static final String BASE_PATH = "tracks";

	private final WebResource baseResource;

	public TrackClient(final Client client, final String clientId) {
		super(client, clientId);
		this.baseResource = hostResource.path(BASE_PATH);
	}

	public TrackView getTrack(final String id) {
		final Track track = baseResource.path(id + ".json").get(Track.class);
		return TrackViewFactory.build(track);
	}

	public List<TrackView> getTracks(final String q) {
		final List<Track> tracks = hostResource.path("tracks.json").queryParam("q", q).queryParam("limit", "10").get(new GenericType<List<Track>>() {
		});
		return TrackViewFactory.build(tracks);
	}
}
