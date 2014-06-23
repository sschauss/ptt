package de.unikoblenz.ptt.lord.ass03.core.user;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface UserDao {

	@SqlQuery("SELECT * FROM users")
	List<User> getUsers();
	
	@SqlQuery("SELECT * FROM users WHERE id = :id")
	User getUser(@Bind UUID uuid);
	
}
