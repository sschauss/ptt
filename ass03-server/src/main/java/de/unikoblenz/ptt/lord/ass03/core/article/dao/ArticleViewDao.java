package de.unikoblenz.ptt.lord.ass03.core.article.dao;

import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.view.ArticleView;

@RegisterMapper(ArticleViewMapper.class)
public interface ArticleViewDao {

	@SqlUpdate("INSERT INTO article_view_store (entity_id, purchaser_entity_id, purchase_date, name, value) VALUES (:entityId, :purchaserEntityId, :purchaseDate, :name, :value)")
	void insert(@BindBean ArticleCreatedEvent articleCreatedEvent);

	@SqlQuery("SELECT * FROM article_view_store WHERE entity_id = :entityId")
	ArticleView select(UUID entityId);

}
