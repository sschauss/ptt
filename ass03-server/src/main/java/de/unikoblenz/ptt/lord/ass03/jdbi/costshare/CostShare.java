package de.unikoblenz.ptt.lord.ass03.jdbi.costshare;

import java.util.UUID;

public class CostShare {

	private UUID id;

	private String name;

	public CostShare() {
		this.id = UUID.randomUUID();
	}

	public CostShare(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
