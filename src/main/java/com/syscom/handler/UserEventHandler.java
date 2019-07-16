package com.syscom.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.UserEvent;
import com.syscom.mapper.UserEventMapper;
import com.syscom.service.UserService;

@Component
public class UserEventHandler extends AbstractEventHandler<UserEvent> {

	@Autowired
	private UserEventMapper userEventMapper;

	@Autowired
	private UserService userService;

	@Override
	@KafkaListener(topics = "${user.topic.name}", groupId = "${user.group.id}", containerFactory = "userEventListenerContainerFactory")
	public void process(UserEvent userEvent) {
		super.process(userEvent);
	}

	@Override
	public void processEvent(UserEvent userEvent) {
		userService.create(userEventMapper.eventToBean(userEvent));
	}
}
