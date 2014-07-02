package de.unikoblenz.ptt.lord.ass03.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentials {

	private String emailAddress;

	private String password;

	@JsonCreator
	public UserCredentials(@JsonProperty("emailAddress") String emailAddress, @JsonProperty("password") String password) {
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPassword() {
		return password;
	}

}