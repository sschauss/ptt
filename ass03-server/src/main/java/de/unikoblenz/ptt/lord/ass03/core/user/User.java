package de.unikoblenz.ptt.lord.ass03.core.user;

import java.util.UUID;

public class User {

	private UUID id;

	private String firstName;

	private String lastName;

	private UUID flatShareId;

	public User(UUID id, String firstName, String lastName, UUID flatShareId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.flatShareId = flatShareId;
	}

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public UUID getFlatShareId() {
		return flatShareId;
	}

}
