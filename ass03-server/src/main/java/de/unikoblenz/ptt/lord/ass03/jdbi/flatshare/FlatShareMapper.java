package de.unikoblenz.ptt.lord.ass03.jdbi.flatshare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class FlatShareMapper implements ResultSetMapper<FlatShare>{

	public FlatShare map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID id = UUID.fromString(r.getString("id"));
		String street = r.getString("street");
		String houseNumber = r.getString("houseNumber");
		String city = r.getString("city");
		return new FlatShare(id, street, houseNumber, city);
	}

}
