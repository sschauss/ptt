package de.unikoblenz.ptt.lord.ass03.core.article.view;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArticleView {

	private UUID entityId;

	private UUID purchaserEntityId;

	private UUID costShareEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> userEntityIds = new ArrayList<>();

	public ArticleView(UUID entityId, UUID purchaserEntityId, UUID costShareEntityId, Timestamp purchaseDate, String name, BigDecimal value) {
		this.entityId = entityId;
		this.purchaserEntityId = purchaserEntityId;
		this.costShareEntityId = costShareEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
	}

	public UUID getEntityId() {
		return entityId;
	}

	public UUID getPurchaserEntityId() {
		return purchaserEntityId;
	}

	public UUID getCostShareEntityId() {
		return costShareEntityId;
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

	public void setUserEntityIds(List<UUID> userEntityIds) {
		this.userEntityIds = userEntityIds;
	}

}
