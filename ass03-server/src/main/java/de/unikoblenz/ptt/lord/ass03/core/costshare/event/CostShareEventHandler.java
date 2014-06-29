package de.unikoblenz.ptt.lord.ass03.core.costshare.event;

import de.unikoblenz.ptt.lord.ass03.core.costshare.dao.CostShareViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventHandler;

public class CostShareEventHandler extends EventHandler {

	private CostShareViewDao costShareViewDao;

	public CostShareEventHandler(CostShareViewDao costShareViewDao) {
		this.costShareViewDao = costShareViewDao;
	}

	@Override
	public void handle(Event event) {
		if (event instanceof CostShareCreatedEvent) {
			handleCostShareCreatedEvent((CostShareCreatedEvent) event);
		}
	}

	private void handleCostShareCreatedEvent(CostShareCreatedEvent costShareCreatedEvent) {
		costShareViewDao.insert(costShareCreatedEvent.getEntityId(), costShareCreatedEvent.getName());
	}

}
