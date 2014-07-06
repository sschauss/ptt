package de.unikoblenz.ptt.lord.ass03.core.article.command;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.unikoblenz.ptt.lord.ass03.core.cqrs.Command;

public class UpdateArticleCommand implements Command {
	
	private UUID entityId;

	private UUID purchaserEntityId;

	private Timestamp purchaseDate;

	private String name;

	private BigDecimal value;

	private List<UUID> userEntityIds;

	@JsonCreator
	public UpdateArticleCommand(
			@JsonProperty("entityId") UUID entityId, 
			@JsonProperty("purchaserEntityId") UUID purchaserEntityId, 
			@JsonProperty("purchaseDate") Timestamp purchaseDate, 
			@JsonProperty("name") String name,
			@JsonProperty("value") BigDecimal value, 
			@JsonProperty("userEntityIds") List<UUID> userEntityIds) {
		this.entityId = entityId;
		this.purchaserEntityId = purchaserEntityId;
		this.purchaseDate = purchaseDate;
		this.name = name;
		this.value = value;
		this.userEntityIds = userEntityIds;
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

	public List<UUID> getUserEntityIds() {
		return userEntityIds;
	}

}
