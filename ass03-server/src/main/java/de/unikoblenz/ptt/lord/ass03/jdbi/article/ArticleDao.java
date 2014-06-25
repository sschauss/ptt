package de.unikoblenz.ptt.lord.ass03.jdbi.article;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ArticleMapper.class)
public interface ArticleDao {
	
	@SqlQuery("SELECT * FROM articles")
	List<Article> getAll();

	@SqlUpdate("INSERT INTO articles(id, name, value, purchaser) VALUES (:id, :name, :value, :purchaserId)")
	void create(@BindBean Article article);
	
	@SqlQuery("SELECT * FROM articles WHERE id = :id")
	Article get(@Bind UUID id);
	
	@SqlUpdate("UPDATE articles SET name = :name, value = :value, purchaser = :purchaserId WHERE id = :id ")
	void update(@BindBean Article article);
	
	@SqlUpdate("DELETE FROM articles WHERE id = :id")
	void delete(@Bind UUID id);

}
