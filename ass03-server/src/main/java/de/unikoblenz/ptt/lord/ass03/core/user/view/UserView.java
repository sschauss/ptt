package de.unikoblenz.ptt.lord.ass03.core.user.view;

import java.util.UUID;

public class UserView {

	private UUID entityId;

	private String emailAddress;

	private String firstName;

	private String lastName;

	public UserView(UUID entityId, String emailAddress, String firstName, String lastName) {
		this.entityId = entityId;
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UUID getEntityId() {
		return entityId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
