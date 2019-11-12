package com.syscom.handler.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.user.UserDeletedEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.service.UserService;

@Component
public class UserDeletedEventHandler extends AbstractEventHandler<UserDeletedEvent> {

	@Autowired
	private UserService userService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.user.deleted:user-deleted}", groupId = "${spring.kafka.consumer.group.user.deleted:user-deleted-group-id}", containerFactory = "userDeletedEventListenerContainerFactory")
	public void handleEvent(UserDeletedEvent userDeletedEvent) {
		super.handleEvent(userDeletedEvent);
	}

	@Override
	public void processEvent(UserDeletedEvent userDeletedEvent) {
		if (userDeletedEvent != null && StringUtils.isNotEmpty(userDeletedEvent.getLogin())) {
			userService.delete(userDeletedEvent.getLogin());
		}
	}
}
