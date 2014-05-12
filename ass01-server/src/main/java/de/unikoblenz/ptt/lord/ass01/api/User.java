package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Entity {

	private int id;

	private int trackCount;

	private int playlistCount;

	private int followersCount;

	private int followingsCount;

	private int publicFavoritesCount;

	public User(
			@JsonProperty("username") final String label,
			@JsonProperty("track_count") final int trackCount,
			@JsonProperty("playlist_count") final int playlistCount,
			@JsonProperty("followers_count") final int followersCount,
			@JsonProperty("followings_count") final int followingsCount,
			@JsonProperty("public_favorites_count") final int publicFavoritesCount
			) {
		super(label);
		this.trackCount = trackCount;
		this.playlistCount = playlistCount;
		this.followersCount = followersCount;
		this.followingsCount = followingsCount;
		this.publicFavoritesCount = publicFavoritesCount;
	}

	public int getId() {
		return id;
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

}
