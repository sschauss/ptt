package de.unikoblenz.ptt.lord.ass01.api;

import java.util.List;

public class DoughnutGraph {

	private List<DoughnutGraphData> data;

	public DoughnutGraph() {

	}

	public DoughnutGraph(List<DoughnutGraphData> data) {
		this.data = data;
	}

	public List<DoughnutGraphData> getData() {
		return data;
	}

}
