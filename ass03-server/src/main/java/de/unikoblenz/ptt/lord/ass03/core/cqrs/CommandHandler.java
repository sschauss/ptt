package de.unikoblenz.ptt.lord.ass03.core.cqrs;

public abstract class CommandHandler<T extends AggregateRoot> {

	protected EventBus eventBus;

	protected Repository<T> repository;

	public CommandHandler(EventBus eventBus, Repository<T> repository) {
		this.eventBus = eventBus;
		this.repository = repository;
	}

	public abstract void handle(Command command);
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
