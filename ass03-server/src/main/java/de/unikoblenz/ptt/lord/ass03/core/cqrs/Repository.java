package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.UUID;

public abstract class Repository<T extends AggregateRoot> {

	protected EventStore eventStore;

	public Repository(EventStore eventStore) {
		super();
		this.eventStore = eventStore;
	}

	public abstract T createEntity();

	public abstract T getEntity(UUID entityId);

	public void save(T entity) {
		entity.storeEvents(eventStore);
	}

}
