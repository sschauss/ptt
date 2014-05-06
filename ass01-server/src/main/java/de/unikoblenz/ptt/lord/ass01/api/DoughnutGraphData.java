package de.unikoblenz.ptt.lord.ass01.api;

public class DoughnutGraphData {

	private Integer value;

	private String color;

	public DoughnutGraphData(final Integer value, final String color) {
		this.value = value;
		this.color = color;
	}

	public DoughnutGraphData() {

	}

	public Integer getValue() {
		return value;
	}

	public String getColor() {
		return color;
	}

}
