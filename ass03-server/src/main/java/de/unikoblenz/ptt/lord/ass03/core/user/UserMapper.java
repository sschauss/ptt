package de.unikoblenz.ptt.lord.ass03.core.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMapper implements ResultSetMapper<User> {

	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID id = UUID.fromString(r.getString("id"));
		String firstName = r.getString("firstName");
		String lastName = r.getString("firstName");
		UUID flatShareId = UUID.fromString(r.getString("flatShareId"));
		return new User(id, firstName, lastName, flatShareId);
	}

}
