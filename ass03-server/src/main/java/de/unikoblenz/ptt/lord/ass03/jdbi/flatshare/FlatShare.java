package de.unikoblenz.ptt.lord.ass03.jdbi.flatshare;

import java.util.UUID;

public class FlatShare {

	private UUID id;

	private String street;

	private String houseNumber;

	private String city;

	public FlatShare() {
		this.id = UUID.randomUUID();
	}

	public FlatShare(UUID id, String street, String houseNumber, String city) {
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	public UUID getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getCity() {
		return city;
	}

}
