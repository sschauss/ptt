package de.unikoblenz.ptt.lord.ass03.core.article.event;

import java.sql.Timestamp;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class ArticleDeletedEvent extends Event {

	private static final long serialVersionUID = -416042922840211696L;

	public ArticleDeletedEvent(UUID entityId, Timestamp createdAt) {
		super(entityId, createdAt);
	}

}
