package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import java.util.ArrayList;
import java.util.List;

import de.unikoblenz.ptt.lord.ass01.api.TrackView;
import de.unikoblenz.ptt.lord.ass01.util.SoundcloudDate;

public final class TrackViewFactory {

	private TrackViewFactory() {

	}

	public static List<TrackView> build(final List<Track> tracks) {
		final List<TrackView> trackViews = new ArrayList<>();
		for (final Track track : tracks) {
			final TrackView trackView = build(track);
			trackViews.add(trackView);
		}
		return trackViews;
	}

	public static TrackView build(final Track track) {
		final String label = track.getTitle();
		final int id = track.getId();
		final int commentCount = track.getCommentCount();
		final int downloadCount = track.getDownloadCount();
		final int playbackCount = track.getPlaybackCount();
		final int favoritingsCount = track.getFavoritingsCount();
		final double interestingness = calculateInterestingness(track);
		return new TrackView(label, id, commentCount, downloadCount, playbackCount, favoritingsCount, interestingness);
	}

	private static double calculateInterestingness(final Track track) {

		final double favs = track.getFavoritingsCount();
		final double plays = track.getPlaybackCount();
		final double downloads = track.getDownloadCount();
		final double comments = track.getCommentCount();
		final boolean downloadable = track.isDownloadable();
		final SoundcloudDate createAt = track.getCreatedAt();
		final int daysOnline = createAt.getTimeDifferenceFromTodayInDays();

		final double playsBaseFactor = Math.log(plays + 1);
		final double favoritingsBonus = (favs / (plays + 1)) * 100.0;
		final double commentBonus = (comments / (plays + 1)) * 100.0;

		double downloadMalus = 0.0;
		if (downloadable) {
			final double downloadPercentage = (downloads / (plays + 1)) * 100.0;
			if (downloadPercentage < 7.5) {
				downloadMalus = downloadPercentage / 15.0;
			}
		}

		final double timeBonus = Math.log((plays / (daysOnline + 1)) + 1);

		final double interestingness = Math.log(playsBaseFactor + favoritingsBonus + commentBonus - downloadMalus + timeBonus);

		return Math.round(interestingness * 100.0) / 100.0;

	}
}
