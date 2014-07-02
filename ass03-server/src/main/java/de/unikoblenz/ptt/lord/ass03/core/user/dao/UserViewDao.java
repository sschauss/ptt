package de.unikoblenz.ptt.lord.ass03.core.user.dao;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.unikoblenz.ptt.lord.ass03.core.user.event.UserCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.user.view.UserView;

@RegisterMapper(UserViewMapper.class)
public interface UserViewDao {

	
	@SqlUpdate("INSERT INTO user_view_store (entity_id, email_address, first_name, last_name) VALUES (:entityId, :emailAddress, :firstName, :lastName)")
	void insert(@BindBean UserCreatedEvent event);

	@SqlQuery("SELECT * FROM user_view_store WHERE entity_id = :entityId")
	UserView selectByEntityId(@Bind("entityId") UUID entityId);
	
	@SqlQuery("SELECT * FROM user_view_store WHERE email_address = :emailAddress")
	UserView get(@Bind("emailAddress") String emailAddress);

	@SqlQuery("SELECT * FROM USER_VIEW_STORE WHERE first_name LIKE :q OR last_name LIKE :q LIMIT 10")
	List<UserView> search(@Bind("q") String q);

}
