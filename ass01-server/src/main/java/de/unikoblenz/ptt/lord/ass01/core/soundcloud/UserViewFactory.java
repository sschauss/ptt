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
		double tracks = user.getTrackCount();
		double playlists = user.getPlaylistCount();
		double followers = user.getFollowersCount();
		double followings = user.getFollowingsCount();
		double favorites = user.getPublicFavoritesCount();
		
		double baseFactor1 = Math.log(tracks + 1) * 2;
		double baseFactor2 = Math.log(followers + 1);
		
		double followingsBonus = (followings / (followers + 1)) * 100.0;
		double favoritesBonus = (favorites / (followers + 1)) * 100.0;
		
		double playlistsBonus = 0;
		double playlistsFactor = (playlists / (tracks + 1)) * 100.0;
		if (playlistsFactor > 0.1) playlistsBonus = 0.3;
		if (playlistsFactor > 0.2) playlistsBonus = -0.3;
		
		double interestingness = Math.log(baseFactor1 + baseFactor2 + followingsBonus + favoritesBonus + playlistsBonus + 1);
		
		return Math.round(interestingness * 100.0) / 100.0;
	}

}
