package de.unikoblenz.ptt.lord.ass01.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoundCloudDate extends Date {

	private static final long serialVersionUID = 3114913857923196811L;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss +0000");

	public SoundCloudDate(final String soundcloudDateString) {
		Date date = new Date();
		try {
			date = dateFormat.parse(soundcloudDateString);
		} catch (ParseException e) {

		}
		this.setTime(date.getTime());
	}

	public int getTimeDifferenceFromTodayInDays() {
		Date date = new Date();
		long timeDifference = date.getTime() - this.getTime();
		return (int) Math.round(timeDifference / (24 * 60 * 60 * 1000));
	}

}
