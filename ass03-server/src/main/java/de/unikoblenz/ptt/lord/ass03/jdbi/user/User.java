package de.unikoblenz.ptt.lord.ass03.jdbi.user;

import java.util.UUID;

public class User {

	private UUID id;

	private String emailAddress;

	private String password;

	private String firstName;

	private String lastName;

	public User() {
		this.id = UUID.randomUUID();
	}

	public User(UUID id, String emailAddress, String password, String firstName, String lastName) {
		this.id = id;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UUID getId() {
		return id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
