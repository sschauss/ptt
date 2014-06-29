package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventBus {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);

	private Set<EventHandler> eventHandlers = new HashSet<>();

	public void subscribe(EventHandler eventHandler) {
		eventHandlers.add(eventHandler);
		LOGGER.info(eventHandler.toString() + " subscribed");
	}

	public void publish(Event event) {
		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
		LOGGER.info("Published: " + event.toString());
	}

}
