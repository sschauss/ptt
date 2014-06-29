package de.unikoblenz.ptt.lord.ass03.core.user.event;

import java.sql.Timestamp;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class UserCreatedEvent extends Event {

	private static final long serialVersionUID = -2283710375454357896L;

	private String emailAddress;

	private String firstName;

	private String lastName;

	public UserCreatedEvent(UUID entityId, Timestamp timestamp, String emailAddress, String password, String firstName, String lastName) {
		super(entityId, timestamp);
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	@Override
	public String toString() {
		return super.toString() + ", Email Address: " + emailAddress + ", Password: xxxx, First Name: " + firstName + ", Last Name: " + lastName;
	}

}
