package de.unikoblenz.ptt.lord.ass03.core.costshareuser.event;

import de.unikoblenz.ptt.lord.ass03.core.costshare.event.CostShareCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.costshareuser.dao.CostShareUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventHandler;

public class CostShareUserEventHandler extends EventHandler {

	private CostShareUserViewDao costShareUserViewDao;

	public CostShareUserEventHandler(CostShareUserViewDao costShareUserViewDao) {
		this.costShareUserViewDao = costShareUserViewDao;
	}

	@Override
	public void handle(Event event) {
		if (event instanceof CostShareCreatedEvent) {
			handleCostShareCreatedEvent((CostShareCreatedEvent) event);
		}
	}

	private void handleCostShareCreatedEvent(CostShareCreatedEvent costShareCreatedEvent) {
		costShareUserViewDao.insert(costShareCreatedEvent.getEntityId(), costShareCreatedEvent.getInitialUserEntityId());
	}

}
