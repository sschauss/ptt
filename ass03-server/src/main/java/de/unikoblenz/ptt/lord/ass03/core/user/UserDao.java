package de.unikoblenz.ptt.lord.ass03.core.user;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDao {

	@SqlQuery("SELECT * FROM users")
	List<User> getUsers();
	
	@SqlQuery("SELECT * FROM users WHERE id = :id")
	User getUser(@Bind UUID uuid);

	@SqlUpdate("INSERT INTO users(id, firstName, lastName, flatShareId) VALUES(:id, :firstName, :lastName, :flatShareId)")
	void createUser(@BindBean User user);
	
}
