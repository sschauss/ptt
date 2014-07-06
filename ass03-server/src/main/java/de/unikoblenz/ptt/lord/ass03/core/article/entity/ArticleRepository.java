package de.unikoblenz.ptt.lord.ass03.core.article.entity;

import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.EventStore;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Repository;

public class ArticleRepository extends Repository<Article> {

	public ArticleRepository(EventStore eventStore) {
		super(eventStore);
	}

	@Override
	public Article createEntity() {
		return Article.newArticle();
	}

	@Override
	public Article getEntity(UUID entityId) {
		List<Event> events = eventStore.get(entityId);
		Article article = new Article(entityId);
		article.apply(events);
		return article;

	}

}
