package de.unikoblenz.ptt.lord.ass01.api;


public class TrackView extends EntityView {

	private int id;

	private int commentCount;

	private int downloadCount;

	private int playbackCount;

	private int favoritingsCount;

	private double interestingness;
	
	public TrackView(final String label, final int id, final int commentCount, final int downloadCount, final int playbackCount, final int favoritingsCount, final double interestingness) {
		super(label);
		this.id = id;
		this.commentCount = commentCount;
		this.downloadCount = downloadCount;
		this.playbackCount = playbackCount;
		this.favoritingsCount = favoritingsCount;
		this.interestingness = interestingness;
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
		return interestingness;
	}

}
