package com.syscom.handler.category;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.category.CategoryDeletedEvent;
import com.syscom.handler.AbstractEventHandler;
import com.syscom.service.CategoryService;

@Component
public class CategoryDeletedEventHandler extends AbstractEventHandler<CategoryDeletedEvent> {

	@Autowired
	private CategoryService categoryService;

	@Override
	@KafkaListener(topics = "${spring.kafka.consumer.topic.category.deleted:category-deleted}", groupId = "${spring.kafka.consumer.group.category.deleted:category-deleted-group-id}", containerFactory = "categoryDeletedEventListenerContainerFactory")
	public void handleEvent(CategoryDeletedEvent categoryDeletedEvent) {
		super.handleEvent(categoryDeletedEvent);
	}

	@Override
	public void processEvent(CategoryDeletedEvent categoryDeletedEvent) {
		if (categoryDeletedEvent != null && StringUtils.isNotEmpty(categoryDeletedEvent.getCode())) {
			categoryService.delete(categoryDeletedEvent.getCode());
		}
	}
}