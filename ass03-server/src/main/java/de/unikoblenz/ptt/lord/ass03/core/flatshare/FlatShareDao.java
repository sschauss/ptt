package de.unikoblenz.ptt.lord.ass03.core.flatshare;

import java.util.List;
import java.util.UUID;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface FlatShareDao {

	@SqlQuery("SELECT * FROM flatshares")
	List<FlatShare> getFlatShares();
	
	@SqlQuery("SELECT * FROM flatshares WHERE id = :id")
	FlatShare getFlatShare(@Bind UUID uuid);
}
