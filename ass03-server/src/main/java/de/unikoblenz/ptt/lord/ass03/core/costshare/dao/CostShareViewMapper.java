package de.unikoblenz.ptt.lord.ass03.core.costshare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import de.unikoblenz.ptt.lord.ass03.core.costshare.view.CostShareView;

public class CostShareViewMapper implements ResultSetMapper<CostShareView> {

	@Override
	public CostShareView map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID entityId = UUID.fromString(r.getString("entity_id"));
		String name = r.getString("name");
		return new CostShareView(entityId, name);
	}

}
