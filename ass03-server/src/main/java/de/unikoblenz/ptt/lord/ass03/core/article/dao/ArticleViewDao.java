package de.unikoblenz.ptt.lord.ass03.core.article.dao;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import de.unikoblenz.ptt.lord.ass03.core.article.event.ArticleCreatedEvent;
import de.unikoblenz.ptt.lord.ass03.core.article.view.ArticleView;

@RegisterMapper(ArticleViewMapper.class)
public interface ArticleViewDao {

	@SqlUpdate("INSERT INTO article_view_store (entity_id, purchaser_entity_id, cost_share_entity_id, purchase_date, name, value) VALUES (:entityId, :purchaserEntityId, :costShareEntityId, :purchaseDate, :name, :value)")
	void insert(@BindBean ArticleCreatedEvent articleCreatedEvent);

	@SqlQuery("SELECT * FROM article_view_store WHERE entity_id = :entityId")
	ArticleView select(@Bind("entityId") UUID entityId);

	@SqlQuery("SELECT * FROM article_view_store WHERE cost_share_entity_id = :entityId")
	List<ArticleView> selectByCostShareEntityId(@Bind("entityId") UUID entityId);

}
