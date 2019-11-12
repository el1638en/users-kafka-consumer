package com.syscom.config;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonDeserializerWithJTM<T> extends JsonDeserializer<T> {
	public JsonDeserializerWithJTM() {
		super();
		objectMapper.registerModule(new JavaTimeModule());
		setRemoveTypeHeaders(false);
		this.typeMapper.addTrustedPackages("*");

	}
}