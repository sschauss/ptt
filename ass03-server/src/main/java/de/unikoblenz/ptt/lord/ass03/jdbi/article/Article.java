package de.unikoblenz.ptt.lord.ass03.jdbi.article;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {

	private UUID id;
	
	private String name;
	
	private Long value;
	
	private UUID purchaserId;
	
	public Article() {
		this.id = UUID.randomUUID();
	}

	public Article(UUID id, String name, Long value, UUID purchaserId) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.purchaserId = purchaserId;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public UUID getPurchaserId() {
		return purchaserId;
	}
	
}
