package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class EventMapper implements ResultSetMapper<Event> {

	@Override
	public Event map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		Blob blob = r.getBlob("event");
		Event event = null;
		try {

			ObjectInputStream objectInputStream = new ObjectInputStream(blob.getBinaryStream());
			Object object = objectInputStream.readObject();
			event = (Event) object;
		} catch (IOException | ClassNotFoundException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return event;
	}

}
