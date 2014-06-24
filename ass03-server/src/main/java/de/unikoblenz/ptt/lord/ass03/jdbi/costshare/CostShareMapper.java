package de.unikoblenz.ptt.lord.ass03.jdbi.costshare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class CostShareMapper implements ResultSetMapper<CostShare> {

	public CostShare map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID id = UUID.fromString(r.getString("id"));
		String name = r.getString("name");
		return new CostShare(id, name);
	}

}
