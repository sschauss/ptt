package de.unikoblenz.ptt.lord.ass03.core.costshare.command;

import de.unikoblenz.ptt.lord.ass03.core.costshare.entity.CostShare;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventBus;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Repository;

public class CostShareCommandHandler extends CommandHandler<CostShare> {

	public CostShareCommandHandler(EventBus eventBus, Repository<CostShare> repository) {
		super(eventBus, repository);
	}

	@Override
	public void handle(Command command) {
		if (command instanceof CreateCostShareCommand) {
			handleCreateCostShareCommand((CreateCostShareCommand) command);
		}
	}

	private void handleCreateCostShareCommand(CreateCostShareCommand createCostShareCommand) {
		CostShare costShare = repository.createEntity();
		costShare.createCostShare(createCostShareCommand.getName(), createCostShareCommand.getUserEntityIds());
		costShare.publishEvents(eventBus);
		repository.save(costShare);
	}

}
