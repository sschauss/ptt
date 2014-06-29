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
		if(command instanceof CreateArticleCommand) {
			handleCreateArticleCommand((CreateArticleCommand) command);
		}
	}

	private void handleCreateArticleCommand(CreateArticleCommand createArticleCommand) {
		Article article = repository.createEntity();
		article.createArticle(createArticleCommand.getPurchaserEntityId(), createArticleCommand.getPurchaseDate(), createArticleCommand.getName(), createArticleCommand.getValue(), createArticleCommand.getConsumerEntityIds());
		article.publishEvents(eventBus);
		repository.save(article);
	}

}

