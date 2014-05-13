package de.unikoblenz.ptt.lord.ass01.api;

public class EntityView {

	private int id;

	private String label;

	public EntityView(final int id, final String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

}
