package de.unikoblenz.ptt.lord.ass03.core.costshare.event;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class CostShareCreatedEvent extends Event {

	private static final long serialVersionUID = 5276887279105916230L;

	private String name;

	private List<UUID> userEntityIds;

	public CostShareCreatedEvent(UUID entityId, Timestamp createdAt, String name, List<UUID> userEntityIds) {
		super(entityId, createdAt);
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
		return super.toString() + ", Name: " + name + ", Inital User Entity Id: " + userEntityIds;
	}

}
