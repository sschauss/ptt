package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

	private int id;

	private String title;

	@JsonProperty("comment_count")
	private int commentCount;

	@JsonProperty("download_count")
	private int downloadCount;

	@JsonProperty("playback_count")
	private int playbackCount;

	@JsonProperty("favoritings_count")
	private int favoritingsCount;

	public Track() {

	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public int getPlaybackCount() {
		return playbackCount;
	}

	public int getFavoritingsCount() {
		return favoritingsCount;
	}

}
