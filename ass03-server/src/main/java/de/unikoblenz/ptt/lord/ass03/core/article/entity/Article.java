package de.unikoblenz.ptt.lord.ass03.core.article.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
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

	}

	public void createArticle(UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, UUID costShareEntityId, List<UUID> userEntityIds) {
		Timestamp createdAt = new Timestamp(new Date().getTime());
		ArticleCreatedEvent articleCreatedEvent = new ArticleCreatedEvent(entityId, createdAt, purchaserEntityId, purchaseDate, name, value, costShareEntityId, userEntityIds);
		register(articleCreatedEvent);
	}

}
