package de.unikoblenz.ptt.lord.ass01.health;

import com.codahale.metrics.health.HealthCheck;

import de.unikoblenz.ptt.lord.ass01.client.UserClient;

public class UserClientHealthCheck extends HealthCheck{
	
	private final UserClient userClient;
	
	

	public UserClientHealthCheck(final UserClient userClient) {
		this.userClient = userClient;
	}



	@Override
	protected Result check() throws Exception {
		try{
			userClient.getUser("4444170");
			userClient.getUsers("schauboga");
			return Result.healthy();
		}catch(final Exception exception){
			return Result.unhealthy(exception);
		}
	}

}
