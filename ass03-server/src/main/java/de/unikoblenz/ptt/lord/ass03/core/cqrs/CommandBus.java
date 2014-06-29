package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandBus {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(CommandBus.class);

	private Set<CommandHandler<?>> commandHandlers = new HashSet<>();

	public void subscribe(CommandHandler<?> commandHandler) {
		commandHandlers.add(commandHandler);
		LOGGER.info(commandHandler.toString() + " subscribed");
	}

	public void publish(Command command) {
		for (CommandHandler<?> commandHandler : commandHandlers) {
			commandHandler.handle(command);
		}
		LOGGER.info("Published: " + command);
	}

}
