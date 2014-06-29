package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AggregateRoot {

	protected UUID entityId;

	private List<Event> unpublishedEvents = new ArrayList<>();

	public AggregateRoot(UUID entityId) {
		this.entityId = entityId;
	}

	public void register(Event event) {
		apply(event);
		unpublishedEvents.add(event);
	}

	public abstract void apply(Event event);

	public void apply(List<Event> events) {
		for (Event event : events) {
			apply(event);
		}
	}

	public void publishEvents(EventBus eventBus) {
		for (Event event : unpublishedEvents) {
			eventBus.publish(event);
		}
	}

	public void storeEvents(EventStore eventStore) {
		eventStore.store(unpublishedEvents);
	}

}
