package de.unikoblenz.ptt.lord.ass03.core.article.event;

import de.unikoblenz.ptt.lord.ass03.core.article.dao.ArticleViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventHandler;

public class ArticleEventHandler extends EventHandler {

	private ArticleViewDao articleViewDao;

	public ArticleEventHandler(ArticleViewDao articleViewDao) {
		this.articleViewDao = articleViewDao;
	}

	@Override
	public void handle(Event event) {
		if (event instanceof ArticleCreatedEvent) {
			handleArticleCreatedEvent((ArticleCreatedEvent) event);
		}
	}

	private void handleArticleCreatedEvent(ArticleCreatedEvent articleCreatedEvent) {
		articleViewDao.insert(articleCreatedEvent);
	}

}
