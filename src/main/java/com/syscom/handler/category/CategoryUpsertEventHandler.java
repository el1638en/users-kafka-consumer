package com.syscom.handler.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.category.CategoryUpsertEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.mapper.category.CategoryUpsertEventMapper;
import com.syscom.service.CategoryService;

@Component
public class CategoryUpsertEventHandler extends AbstractEventHandler<CategoryUpsertEvent> {

	@Autowired
	private CategoryUpsertEventMapper categoryCreatedEventMapper;

	@Autowired
	private CategoryService categoryService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.category.upsert:category-upsert}", groupId = "${spring.kafka.consumer.group.category.upsert:category-upsert-group-id}", containerFactory = "categoryUpsertEventListenerContainerFactory")
	public void handleEvent(CategoryUpsertEvent event) {
		super.handleEvent(event);
	}

	@Override
	public void processEvent(CategoryUpsertEvent categoryCreatedEvent) {
		categoryService.upsert(categoryCreatedEventMapper.eventToBean(categoryCreatedEvent));
	}
}
