package de.unikoblenz.ptt.lord.ass03.core.article.command;

import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class DeleteArticleCommand implements Command {

	private UUID entityId;

	public DeleteArticleCommand(UUID entityId) {
		this.entityId = entityId;
	}

	public UUID getEntityId() {
		return entityId;
	}

}
