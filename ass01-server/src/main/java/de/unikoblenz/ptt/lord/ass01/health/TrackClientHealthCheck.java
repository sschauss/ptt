package de.unikoblenz.ptt.lord.ass01.health;

import com.codahale.metrics.health.HealthCheck;

import de.unikoblenz.ptt.lord.ass01.client.TrackClient;

public class TrackClientHealthCheck extends HealthCheck {

	private final TrackClient trackClient;

	public TrackClientHealthCheck(final TrackClient trackClient) {
		this.trackClient = trackClient;
	}

	@Override
	protected Result check() throws Exception {
		try {
			trackClient.getTrack("58221417");
			trackClient.getTracks("k1ste");
			return Result.healthy();
		} catch (final Exception exception) {
			return Result.unhealthy(exception);
		}
	}

}
