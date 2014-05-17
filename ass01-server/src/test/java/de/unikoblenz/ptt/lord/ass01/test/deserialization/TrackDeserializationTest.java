package de.unikoblenz.ptt.lord.ass01.test.deserialization;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import de.unikoblenz.ptt.lord.ass01.core.soundcloud.Track;
import de.unikoblenz.ptt.lord.ass01.util.SoundCloudDate;

public class TrackDeserializationTest  extends DeserializationTest {
	

	@Test
	public void deserializeTrack() throws Exception {
		final Track track = new Track(58221417, "BeatsfürdieBirne",  0, 26, 321, 11, true, new SoundCloudDate("2012/08/31 13:33:09 +0000"));
		final Track userFromJson = OBJECT_MAPPER.readValue(fixture("fixtures/track.json"), Track.class);
		assertThat("a user can be deserialized from JSON", track, is(userFromJson));
	}

	@Test
	public void deserializeTracks() throws Exception {
		final List<Track> tracks = new ArrayList<>();
		final List<Track> tracksFromJson = OBJECT_MAPPER.readValue(fixture("fixtures/tracks.json"), new TypeReference<List<Track>>() {
		});
		tracks.add(new Track(58221417, "BeatsfürdieBirne",  0, 26, 321, 11, true, new SoundCloudDate("2012/08/31 13:33:09 +0000")));
		tracks.add(new Track(104542592, "Interview zum Bonkers Break Vol. 5 mit Tobias Geisler",  0, 0, 76, 2, false, new SoundCloudDate("2013/08/08 14:09:32 +0000"))); 
		tracks.add(new Track(9387769, "Neonlights & Overdrive - Continuous Mix",  3, 64, 580, 7, true, new SoundCloudDate("2011/01/20 15:27:11 +0000"))); 
		assertThat("users can be deserialized from JSON", tracks, is(tracksFromJson));
	}

}
