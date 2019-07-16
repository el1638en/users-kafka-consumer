package com.syscom.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.syscom.event.CategoryEvent;
import com.syscom.mapper.CategoryEventMapper;
import com.syscom.service.CategoryService;

@Component
public class CategoryEventHandler extends AbstractEventHandler<CategoryEvent> {

	@Autowired
	private CategoryEventMapper categoryEventMapper;

	@Autowired
	private CategoryService categoryService;

	@Override
	@KafkaListener(topics = "${category.topic.name}", groupId = "${category.group.id}", containerFactory = "categoryEventListenerContainerFactory")
	public void process(CategoryEvent event) {
		super.process(event);
	}

	@Override
	public void processEvent(CategoryEvent categoryEvent) {
		categoryService.create(categoryEventMapper.eventToBean(categoryEvent));
	}
}
