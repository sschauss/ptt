package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track extends Entity {

	private int id;

	private int commentCount;

	private int downloadCount;

	private int playbackCount;

	private int favoritingsCount;

	@JsonCreator
	public Track(
			@JsonProperty("title") final String label,
			@JsonProperty("id")final int id,
			@JsonProperty("comment_count") final int commentCount,
			@JsonProperty("download_count") final int downloadCount,
			@JsonProperty("playback_count") final int playbackCount,
			@JsonProperty("favoritings_count") final int favoritingsCount) {
		super(label);
		this.id = id;
		this.commentCount = commentCount;
		this.downloadCount = downloadCount;
		this.playbackCount = playbackCount;
		this.favoritingsCount = favoritingsCount;
	}

	public int getId() {
		return id;
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

	public double getInterestingness() {
		/**
		 * double favs = favoritingsCount; double plays = playbackCount; double
		 * downloads = downloadCount; double comments = commentCount;
		 * 
		 * double baseFactor = Math.log(plays); double favoritingsBonus = (favs
		 * / plays) * 100.0; double commentBonus = (comments / plays) * 100.0;
		 * 
		 * double downloadMalus = 0.0; if (downloadable) { double
		 * downloadPercentage = (downloads / plays) * 100.0; if
		 * (downloadPercentage < 7.5) downloadMalus = downloadPercentage / 15.0;
		 * }
		 * 
		 * SoundcloudDate onlineSince = new SoundcloudDate(createdAt); int
		 * daysOnline = onlineSince.getTimeDifferenceFromTodayInDays(); double
		 * timeBonus = Math.log((plays / daysOnline) + 1);
		 * 
		 * double interestingness = Math.log(baseFactor + favoritingsBonus +
		 * commentBonus - downloadMalus + timeBonus);
		 * 
		 * return Math.round(interestingness * 100.0) / 100.0;
		 **/
		return 1;
	}

}
