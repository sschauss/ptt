package de.unikoblenz.ptt.lord.ass03.jdbi.costshare;

import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(CostShareMapper.class)
public interface CostShareDao {

	@SqlUpdate("INSERT INTO flatShares(id, street, houseNumber, city) VALUES (:id, :street, :houseNumber, :city)")
	void create(@BindBean CostShare flatShare);

	@SqlQuery("SELECT * FROM flatShares WHERE id = :id")
	CostShare get(@Bind("id") UUID id);

}
