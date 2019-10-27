package com.syscom.handler.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.user.UserUpsertEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.mapper.user.UserUpsertEventMapper;
import com.syscom.service.UserService;

@Component
public class UserUpsertEventHandler extends AbstractEventHandler<UserUpsertEvent> {

	@Autowired
	private UserUpsertEventMapper userUpsertEventMapper;

	@Autowired
	private UserService userService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.user.upsert:users-upsert}", groupId = "${spring.kafka.consumer.group.user.upsert:user-upsert-group-id}", containerFactory = "userUpsertEventListenerContainerFactory")
	public void handleEvent(UserUpsertEvent userEvent) {
		super.handleEvent(userEvent);
	}

	@Override
	public void processEvent(UserUpsertEvent userEvent) {
		userService.upsert(userUpsertEventMapper.eventToBean(userEvent));
	}
}
