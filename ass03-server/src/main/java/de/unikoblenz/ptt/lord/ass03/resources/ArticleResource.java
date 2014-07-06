package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.unikoblenz.ptt.lord.ass03.core.article.command.CreateArticleCommand;
import de.unikoblenz.ptt.lord.ass03.core.article.command.DeleteArticleCommand;
import de.unikoblenz.ptt.lord.ass03.core.article.command.UpdateArticleCommand;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;

@Path("/articles")
public class ArticleResource extends Resource {

	public ArticleResource(CommandBus commandBus) {
		super(commandBus);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createArticle(CreateArticleCommand command) {
		commandBus.publish(command);
		return Response.status(Status.CREATED).build();
	}

	@POST
	@Path("/{entityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArticle(UpdateArticleCommand command) {
		commandBus.publish(command);
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("/{entityId}")
	public Response deleteArticle(@PathParam("entityId") UUID entityId) {
		commandBus.publish(new DeleteArticleCommand(entityId));
		return Response.ok().build();
	}

}
