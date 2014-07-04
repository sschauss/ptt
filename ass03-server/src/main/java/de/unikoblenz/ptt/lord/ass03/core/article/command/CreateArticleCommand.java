package de.unikoblenz.ptt.lord.ass03.core.article.command;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class CreateArticleCommand implements Command {

	private UUID purchaserEntityId;
	
	private UUID costShareEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> userEntityIds;

	@JsonCreator
	public CreateArticleCommand(
			@JsonProperty("purchaserEntityId") UUID purchaserEntityId, 
			@JsonProperty("costShareEntityId") UUID costShareEntityId,
			@JsonProperty("purchaseDate") Timestamp purchaseDate, 
			@JsonProperty("name") String name,
			@JsonProperty("value") BigDecimal value, 
			@JsonProperty("userEntityIds") List<UUID> userEntityIds) {
		this.purchaserEntityId = purchaserEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.costShareEntityId = costShareEntityId;
		this.userEntityIds = userEntityIds;
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

	public void setPurchaseDate(Timestamp purchaseDate) {
		this.purchaseDate = purchaseDate;
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
	
	@Override
	public String toString() {
		return "Purchaser Entity Id: " + purchaserEntityId + ", Cost Share Entity Id: " + costShareEntityId + ", Purchase Date: " + purchaseDate + ", Name: " + name + ", Value: " + value + ", ConsumerEntityIds: " + userEntityIds;
	}

}
