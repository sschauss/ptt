package de.unikoblenz.ptt.lord.ass01.core;

import java.util.ArrayList;
import java.util.List;

import de.unikoblenz.ptt.lord.ass01.api.DoughnutGraph;
import de.unikoblenz.ptt.lord.ass01.api.DoughnutGraphData;
import de.unikoblenz.ptt.lord.ass01.client.TrackClient;

public class DoughnutGraphService {

	private final TrackClient trackClient;

	public DoughnutGraphService(TrackClient trackClient) {
		this.trackClient = trackClient;
	}

	public DoughnutGraph create(final List<String> ids) {
		List<DoughnutGraphData> data = new ArrayList<>();
		for (final Track track : trackClient.getTracks(ids)) {
			final DoughnutGraphData doughnutGraphData = new DoughnutGraphData(track.getPlaybackCount(), "red");
			data.add(doughnutGraphData);
		}
		return new DoughnutGraph(data);
	}

}
