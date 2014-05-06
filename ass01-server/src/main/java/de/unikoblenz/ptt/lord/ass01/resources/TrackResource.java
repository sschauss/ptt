package de.unikoblenz.ptt.lord.ass01.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.unikoblenz.ptt.lord.ass01.api.DoughnutGraph;
import de.unikoblenz.ptt.lord.ass01.core.DoughnutGraphService;

@Path("/tracks")
public class TrackResource {

	private final DoughnutGraphService doughnutGraphService;

	public TrackResource(final DoughnutGraphService doughnutGraphService) {
		this.doughnutGraphService = doughnutGraphService;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public DoughnutGraph createDoughnutChart(final List<String> ids) {
		return doughnutGraphService.create(ids);
	}

}
