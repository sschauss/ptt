package de.unikoblenz.ptt.lord.ass03.core.article.event;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Event;

public class ArticleCreatedEvent extends Event {

	private static final long serialVersionUID = 4179377893542947767L;

	private UUID purchaserEntityId;

	private UUID costShareEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> userEntityIds;

	public ArticleCreatedEvent(UUID entityId, Timestamp createdAt, UUID purchaserEntityId, Timestamp purchaseDate, String name, BigDecimal value, UUID costShareEntityId, List<UUID> userEntityIds) {
		super(entityId, createdAt);
		this.purchaserEntityId = purchaserEntityId;
		this.costShareEntityId = costShareEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.userEntityIds = userEntityIds;
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

	public UUID getCostShareEntityId() {
		return costShareEntityId;
	}

	public List<UUID> getUserEntityIds() {
		return userEntityIds;
	}

	@Override
	public String toString() {
		return super.toString() + ", Purchaser Entity Id" + purchaserEntityId + ", Cost Share Entity Id: " + costShareEntityId + ", Purchase Date: " + purchaseDate + ", Name: " + name + ", Value: " + value + ", User Entity Ids" + userEntityIds;
	}

}
