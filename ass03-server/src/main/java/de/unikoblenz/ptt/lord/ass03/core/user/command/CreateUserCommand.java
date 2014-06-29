package de.unikoblenz.ptt.lord.ass03.core.user.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class CreateUserCommand implements Command {

	private String emailAddress;

	private String password;

	private String firstName;

	private String lastName;

	@JsonCreator
	public CreateUserCommand(
			@JsonProperty("emailAddress") String emailAddress, 
			@JsonProperty("password") String password, 
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName) {
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

	@Override
	public String toString() {
		return "Email Address: " + emailAddress + ", Password: xxxx, First Name: " + firstName + ", Last Name: " + lastName;
	}

}
