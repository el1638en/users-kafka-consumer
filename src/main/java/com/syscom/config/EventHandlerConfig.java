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

import com.syscom.event.category.CategoryCreatedEvent;
import com.syscom.event.category.CategoryDeletedEvent;
import com.syscom.event.user.UserCreatedEvent;
import com.syscom.event.user.UserDeletedEvent;

@Configuration
public class EventHandlerConfig {

	@Value(value = "${spring.kafka.consumer.bootstrap-servers:localhost:9092}")
	private String consumerBootStrapServers;

	@Value(value = "${spring.kafka.consumer.group.user.created:users-created}")
	private String userCreatedGroupId;

	@Value(value = "${spring.kafka.consumer.group.user.deleted:user-deleted}")
	private String userDeletedGroupId;

	@Value(value = "${spring.kafka.consumer.group.category.created:category-created}")
	private String categoryCreatedGroupId;

	@Value(value = "${spring.kafka.consumer.group.category.deleted:category-deleted}")
	private String categoryDeletedGroupId;

	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootStrapServers);
		return properties;
	}

	@Bean("userCreatedEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> userCreatedEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, userCreatedGroupId);
		ConsumerFactory<String, UserCreatedEvent> userCreatedEventConsumerFactory = new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(), new JsonDeserializerWithJTM<UserCreatedEvent>());

		ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userCreatedEventConsumerFactory);
		return factory;
	}

	@Bean("userDeletedEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, UserDeletedEvent> userDeletedEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, userDeletedGroupId);
		ConsumerFactory<String, UserDeletedEvent> userDeletedEventConsumerFactory = new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(), new JsonDeserializerWithJTM<UserDeletedEvent>());

		ConcurrentKafkaListenerContainerFactory<String, UserDeletedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userDeletedEventConsumerFactory);
		return factory;
	}

	@Bean("categoryCreatedEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, CategoryCreatedEvent> categoryCreatedEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, categoryCreatedGroupId);
		ConsumerFactory<String, CategoryCreatedEvent> categoryCreatedEventConsumerFactory = new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(), new JsonDeserializerWithJTM<CategoryCreatedEvent>());

		ConcurrentKafkaListenerContainerFactory<String, CategoryCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(categoryCreatedEventConsumerFactory);
		return factory;
	}

	@Bean("categoryDeletedEventListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, CategoryDeletedEvent> categoryDeletedEventListenerContainerFactory() {
		Map<String, Object> properties = getProperties();
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, userDeletedGroupId);
		ConsumerFactory<String, CategoryDeletedEvent> categoryDeletedEventConsumerFactory = new DefaultKafkaConsumerFactory<>(
				properties, new StringDeserializer(), new JsonDeserializerWithJTM<CategoryDeletedEvent>());

		ConcurrentKafkaListenerContainerFactory<String, CategoryDeletedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(categoryDeletedEventConsumerFactory);
		return factory;
	}

}
