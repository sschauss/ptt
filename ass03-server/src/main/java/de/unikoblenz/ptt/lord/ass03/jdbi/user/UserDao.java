package de.unikoblenz.ptt.lord.ass03.jdbi.user;

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
	List<User> getAll();

	@SqlUpdate("INSERT INTO users(id, firstName, lastName, flatShareId) VALUES(:id, :firstName, :lastName, :flatShareId)")
	void create(@BindBean User user);

	@SqlQuery("SELECT * FROM users WHERE id = :id")
	User get(@Bind("id") UUID id);

	@SqlUpdate("UPDATE users SET firstName = :firstName, lastName = :lastname, flatShareId = :flatShareId WHERE id = :id")
	void update(User user);

	@SqlUpdate("DELETE FROM users WHERE id = :id")
	void delete(UUID id);

}