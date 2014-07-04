package de.unikoblenz.ptt.lord.ass03.core.articleuser.dao;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ArticleUserViewMapper.class)
public interface ArticleUserViewDao {

	@SqlUpdate("INSERT INTO article_user_view_store (article_entity_id, user_entity_id) VALUES (:articleEntityId, :userEntityId)")
	void insert(@Bind("articleEntityId") UUID articleEntityId, @Bind("userEntityId") UUID userEntityId);
	
	@SqlQuery("SELECT user_entity_id FROM article_user_view_store WHERE article_entity_id = :entityId")
	List<UUID> select(@Bind("entityId") UUID entityId);
}
