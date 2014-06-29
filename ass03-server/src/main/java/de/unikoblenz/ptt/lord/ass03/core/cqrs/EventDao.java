package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(EventMapper.class)
public interface EventDao {

	@SqlUpdate("INSERT INTO event_store(entity_id, created_at, event) VALUES (:entityId, :createdAt, :event)")
	void insert(@Bind("entityId") UUID entityId, @Bind("createdAt") Timestamp createdAt, @Bind("event") byte[] event);
	
	@SqlQuery("SELECT event FROM event_store WHERE entity_id = :entityId ORDER BY created_at ASC")
	List<Event> get(@Bind("entityId") UUID entityId);
	
}
