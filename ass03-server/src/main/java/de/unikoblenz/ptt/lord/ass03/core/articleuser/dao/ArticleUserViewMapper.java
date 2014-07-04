package de.unikoblenz.ptt.lord.ass03.core.articleuser.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ArticleUserViewMapper implements ResultSetMapper<UUID>{

	@Override
	public UUID map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return UUID.fromString(r.getString("user_entity_id"));
	}

	
}
