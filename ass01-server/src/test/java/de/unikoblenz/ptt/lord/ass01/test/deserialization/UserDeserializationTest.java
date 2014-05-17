package de.unikoblenz.ptt.lord.ass01.test.deserialization;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import de.unikoblenz.ptt.lord.ass01.core.soundcloud.User;

public class UserDeserializationTest extends DeserializationTest {

	@Test
	public void deserializeUser() throws Exception {
		final User user = new User(4444170, "schauboga", 0, 0, 106, 536, 210);
		final User userFromJSON = OBJECT_MAPPER.readValue(fixture("fixtures/entities/user.json"), User.class);
		assertThat("a user can be deserialized from JSON", user, is(userFromJSON));
	}

	@Test
	public void deserializeUsers() throws Exception {
		final List<User> users = new ArrayList<>();

		users.add(new User(27430596, "Tom Schau 1", 1, 0, 4, 0, 0));
		users.add(new User(80380761, "Yrsa Schau", 5, 0, 1, 18, 0));
		users.add(new User(61934419, "Vermona-Schau-Ensemble", 0, 0, 0, 0, 0));
		users.add(new User(52284710, "Franziska Schau", 0, 3, 2, 54, 41));
		users.add(new User(14847024, "Ronny Er Schau Wieder", 0, 0, 54, 180, 27));
		users.add(new User(18465994, "Live Schau", 0, 1, 18, 5, 6));
		users.add(new User(51552879, "Julia Marie Schau", 0, 0, 42, 77, 2));
		users.add(new User(55387533, "Rayleen Schau", 0, 1, 0, 25, 0));
		users.add(new User(9455794, "SCHAU ORT", 1, 0, 0, 0, 1));
		users.add(new User(61953736, "Jackson Lee Schau", 0, 0, 7, 100, 0));

		final List<User> usersFromJSON = OBJECT_MAPPER.readValue(fixture("fixtures/entities/users.json"), new TypeReference<List<User>>() {
		});
		assertThat("users can be deserialized from JSON", users, is(usersFromJSON));
	}

}
