package com.syscom.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.syscom.event.UserEvent;

@Configuration
public class KafkaUserConsumerConfig {

	@Value(value = "${kafka.serverAddress}")
	private String kafkaServerAddress;

	public ConsumerFactory<String, UserEvent> userConsumerFactory() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "users");
		return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(),
				new JsonDeserializer<>(UserEvent.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserEvent> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}
}
