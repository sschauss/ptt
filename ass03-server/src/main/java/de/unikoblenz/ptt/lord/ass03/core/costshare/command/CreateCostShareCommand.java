package de.unikoblenz.ptt.lord.ass03.core.costshare.command;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class CreateCostShareCommand implements Command {

	private String name;

	private List<UUID> userEntityIds;

	@JsonCreator
	public CreateCostShareCommand(@JsonProperty("name") String name, @JsonProperty("userEntityIds") List<UUID> userEntityIds) {
		this.name = name;
		this.userEntityIds = userEntityIds;
	}

	public String getName() {
		return name;
	}

	public List<UUID> getUserEntityIds() {
		return userEntityIds;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Initial User Entity Id: " + userEntityIds;
	}

}
