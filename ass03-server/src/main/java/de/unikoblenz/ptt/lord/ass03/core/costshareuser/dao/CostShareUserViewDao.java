package de.unikoblenz.ptt.lord.ass03.core.costshareuser.dao;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.unikoblenz.ptt.lord.ass03.core.costshare.dao.CostShareViewMapper;
import de.unikoblenz.ptt.lord.ass03.core.costshare.view.CostShareView;
import de.unikoblenz.ptt.lord.ass03.core.user.dao.UserViewMapper;
import de.unikoblenz.ptt.lord.ass03.core.user.view.UserView;

public interface CostShareUserViewDao {

	@SqlUpdate("INSERT INTO cost_share_user_view_store (cost_share_entity_id, user_entity_id) VALUES (:costShareEntityId, :userEntityId)")
	void insert(@Bind("costShareEntityId") UUID costShareEntityId, @Bind("userEntityId") UUID userEntityId);

	@RegisterMapper(UserViewMapper.class)
	@SqlQuery("SELECT uvs.entity_id, uvs.first_name, uvs.last_name, uvs.email_address FROM cost_share_user_view_store csuvs, user_view_store uvs WHERE csuvs.cost_share_entity_id = :entityId  AND csuvs.user_entity_id = uvs.entity_id")
	List<UserView> selectByCostShareEntityId(@Bind("entityId") UUID entityId);

	@RegisterMapper(CostShareViewMapper.class)
	@SqlQuery("SELECT csvs.entity_id, csvs.name FROM cost_share_user_view_store csuvs, cost_share_view_store csvs WHERE csuvs.user_entity_id = :entityId  AND csuvs.cost_share_entity_id = csvs.entity_id")
	List<CostShareView> selectByUserEntityId(@Bind("entityId") UUID entityId);

}
