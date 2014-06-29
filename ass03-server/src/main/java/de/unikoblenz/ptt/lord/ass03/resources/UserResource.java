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

import de.unikoblenz.ptt.lord.ass03.core.costshare.view.CostShareView;
import de.unikoblenz.ptt.lord.ass03.core.costshareuser.dao.CostShareUserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;
import de.unikoblenz.ptt.lord.ass03.core.user.command.CreateUserCommand;
import de.unikoblenz.ptt.lord.ass03.core.user.dao.UserViewDao;
import de.unikoblenz.ptt.lord.ass03.core.user.view.UserView;

@Path("/users")
public class UserResource extends Resource {

	private UserViewDao userViewDao;

	private CostShareUserViewDao costShareUserViewDao;

	public UserResource(CommandBus commandBus, UserViewDao userViewDao, CostShareUserViewDao costShareUserViewDao) {
		super(commandBus);
		this.userViewDao = userViewDao;
		this.costShareUserViewDao = costShareUserViewDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(CreateUserCommand createUserCommand) {
		commandBus.publish(createUserCommand);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{entityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("entityId") UUID entityId) {
		UserView userView = userViewDao.get(entityId);
		return Response.ok(userView).build();
	}

	@GET
	@Path("/{entityId}/costshares")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCostShares(@PathParam("entityId") UUID entityId) {
		List<CostShareView> userView = costShareUserViewDao.selectByUserEntityId(entityId);
		return Response.ok(userView).build();
	}

}
