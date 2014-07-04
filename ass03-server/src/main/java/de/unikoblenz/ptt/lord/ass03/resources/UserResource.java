package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@QueryParam("q") String q) {
		List<UserView> userViews = userViewDao.search(q + "%");
		return Response.ok(userViews).build();
	}

	@GET
	@Path("/{entityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("entityId") UUID entityId) {
		UserView userView = userViewDao.selectByEntityId(entityId);
		return Response.ok(userView).build();
	}

	@GET
	@Path("/{entityId}/costshares")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCostShares(@PathParam("entityId") UUID entityId) {
		List<CostShareView> costSharesViews = costShareUserViewDao.selectByUserEntityId(entityId);
		return Response.ok(costSharesViews).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserCredentials userCredentials) {
		UserView userView = userViewDao.get(userCredentials.getEmailAddress());
		if (userView == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			return Response.status(Status.CREATED).header("x-entityId", userView.getEntityId()).build();
		}
	}

}
