package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass01.util.SoundcloudDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track extends Entity {

	private String title;

	@JsonProperty("comment_count")
	private int commentCount;

	@JsonProperty("download_count")
	private int downloadCount;

	@JsonProperty("playback_count")
	private int playbackCount;

	@JsonProperty("favoritings_count")
	private int favoritingsCount;

	private boolean downloadable;

	@JsonProperty("created_at")
	private SoundcloudDate createdAt;

	public Track() {

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

	public boolean isDownloadable() {
		return downloadable;
	}

	public SoundcloudDate getCreatedAt() {
		return createdAt;
	}

}
