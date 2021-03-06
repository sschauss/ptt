package de.unikoblenz.ptt.lord.ass01.api;

public class UserView extends EntityView {

	private int trackCount;

	private int playlistCount;

	private int followersCount;

	private int followingsCount;

	private int publicFavoritesCount;

	private double interestingness;

	public UserView(final int id, final String label, final int trackCount, final int playlistCount, final int followersCount, final int followingsCount, final int publicFavoritesCount, final double interestingness) {
		super(id, label);
		this.trackCount = trackCount;
		this.playlistCount = playlistCount;
		this.followersCount = followersCount;
		this.followingsCount = followingsCount;
		this.publicFavoritesCount = publicFavoritesCount;
		this.interestingness = interestingness;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public int getPlaylistCount() {
		return playlistCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFollowingsCount() {
		return followingsCount;
	}

	public int getPublicFavoritesCount() {
		return publicFavoritesCount;
	}

	public double getInterestingness() {
		return interestingness;
	}
}
