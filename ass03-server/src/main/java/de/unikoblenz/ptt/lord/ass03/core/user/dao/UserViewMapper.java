package de.unikoblenz.ptt.lord.ass03.core.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import de.unikoblenz.ptt.lord.ass03.core.user.view.UserView;

public class UserViewMapper implements ResultSetMapper<UserView> {

	@Override
	public UserView map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID entityId = UUID.fromString(r.getString("entity_id"));
		String emailAddress = r.getString("email_address");
		String firstName = r.getString("first_name");
		String lastName = r.getString("last_name");
		return new UserView(entityId, emailAddress, firstName, lastName);
	}

}
