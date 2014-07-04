package de.unikoblenz.ptt.lord.ass03.core.articleuser.event;

import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.articleuser.dao.ArticleUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventHandler;

public class ArticleUserEventHandler extends EventHandler {

	private ArticleUserViewDao articleUserViewDao;

	public ArticleUserEventHandler(ArticleUserViewDao articleUserViewDao) {
		this.articleUserViewDao = articleUserViewDao;
	}

	@Override
	public void handle(Event event) {
		if (event instanceof ArticleCreatedEvent) {
			handleArticleCreatedEvent((ArticleCreatedEvent) event);
		}
	}

	private void handleArticleCreatedEvent(ArticleCreatedEvent articleCreatedEvent) {
		for (UUID userEntityId : articleCreatedEvent.getUserEntityIds()) {
			articleUserViewDao.insert(articleCreatedEvent.getEntityId(), userEntityId);
		}
	}

}
