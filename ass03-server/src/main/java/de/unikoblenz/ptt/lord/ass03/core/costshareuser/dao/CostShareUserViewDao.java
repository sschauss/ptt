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
	void insert(@Bind("costShareEntityId") UUID costShareEntityId, @Bind("userEntityId") UUID userEntityid);

	@RegisterMapper(UserViewMapper.class)
	@SqlQuery("SELECT * FROM user_view_store INNER JOIN cost_share_user_view_store ON cost_share_user_view_store.cost_share_entity_id = :entityId")
	List<UserView> selectByCostShareEntityId(@Bind("entityId") UUID entityId);

	@RegisterMapper(CostShareViewMapper.class)
	@SqlQuery("SELECT * FROM cost_share_view_store INNER JOIN cost_share_user_view_store ON cost_share_user_view_store.user_entity_id = :entityId")
	List<CostShareView> selectByUserEntityId(@Bind("entityId") UUID entityId);

}
