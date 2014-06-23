package de.unikoblenz.ptt.lord.ass03.core.flatshare;

import java.util.List;
import java.util.UUID;

public class FlatShare {

	private UUID id;

	private String street;

	private String houseNumber;

	private String city;

	private List<UUID> userIds;

	public FlatShare(UUID id, String street, String houseNumber, String city, List<UUID> userIds) {
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.userIds = userIds;
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

	public List<UUID> getUserIds() {
		return userIds;
	}

}
