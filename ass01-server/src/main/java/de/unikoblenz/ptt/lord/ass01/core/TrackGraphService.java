package de.unikoblenz.ptt.lord.ass01.core;

import de.unikoblenz.ptt.lord.ass01.api.TrackGraphData;
import de.unikoblenz.ptt.lord.ass01.client.TrackClient;

public class TrackGraphService {

	private final TrackClient trackClient;

	public TrackGraphService(final TrackClient trackClient) {
		this.trackClient = trackClient;
	}

	public TrackGraphData createTrackGraphData(final String id) {
		final Track track = trackClient.getTrack(id);
		final int commentsCount = track.getCommentCount();
		final int downloadCount = track.getDownloadCount();
		final int playbackCount = track.getPlaybackCount();
		final int favoritingsCount = track.getFavoritingsCount();
		return new TrackGraphData(commentsCount, downloadCount, playbackCount, favoritingsCount);
	}

}
