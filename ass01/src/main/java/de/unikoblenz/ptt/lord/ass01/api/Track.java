package de.unikoblenz.ptt.lord.ass01.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

	private int id;

	public Track() {

	}

	public int getId() {
		return id;
	}

}
