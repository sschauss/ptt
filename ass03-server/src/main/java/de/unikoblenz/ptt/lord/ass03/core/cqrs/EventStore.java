package de.unikoblenz.ptt.lord.ass03.core.cqrs;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class EventStore {

	private EventDao eventDao;

	public EventStore(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public void store(List<Event> events) {
		for (Event event : events) {
			store(event);
		}
	}

	public void store(Event event) {
		byte[] blob = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(event);
			objectOutputStream.flush();
			blob = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		eventDao.insert(event.getEntityId(), event.getCreatedAt(), blob);
	}

	public List<Event> get(UUID entityId) {
		return eventDao.get(entityId);
	}
}
