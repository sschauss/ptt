package de.unikoblenz.ptt.lord.ass03.core.article.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleDeletedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleUpdatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.AggregateRoot;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class Article extends AggregateRoot {

	public Article(UUID entityId) {
		super(entityId);
	}

	public static Article newArticle() {
		UUID entityId = UUID.randomUUID();
		return new Article(entityId);
	}

	@Override
	public void apply(Event event) {
		//no DomainData yet
	}

	public void create(UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, UUID costShareEntityId, List<UUID> userEntityIds) {
		ArticleCreatedEvent articleCreatedEvent = new ArticleCreatedEvent(entityId, getCreatedAt(), purchaserEntityId, purchaseDate, name, value, costShareEntityId, userEntityIds);
		register(articleCreatedEvent);
	}

	public void update(UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, List<UUID> userEntityIds) {
		ArticleUpdatedEvent articleUpdatedEvent = new ArticleUpdatedEvent(entityId, getCreatedAt(), purchaserEntityId, purchaseDate, name, value, userEntityIds);
		register(articleUpdatedEvent);
	}

	public void delete() {
		ArticleDeletedEvent articleDeletedEvent = new ArticleDeletedEvent(entityId, getCreatedAt());
		register(articleDeletedEvent);
	}
}
