package de.unikoblenz.ptt.lord.ass03.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;

import de.unikoblenz.ptt.lord.ass03.jdbi.article.Article;
import de.unikoblenz.ptt.lord.ass03.jdbi.article.ArticleDao;

@Path("/articles")
public class ArticleResource {

	private final ArticleDao articleDao;
	
	public ArticleResource(DBI jdbi) {
		articleDao = jdbi.onDemand(ArticleDao.class); 
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Article> getAll() {
		return articleDao.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Article article) {
		System.out.println("XXXXXXX");
		articleDao.create(article);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Article get(@PathParam("id") UUID id) {
		return articleDao.get(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(Article article) {
		articleDao.update(article);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") UUID id) {
		articleDao.delete(id);
	}
	
}