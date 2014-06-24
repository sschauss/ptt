package de.unikoblenz.ptt.lord.ass03.jdbi.user;

import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDao {

	@SqlUpdate("INSERT INTO user(id, email_address, password, first_name, last_name) VALUES(:id, :emailAddress, :password, :firstName, :lastName)")
	void create(@BindBean User user);

	@SqlQuery("SELECT * FROM user WHERE id = :id")
	User get(@Bind("id") UUID id);
	
	@SqlQuery("SELECT * FROM user WHERE email_address = :emailAddress")
	User get(@Bind("emailAddress") String emailAddress);

}