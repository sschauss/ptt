package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private int id;

	private String username;

	@JsonProperty("track_count")
	private int trackCount;

	@JsonProperty("playlist_count")
	private int playlist_count;

	@JsonProperty("followers_count")
	private int followersCount;

	@JsonProperty("followings_count")
	private int followingsCount;

	@JsonProperty("public_favorites_count")
	private int publicFavoritesCount;

	public User() {

	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public int getPlaylist_count() {
		return playlist_count;
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
