package de.unikoblenz.ptt.lord.ass03.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.unikoblenz.ptt.lord.ass03.core.article.command.CreateArticleCommand;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;

@Path("/articles")
public class ArticleResource extends Resource {



	public ArticleResource(CommandBus commandBus) {
		super(commandBus);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createArticle(CreateArticleCommand createArticleCommand) {
		commandBus.publish(createArticleCommand);
		return Response.status(Status.CREATED).build();
	}


}
