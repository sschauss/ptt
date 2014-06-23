package de.unikoblenz.ptt.lord.ass03.jdbi.flatshare;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(FlatShareMapper.class)
public interface FlatShareDao {

	@SqlQuery("SELECT * FROM flatShares")
	List<FlatShare> getAll();

	@SqlUpdate("INSERT INTO flatShares(id, street, houseNumber, city) VALUES (:id, :street, :houseNumber, :city)")
	void create(@BindBean FlatShare flatShare);
	
	@SqlQuery("SELECT * FROM flatShares WHERE id = :id")
	FlatShare get(@Bind UUID id);
	
	@SqlUpdate("UPDATE flatShares SET street = :street, houseNumber = :houseNumber, city = :city WHERE id = :id ")
	void update(@BindBean FlatShare flatShare);
	
	@SqlUpdate("DELETE FROM flatShares WHERE id = :id")
	void delete(@Bind UUID id);
	
}
