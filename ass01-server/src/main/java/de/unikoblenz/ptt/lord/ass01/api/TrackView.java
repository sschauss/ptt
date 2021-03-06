package de.unikoblenz.ptt.lord.ass01.api;

public class TrackView extends EntityView {

	private int commentCount;

	private int downloadCount;

	private int playbackCount;

	private int favoritingsCount;

	private double interestingness;

	public TrackView(final int id, final String label, final int commentCount, final int downloadCount, final int playbackCount, final int favoritingsCount, final double interestingness) {
		super(id, label);
		this.commentCount = commentCount;
		this.downloadCount = downloadCount;
		this.playbackCount = playbackCount;
		this.favoritingsCount = favoritingsCount;
		this.interestingness = interestingness;
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
		return interestingness;
	}

}
