package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import java.util.ArrayList;
import java.util.List;

import de.unikoblenz.ptt.lord.ass01.api.UserView;

public final class UserViewFactory {

	private UserViewFactory() {

	}

	public static List<UserView> build(final List<User> users) {
		final List<UserView> userViews = new ArrayList<>();
		for (final User user : users) {
			UserView userView = build(user);
			userViews.add(userView);
		}
		return userViews;
	}

	public static UserView build(final User user) {
		final int id = user.getId();
		final String label = user.getUsername();
		final int trackCount = user.getTrackCount();
		final int playlistCount = user.getPlaylistCount();
		final int followersCount = user.getFollowersCount();
		final int followingsCount = user.getFollowingsCount();
		final int publicFavoritesCount = user.getPublicFavoritesCount();
		final double interestingness = calculateInterestingness(user);
		return new UserView(label, id, trackCount, playlistCount, followersCount, followingsCount, publicFavoritesCount, interestingness);
	}

	private static double calculateInterestingness(final User user) {
		final double tracks = user.getTrackCount();
		final double playlists = user.getPlaylistCount();
		final double followers = user.getFollowersCount();

		final double trackBaseFactor = Math.log(tracks + 1);
		final double followersBaseFactor = Math.log(followers + 1) * 2;

		final double playlistsBonus = Math.log(playlists + 1);

		final double interestingness = Math.log(trackBaseFactor + followersBaseFactor + playlistsBonus + 1);

		return Math.round(interestingness * 100.0) / 100.0;
	}

}
