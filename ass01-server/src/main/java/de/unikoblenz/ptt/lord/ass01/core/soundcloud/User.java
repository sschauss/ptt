package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Entity {

	private String username;

	@JsonProperty("track_count")
	private int trackCount;

	@JsonProperty("playlist_count")
	private int playlistCount;

	@JsonProperty("followers_count")
	private int followersCount;

	@JsonProperty("followings_count")
	private int followingsCount;

	@JsonProperty("public_favorites_count")
	private int publicFavoritesCount;

	public User() {

	}

	public String getUsername() {
		return username;
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
