package de.unikoblenz.ptt.lord.ass03.core.article.view;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ArticleView {

	private UUID entityId;

	private UUID purchaserEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> consumerEntityIds;

	public ArticleView(UUID entityId, UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, List<UUID> consumerEntityIds) {
		this.entityId = entityId;
		this.purchaserEntityId = purchaserEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.consumerEntityIds = consumerEntityIds;
	}

	public UUID getEntityId() {
		return entityId;
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

	public List<UUID> getConsumerEntityIds() {
		return consumerEntityIds;
	}

}
