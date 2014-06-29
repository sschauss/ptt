package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public abstract class Event implements Serializable {

	private static final long serialVersionUID = 1036382564121119301L;

	private UUID entityId;

	private Timestamp createdAt;

	public Event(UUID entityId, Timestamp createdAt) {
		this.entityId = entityId;
		this.createdAt = createdAt;
	}

	public UUID getEntityId() {
		return entityId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	@Override
	public String toString() {
		return "Entity ID: " + entityId + ", Created At: " + createdAt;
	}

}
