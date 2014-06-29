package de.unikoblenz.ptt.lord.ass03.core.article.event;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class ArticleCreatedEvent extends Event {

	private static final long serialVersionUID = 4179377893542947767L;

	private UUID purchaserEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> consumerEntityIds;

	public ArticleCreatedEvent(UUID entityId, Timestamp createdAt, UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, List<UUID> consumerEntityIds) {
		super(entityId, createdAt);
		this.purchaserEntityId = purchaserEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.consumerEntityIds = consumerEntityIds;
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
	
	@Override
	public String toString() {
		return super.toString() + ", Purchaser Entity Id" + purchaserEntityId + ", Purchase Date: " + purchaseDate + ", Name: " + name + ", Value: " + value + ", Consumer Entity Ids" + consumerEntityIds;
	}

}
