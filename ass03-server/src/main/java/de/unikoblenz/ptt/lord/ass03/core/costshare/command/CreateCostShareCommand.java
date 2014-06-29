package de.unikoblenz.ptt.lord.ass03.core.costshare.command;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class CreateCostShareCommand implements Command {

	private String name;

	private UUID initialUserEntityId;

	@JsonCreator
	public CreateCostShareCommand(@JsonProperty("name") String name, @JsonProperty("initialUserEntityId") UUID initialUserEntityId) {
		this.name = name;
		this.initialUserEntityId = initialUserEntityId;
	}

	public String getName() {
		return name;
	}

	public UUID getInitialUserEntityId() {
		return initialUserEntityId;
	}
	
	@Override
	public String toString() {
		return "Name: " + name + ", Initial User Entity Id: " + initialUserEntityId;
	}

}
