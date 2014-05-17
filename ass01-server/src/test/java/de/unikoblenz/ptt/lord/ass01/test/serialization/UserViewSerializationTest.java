package de.unikoblenz.ptt.lord.ass01.test.serialization;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.unikoblenz.ptt.lord.ass01.api.UserView;

public class UserViewSerializationTest extends SerializationTest {

	@Test
	public void serializeUserView() throws Exception {
		final UserView trackView = new UserView(4444170, "schauboga", 0, 0, 106, 536, 210, 2.34);
		final String userViewAsJSON = OBJECT_MAPPER.writeValueAsString(trackView);
		assertThat("a track can be serialized to JSON", userViewAsJSON, is(fixture("fixtures/views/userView.json")));
	}

	@Test
	public void serializeUserViews() throws Exception {
		final List<UserView> userViews = new ArrayList<>();
		userViews.add(new UserView(27430596, "Tom Schau 1", 1, 0, 4, 0, 0, 1.59));
		userViews.add(new UserView(80380761, "Yrsa Schau", 5, 0, 1, 18, 0, 1.43));
		userViews.add(new UserView(61934419, "Vermona-Schau-Ensemble", 0, 0, 0, 0, 0, 0));
		userViews.add(new UserView(52284710, "Franziska Schau", 0, 3, 2, 54, 41, 1.52));
		userViews.add(new UserView(14847024, "Ronny Er Schau Wieder", 0, 0, 54, 180, 27, 2.2));
		userViews.add(new UserView(18465994, "Live Schau", 0, 1, 18, 5, 6, 2.03));
		userViews.add(new UserView(55387533, "Rayleen Schau", 0, 1, 0, 25, 0, 0.53));
		userViews.add(new UserView(51552879, "Julia Marie Schau", 0, 0, 42, 77, 2, 2.14));
		userViews.add(new UserView(61953736, "Jackson Lee Schau", 0, 0, 7, 100, 0, 1.64));
		userViews.add(new UserView(66063792, "Dan M Schau", 1, 0, 2, 9, 0, 1.36));
		final String userViewAsJSON = OBJECT_MAPPER.writeValueAsString(userViews);
		assertThat("tracks can be serialized to JSON", userViewAsJSON, is(fixture("fixtures/views/userViews.json")));
	}

}
