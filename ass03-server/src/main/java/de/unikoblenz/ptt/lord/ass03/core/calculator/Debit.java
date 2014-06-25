package de.unikoblenz.ptt.lord.ass03.core.calculator;

import java.util.UUID;

public class Debit {
	
	private final UUID purchasorId;
	
	private final UUID debitorId;
	
	private final double debts;
	
	public Debit(UUID purchasorId, UUID debitorId, double debts) {
		this.purchasorId = purchasorId;
		this.debitorId = debitorId;
		this.debts = debts;
	}

	public UUID getPurchasorId() {
		return purchasorId;
	}

	public UUID getDebitorId() {
		return debitorId;
	}

	public double getDebts() {
		return debts;
	}
	
}
