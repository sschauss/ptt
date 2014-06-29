package de.unikoblenz.ptt.lord.ass03.core.costshare.event;

import java.sql.Timestamp;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class CostShareCreatedEvent extends Event {

	private static final long serialVersionUID = 5276887279105916230L;

	private String name;

	private UUID initialUserEntityId;

	public CostShareCreatedEvent(UUID entityId, Timestamp createdAt, String name, UUID initialUserEntityId) {
		super(entityId, createdAt);
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
		return super.toString() + ", Name: " + name + ", Inital User Entity Id: " + initialUserEntityId;
	}
	

}
