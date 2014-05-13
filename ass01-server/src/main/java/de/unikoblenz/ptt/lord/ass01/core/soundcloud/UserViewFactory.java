package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import java.util.ArrayList;
import java.util.List;

import de.unikoblenz.ptt.lord.ass01.api.UserView;

public final class UserViewFactory {

	private UserViewFactory() {

	}

	public static List<UserView> build(final List<User> users) {
		List<UserView> userViews = new ArrayList<>();
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
		// TODO Auto-generated method stub
		return 0;
	}

}
