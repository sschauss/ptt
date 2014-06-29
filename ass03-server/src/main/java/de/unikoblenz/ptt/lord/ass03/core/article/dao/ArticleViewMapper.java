package de.unikoblenz.ptt.lord.ass03.core.article.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import de.unikoblenz.ptt.lord.ass03.core.article.view.ArticleView;

public class ArticleViewMapper implements ResultSetMapper<ArticleView>{

	@Override
	public ArticleView map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		UUID entityId = UUID.fromString(r.getString("entity_id"));
		UUID purchaserEntityId = UUID.fromString(r.getString("purchser_entity_id"));
		Timestamp purchaseDate = r.getTimestamp("purchase_date");
		String name = r.getString("name");
		BigDecimal value = r.getBigDecimal("value");
		List<UUID> consumerEntityIds = new ArrayList<>();
		for (String consumerEntityId : (String[]) r.getArray("consumer_entity_ids").getArray()) {
			consumerEntityIds.add(UUID.fromString(consumerEntityId));
		}
		return new ArticleView(entityId, purchaserEntityId, purchaseDate, name, value, consumerEntityIds);
	}

}
