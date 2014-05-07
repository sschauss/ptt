package de.unikoblenz.ptt.lord.ass01.core.jackson;

import java.io.IOException;

import io.dropwizard.util.Duration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DurationSerializer extends JsonSerializer<Duration> {

	@Override
	public void serialize(final Duration value, final JsonGenerator jgen, final SerializerProvider provider) throws IOException, JsonProcessingException {
		String duration = value.toString();
		jgen.writeString(duration);
	}

}
