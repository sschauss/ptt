package de.unikoblenz.ptt.lord.ass01.client;

import java.util.ArrayList;
import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import de.unikoblenz.ptt.lord.ass01.core.Track;

public class TrackClient extends SoundCloudClient {

	private static final String BASE_PATH = "tracks";

	private final WebResource baseResource;

	public TrackClient(final Client client, final String clientId) {
		super(client, clientId);
		this.baseResource = hostResource.path(BASE_PATH);
	}

	public Track getTrack(final String id) {
		System.out.println(baseResource.path(id + ".json").getURI().toString());
		return baseResource.path(id + ".json").get(Track.class);
	}

	public List<Track> getTracks(List<String> ids) {
		List<Track> tracks = new ArrayList<>();
		for (final String id : ids) {
			final Track track = getTrack(id);
			tracks.add(track);
		}
		return tracks;
	}

}
