package de.unikoblenz.ptt.lord.ass03.core.article.command;

import de.unikoblenz.ptt.lord.ass03.core.article.entity.Article;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandHandler;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventBus;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Repository;

public class ArticleCommandHandler extends CommandHandler<Article> {

	public ArticleCommandHandler(EventBus eventBus, Repository<Article> repository) {
		super(eventBus, repository);
	}

	@Override
	public void handle(Command command) {
		if (command instanceof CreateArticleCommand) {
			handleCreateArticleCommand((CreateArticleCommand) command);
		} else if (command instanceof UpdateArticleCommand) {
			handleUpdateArticleCommand((UpdateArticleCommand) command);
		} else if (command instanceof DeleteArticleCommand) {
			handleDeleteArticleCommand((DeleteArticleCommand) command);
		}
	}

	private void handleCreateArticleCommand(CreateArticleCommand command) {
		Article article = repository.createEntity();
		article.create(command.getPurchaserEntityId(), command.getPurchaseDate(), command.getName(), command.getValue(), command.getCostShareEntityId(), command.getUserEntityIds());
		article.publishEvents(eventBus);
		repository.save(article);
	}

	private void handleUpdateArticleCommand(UpdateArticleCommand command) {
		Article article = repository.getEntity(command.getEntityId());
		article.update(command.getPurchaserEntityId(), command.getPurchaseDate(), command.getName(), command.getValue(), command.getUserEntityIds());
		article.publishEvents(eventBus);
		repository.save(article);
	}
	
	private void handleDeleteArticleCommand(DeleteArticleCommand command) {
		Article article = repository.getEntity(command.getEntityId());
		article.delete();
		article.publishEvents(eventBus);
		repository.save(article);
	}

}
