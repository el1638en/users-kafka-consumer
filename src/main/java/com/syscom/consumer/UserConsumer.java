package com.syscom.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.UserEvent;
import com.syscom.mapper.UserEventMapper;
import com.syscom.service.UserService;

@Component
public class UserConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(UserConsumer.class);
	
	@Autowired
	private UserEventMapper userEventMapper;
	
	@Autowired
	private UserService userService;
	
    @KafkaListener(topics = "${user.topic.name}", containerFactory = "userKafkaListenerContainerFactory")
    public void userListener(UserEvent userEvent) {
    	logger.info("Reception d'un event kafka utilisateur : {}", userEvent);
        userService.create(userEventMapper.eventToBean(userEvent));
    }
}
