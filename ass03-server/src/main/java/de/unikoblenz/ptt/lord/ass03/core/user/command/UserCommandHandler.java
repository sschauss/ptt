package de.unikoblenz.ptt.lord.ass03.core.user.command;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventBus;
import de.unikoblenz.ptt.lord.ass03.core.user.entity.User;
import de.unikoblenz.ptt.lord.ass03.core.user.entity.UserRepository;

public class UserCommandHandler extends CommandHandler<User> {

	public UserCommandHandler(EventBus eventBus, UserRepository userRepository) {
		super(eventBus, userRepository);
	}

	@Override
	public void handle(Command command) {
		if (command instanceof CreateUserCommand) {
			handleCreateUserCommand((CreateUserCommand) command);
		}
	}

	private void handleCreateUserCommand(CreateUserCommand command) {
		User user = repository.createEntity();
		user.createUser(command.getEmailAddress(), command.getPassword(), command.getFirstName(), command.getLastName());
		user.publishEvents(eventBus);
		repository.save(user);
	}

}
