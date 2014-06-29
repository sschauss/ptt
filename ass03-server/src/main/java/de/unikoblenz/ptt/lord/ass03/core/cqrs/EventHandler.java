package de.unikoblenz.ptt.lord.ass03.core.cqrs;

public abstract class EventHandler {

	public abstract void handle(Event event);

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
