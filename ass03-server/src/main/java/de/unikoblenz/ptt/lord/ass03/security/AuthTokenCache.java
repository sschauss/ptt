package de.unikoblenz.ptt.lord.ass03.security;

import java.util.UUID;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class AuthTokenCache {

	private BiMap<UUID, UUID> authTokens = HashBiMap.create();

	public void addAuthToken(UUID id, UUID authToken) {
		synchronized (authTokens) {
			if (authTokens.containsKey(id)) {
				authTokens.inverse().remove(id);
			}
			authTokens.put(id, authToken);
		}
	}

	public UUID authorized(UUID authToken) {
		return authTokens.inverse().get(authToken);
	}
}