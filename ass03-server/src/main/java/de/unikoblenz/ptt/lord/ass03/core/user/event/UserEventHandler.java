package de.unikoblenz.ptt.lord.ass03.core.user.event;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventHandler;
import de.unikoblenz.ptt.lord.ass03.core.user.dao.UserViewDao;

public class UserEventHandler extends EventHandler {

	private UserViewDao userViewDao;

	public UserEventHandler(UserViewDao userViewDao) {
		this.userViewDao = userViewDao;
	}

	@Override
	public void handle(Event event) {
		if (event instanceof UserCreatedEvent) {
			handleUserCreateEvent((UserCreatedEvent) event);
		}
	}

	private void handleUserCreateEvent(UserCreatedEvent userCreatedEvent) {
		userViewDao.insert(userCreatedEvent);
	}

}
