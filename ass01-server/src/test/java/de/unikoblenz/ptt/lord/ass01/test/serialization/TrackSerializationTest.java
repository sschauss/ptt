package de.unikoblenz.ptt.lord.ass01.test.serialization;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.unikoblenz.ptt.lord.ass01.api.TrackView;

public class TrackSerializationTest extends SerializationTest {

	@Test
	public void serializeTrackView() throws Exception {
		final TrackView trackView = new TrackView(58221417, "BeatsfürdieBirne", 0, 26, 321, 11, 2.26);
		final String trackViewAsJSON = OBJECT_MAPPER.writeValueAsString(trackView);
		assertThat("a track can be serialized to JSON", trackViewAsJSON, is(fixture("fixtures/views/trackView.json")));
	}

	@Test
	public void serializeTrackViews() throws Exception {
		final List<TrackView> trackViews = new ArrayList<>();
		trackViews.add(new TrackView(104542592, "Interview zum Bonkers Break Vol. 5 mit Tobias Geisler", 0, 0, 76, 2, 1.97));
		trackViews.add(new TrackView(58221417, "BeatsfürdieBirne", 0, 26, 321, 11, 2.26));
		trackViews.add(new TrackView(9387769, "Neonlights & Overdrive - Continuous Mix", 3, 64, 580, 7, 2.14));
		final String trackViewAsJSON = OBJECT_MAPPER.writeValueAsString(trackViews);
		assertThat("tracks can be serialized to JSON", trackViewAsJSON, is(fixture("fixtures/views/trackViews.json")));
	}

}
