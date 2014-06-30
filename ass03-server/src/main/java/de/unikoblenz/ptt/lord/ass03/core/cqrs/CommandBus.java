package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandBus {

	protected static final Logger LOGGER = LoggerFactory.getLogger(CommandBus.class);

	private Set<CommandHandler<?>> commandHandlers = new HashSet<>();

	public void subscribe(CommandHandler<?> commandHandler) {
		LOGGER.info(commandHandler + " subscribe");
		commandHandlers.add(commandHandler);
	}

	public void publish(Command command) {
		LOGGER.info("Publish: " + command);
		for (CommandHandler<?> commandHandler : commandHandlers) {
			commandHandler.handle(command);
		}
	}

}
