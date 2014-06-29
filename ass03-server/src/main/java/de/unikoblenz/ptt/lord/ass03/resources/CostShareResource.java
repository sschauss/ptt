package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.unikoblenz.ptt.lord.ass03.core.costshare.command.CreateCostShareCommand;
import de.unikoblenz.ptt.lord.ass03.core.costshare.dao.CostShareViewDao;
import de.unikoblenz.ptt.lord.ass03.core.costshare.view.CostShareView;
import de.unikoblenz.ptt.lord.ass03.core.costshareuser.dao.CostShareUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;
import de.unikoblenz.ptt.lord.ass03.core.user.view.UserView;

@Path("/costshares")
public class CostShareResource extends Resource {

	private CostShareViewDao costShareViewDao;

	private CostShareUserViewDao costShareUserViewDao;

	public CostShareResource(CommandBus commandBus, CostShareViewDao costShareViewDao, CostShareUserViewDao costShareUserViewDao) {
		super(commandBus);
		this.costShareViewDao = costShareViewDao;
		this.costShareUserViewDao = costShareUserViewDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCostShare(CreateCostShareCommand createCostShareCommand) {
		commandBus.publish(createCostShareCommand);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{entityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCostShare(@PathParam("entityId") UUID entityId) {
		CostShareView costShareView = costShareViewDao.select(entityId);
		return Response.ok(costShareView).build();
	}

	@GET
	@Path("/{entityId}/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCostShareUsers(@PathParam("entityId") UUID entityId) {
		List<UserView> costShareUserViews = costShareUserViewDao.selectByCostShareEntityId(entityId);
		return Response.ok(costShareUserViews).build();
	}

}
