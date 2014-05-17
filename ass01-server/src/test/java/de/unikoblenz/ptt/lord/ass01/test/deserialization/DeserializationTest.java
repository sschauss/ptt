package de.unikoblenz.ptt.lord.ass01.test.deserialization;

import io.dropwizard.jackson.Jackson;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationTest {

	protected static final ObjectMapper OBJECT_MAPPER = Jackson.newObjectMapper();

	public DeserializationTest() {
		OBJECT_MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

}
