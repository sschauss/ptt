package de.unikoblenz.ptt.lord.ass03.core.costshare.entity;

import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventStore;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Repository;

public class CostShareRepository extends Repository<CostShare> {

	public CostShareRepository(EventStore eventStore) {
		super(eventStore);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CostShare createEntity() {
		return CostShare.createCostShare();
	}

	@Override
	public CostShare getEntity(UUID entityId) {
		List<Event> events = eventStore.get(entityId);
		CostShare costShare = new CostShare(entityId);
		costShare.apply(events);
		return costShare;
	}

}
