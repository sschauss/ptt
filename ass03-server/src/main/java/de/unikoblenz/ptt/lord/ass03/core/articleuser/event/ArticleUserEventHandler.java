package de.unikoblenz.ptt.lord.ass03.core.articleuser.event;

import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleDeletedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleUpdatedEvent;
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
		} else if (event instanceof ArticleUpdatedEvent) {
			handleArticleUpdatedEvent((ArticleUpdatedEvent) event);
		} else if (event instanceof ArticleDeletedEvent) {
			handleArticleDeletedEvent((ArticleDeletedEvent) event);
		}
	}

	private void handleArticleCreatedEvent(ArticleCreatedEvent event) {
		for (UUID userEntityId : event.getUserEntityIds()) {
			articleUserViewDao.insert(event.getEntityId(), userEntityId);
		}
	}

	private void handleArticleUpdatedEvent(ArticleUpdatedEvent event) {
		articleUserViewDao.deleteByArticleEntityId(event.getEntityId());
		for (UUID userEntityId : event.getUserEntityIds()) {
			articleUserViewDao.insert(event.getEntityId(), userEntityId);			
		}
	}

	private void handleArticleDeletedEvent(ArticleDeletedEvent event) {
		articleUserViewDao.deleteByArticleEntityId(event.getEntityId());
	}

}
