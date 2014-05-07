package de.unikoblenz.ptt.lord.ass01.api;


public class TrackGraphData {

	private int commentsCount;

	private int downloadCount;

	private int playbackCount;

	private int favoritingsCount;

	public TrackGraphData() {

	}

	public TrackGraphData(int commentsCount, int downloadCount, int playbackCount, int favoritingsCount) {
		this.commentsCount = commentsCount;
		this.downloadCount = downloadCount;
		this.playbackCount = playbackCount;
		this.favoritingsCount = favoritingsCount;
	}

	public int getCommentsCount() {
		return commentsCount;
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
