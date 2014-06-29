package de.unikoblenz.ptt.lord.ass03.core.costshare.dao;

import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.unikoblenz.ptt.lord.ass03.core.costshare.view.CostShareView;

@RegisterMapper(CostShareViewMapper.class)
public interface CostShareViewDao {

	@SqlUpdate("INSERT INTO cost_share_view_store (entity_id, name) VALUES (:entityId, :name)")
	void insert(@Bind("entityId") UUID entityId, @Bind("name") String name);

	@SqlQuery("SELECT * FROM cost_share_view_store WHERE entity_id = :entityId")
	CostShareView select(@Bind("entityId") UUID entityId);

}
