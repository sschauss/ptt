package de.unikoblenz.ptt.lord.ass03.jdbi.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class ArticleMapper  implements ResultSetMapper<Article>{

	public Article map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID id = UUID.fromString(r.getString("id"));
		String name = r.getString("name");
		Long value = r.getLong("value");
		UUID purchaserId = UUID.fromString(r.getString("purchaser"));
		return new Article(id, name, value, purchaserId);
	}

}
