package de.unikoblenz.ptt.lord.ass03.core.articleuser.dao;

import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface ArticleUserViewDao {

	@SqlUpdate("INSERT INTO article_user_view_store (article_entity_id, user_entity_id) VALUES (:articleEntityId, :userEntityId)")
	void insert(UUID articleEntityId, UUID userEntityId);

}
