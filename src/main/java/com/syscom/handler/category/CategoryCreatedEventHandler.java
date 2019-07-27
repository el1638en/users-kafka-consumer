package com.syscom.handler.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.category.CategoryCreatedEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.mapper.category.CategoryCreatedEventMapper;
import com.syscom.service.CategoryService;

@Component
public class CategoryCreatedEventHandler extends AbstractEventHandler<CategoryCreatedEvent> {

	@Autowired
	private CategoryCreatedEventMapper categoryCreatedEventMapper;

	@Autowired
	private CategoryService categoryService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.category.created:category-created}", groupId = "${spring.kafka.consumer.group.category.created:category-created-group-id}", containerFactory = "categoryCreatedEventListenerContainerFactory")
	public void process(CategoryCreatedEvent event) {
		super.process(event);
	}

	@Override
	public void processEvent(CategoryCreatedEvent categoryCreatedEvent) {
		categoryService.create(categoryCreatedEventMapper.eventToBean(categoryCreatedEvent));
	}
}
