package de.unikoblenz.ptt.lord.ass03.core.costshare.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.costshare.event.CostShareCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.AggregateRoot;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class CostShare extends AggregateRoot {

	public CostShare(UUID entityId) {
		super(entityId);
	}

	public static CostShare createCostShare() {
		UUID entityId = UUID.randomUUID();
		return new CostShare(entityId);
	}

	public void createCostShare(String name, List<UUID> userEntityIds) {
		Timestamp createdAt = new Timestamp(new Date().getTime());
		CostShareCreatedEvent costShareCreatedEvent = new CostShareCreatedEvent(entityId, createdAt, name, userEntityIds);
		register(costShareCreatedEvent);
	}

	@Override
	public void apply(Event event) {
				
	}

}
