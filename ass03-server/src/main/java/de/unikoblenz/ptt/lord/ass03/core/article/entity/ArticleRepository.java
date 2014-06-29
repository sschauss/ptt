package de.unikoblenz.ptt.lord.ass03.core.article.entity;

import java.util.UUID;

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
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
