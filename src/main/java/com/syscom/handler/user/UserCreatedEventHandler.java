package com.syscom.handler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.user.UserCreatedEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.mapper.user.UserCreatedEventMapper;
import com.syscom.service.UserService;

@Component
public class UserCreatedEventHandler extends AbstractEventHandler<UserCreatedEvent> {

	@Autowired
	private UserCreatedEventMapper userCreatedEventMapper;

	@Autowired
	private UserService userService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.user.created:user-created}", groupId = "${spring.kafka.consumer.group.user.created:users-created-group-id}", containerFactory = "userCreatedEventListenerContainerFactory")
	public void process(UserCreatedEvent userEvent) {
		super.process(userEvent);
	}

	@Override
	public void processEvent(UserCreatedEvent userEvent) {
		userService.create(userCreatedEventMapper.eventToBean(userEvent));
	}
}
