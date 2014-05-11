package de.unikoblenz.ptt.lord.ass01.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SoundcloudDate extends Date{

	private static final long serialVersionUID = 3114913857923196811L;
	private SimpleDateFormat soundcloudDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss +0000");
	
	public SoundcloudDate(String soundcloudDateString) {
		super();
		Date helperDate = new Date();
		try {
			helperDate = soundcloudDateFormat.parse(soundcloudDateString);
		} catch (ParseException e) {
			helperDate = new Date();
		}
		this.setTime(helperDate.getTime());
	}
	
	public int getTimeDifferenceFromTodayInDays(){
		Date timestamp = new Date();
		long timeDifference = timestamp.getTime() - this.getTime();  
		return (int) Math.round((double)timeDifference / (24. * 60.*60.*1000.)); 
	}
}
