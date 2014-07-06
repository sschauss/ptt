package de.unikoblenz.ptt.lord.ass03.core.article.event;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class ArticleUpdatedEvent extends Event {

	private static final long serialVersionUID = 3365501833614771450L;

	private UUID purchaserEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> userEntityIds;

	public ArticleUpdatedEvent(UUID entityId, Timestamp createdAt, UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, List<UUID> userEntityIds) {
		super(entityId, createdAt);
		this.purchaserEntityId = purchaserEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.userEntityIds = userEntityIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getPurchaserEntityId() {
		return purchaserEntityId;
	}

	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public List<UUID> getUserEntityIds() {
		return userEntityIds;
	}

}
