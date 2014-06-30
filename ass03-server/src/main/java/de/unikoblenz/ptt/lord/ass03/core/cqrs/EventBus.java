package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventBus {

	protected static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);

	private Set<EventHandler> eventHandlers = new HashSet<>();

	public void subscribe(EventHandler eventHandler) {
		LOGGER.info(eventHandler + " subscribe");
		eventHandlers.add(eventHandler);
	}

	public void publish(Event event) {
		LOGGER.info("Publish: " + event);
		for (EventHandler eventHandler : eventHandlers) {
			eventHandler.handle(event);
		}
	}

}
