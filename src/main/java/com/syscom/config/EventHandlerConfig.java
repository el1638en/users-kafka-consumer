package com.syscom.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.syscom.event.CategoryEvent;
import com.syscom.event.UserEvent;

@Configuration
public class EventHandlerConfig {

	@Value(value = "${spring.kafka.consumer.bootstrap-servers}")
	private String consumerBootStrapServers;

	@Value(value = "${user.group.id}")
	private String userGroupId;

	@Value(value = "${category.group.id}")
	private String categoryGroupId;

	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootStrapServers);
		return properties;
	}

	@Bean("userEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
		ConsumerFactory<String, UserEvent> userEventConsumerFactory = new DefaultKafkaConsumerFactory<>(properties,
				new StringDeserializer(), new JsonDeserializerWithJTM<UserEvent>());

		ConcurrentKafkaListenerContainerFactory<String, UserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userEventConsumerFactory);
		return factory;
	}

	@Bean("categoryEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, CategoryEvent> categoryEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, categoryGroupId);
		ConsumerFactory<String, CategoryEvent> categoryEventConsumerFactory = new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(), new JsonDeserializerWithJTM<CategoryEvent>());

		ConcurrentKafkaListenerContainerFactory<String, CategoryEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(categoryEventConsumerFactory);
		return factory;
	}
}
