package de.unikoblenz.ptt.lord.ass01.core.jackson;

import java.io.IOException;

import io.dropwizard.util.Duration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DurationDeserializer extends JsonDeserializer<Duration> {

	@Override
	public Duration deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final String duration = jp.getText();
		return Duration.parse(duration);
	}

}
