package de.unikoblenz.ptt.lord.ass03.resources;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;

public abstract class Resource {

	protected CommandBus commandBus;

	public Resource(CommandBus commandBus) {
		this.commandBus = commandBus;
	}

}
