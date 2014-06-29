package de.unikoblenz.ptt.lord.ass03.core.user.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.AggregateRoot;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.user.event.UserCreatedEvent;

public class User extends AggregateRoot {

	public User(UUID entityId) {
		super(entityId);
	}

	public static User newUser() {
		UUID entityId = UUID.randomUUID();
		return new User(entityId);
	}

	public void createUser(String emailAddress, String password, String firstName, String lastName) {
		Timestamp createdAt = new Timestamp(new Date().getTime());
		UserCreatedEvent userCreatedEvent = new UserCreatedEvent(entityId, createdAt, emailAddress, password, firstName, lastName);
		register(userCreatedEvent);
	}

	@Override
	public void apply(Event event) {
		if (event instanceof UserCreatedEvent) {
			applyUserCreatedEvent((UserCreatedEvent) event);
		}
	}

	private void applyUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
		entityId = userCreatedEvent.getEntityId();
	}

}
