package de.unikoblenz.ptt.lord.ass03.core.costshare.view;

import java.util.UUID;

public class CostShareView {

	private UUID entityId;

	private String name;

	public CostShareView(UUID entityId, String name) {
		this.entityId = entityId;
		this.name = name;
	}

	public UUID getEntityId() {
		return entityId;
	}

	public String getName() {
		return name;
	}

}
