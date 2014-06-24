package de.unikoblenz.ptt.lord.ass03.jdbi.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMapper implements ResultSetMapper<User> {

	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID id = UUID.fromString(r.getString("id"));
		String userName = r.getString("email_address");
		String password = r.getString("password");
		String firstName = r.getString("first_name");
		String lastName = r.getString("last_name");
		return new User(id, userName, password, firstName, lastName);
	}

}
