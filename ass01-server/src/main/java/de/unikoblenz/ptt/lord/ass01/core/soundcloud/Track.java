package de.unikoblenz.ptt.lord.ass01.core.soundcloud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass01.util.SoundCloudDate;

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
	private SoundCloudDate createdAt;

	public Track() {

	}

	public Track(final int id, final String title, final int commentCount, final int downloadCount, final int playbackCount, final int favoritingsCount, final boolean downloadable, final SoundCloudDate createdAt) {
		super(id);
		this.title = title;
		this.commentCount = commentCount;
		this.downloadCount = downloadCount;
		this.playbackCount = playbackCount;
		this.favoritingsCount = favoritingsCount;
		this.downloadable = downloadable;
		this.createdAt = createdAt;
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

	public SoundCloudDate getCreatedAt() {
		return createdAt;
	}
	
	public boolean equals(final Object object) {
		if (object instanceof Track) {
			final Track track = (Track) object;
			return getId() == track.getId() &&
				   title.equals(track.getTitle()) &&
				   commentCount == track.getCommentCount() &&
				   downloadCount == track.getDownloadCount() &&
				   playbackCount == track.getPlaybackCount() &&
				   favoritingsCount == track.getFavoritingsCount() &&
				   downloadable == track.isDownloadable() &&
				   createdAt.equals(track.createdAt);
		} else {
			return false;
		}
	}
}
