package de.unikoblenz.ptt.lord.ass03.resources;

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

import de.unikoblenz.ptt.lord.ass03.core.article.command.CreateArticleCommand;
import de.unikoblenz.ptt.lord.ass03.core.article.dao.ArticleViewDao;
import de.unikoblenz.ptt.lord.ass03.core.article.view.ArticleView;
import de.unikoblenz.ptt.lord.ass03.core.cqrs.CommandBus;

@Path("/articles")
public class ArticleResource extends Resource {

	private ArticleViewDao articleViewDao;

	public ArticleResource(CommandBus commandBus, ArticleViewDao articleViewDao) {
		super(commandBus);
		this.articleViewDao = articleViewDao;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createArticle(CreateArticleCommand createArticleCommand) {
		commandBus.publish(createArticleCommand);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{entityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticle(@PathParam("entityId") UUID entityId) {
		ArticleView articleView = articleViewDao.select(entityId);
		return Response.ok(articleView).build();
	}

}
