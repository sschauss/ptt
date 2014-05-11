package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass01.util.SoundcloudDate;

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
	
	@JsonProperty("downloadable")
	private boolean downloadable;
	
	@JsonProperty("created_at")
	private String createdAt;
	
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
	
	public double getInterestingness() {
		double favs = favoritingsCount;
		double plays = playbackCount;
		double downloads = downloadCount;
		double comments = commentCount;
		
		double baseFactor = Math.log(plays);
		double favoritingsBonus = (favs / plays) * 100.0;
		double commentBonus = (comments / plays) * 100.0;
		
		double downloadMalus = 0.0;
		if (downloadable){
			double downloadPercentage = (downloads / plays) * 100.0;
			if (downloadPercentage < 7.5) downloadMalus = downloadPercentage / 15.0;
		}
		
		SoundcloudDate onlineSince = new SoundcloudDate(createdAt);
		int daysOnline = onlineSince.getTimeDifferenceFromTodayInDays();
		double timeBonus = Math.log((plays / daysOnline) + 1);
		
		double interestingness = Math.log(baseFactor + favoritingsBonus + commentBonus - downloadMalus + timeBonus);
		
		return Math.round(interestingness * 100.0) / 100.0;
	}

}
