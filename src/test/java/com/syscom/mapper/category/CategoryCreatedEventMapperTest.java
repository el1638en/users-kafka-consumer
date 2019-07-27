package com.syscom.mapper.category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.syscom.beans.Category;
import com.syscom.event.category.CategoryCreatedEvent;

@RunWith(SpringRunner.class)
@Import(value = { CategoryCreatedEventMapperImpl.class })
public class CategoryCreatedEventMapperTest {

	@Autowired
	private CategoryCreatedEventMapper categoryCreatedEventMapper;

	@Test
	public void testEventToBean() throws Exception {
		// GIVEN

		CategoryCreatedEvent categoryCreatedEvent = new CategoryCreatedEvent();
		categoryCreatedEvent.setCode("code");
		categoryCreatedEvent.setLibelle("libelle");

		// WHEN
		Category category = categoryCreatedEventMapper.eventToBean(categoryCreatedEvent);

		// THEN
		assertThat(category.getCode()).isEqualTo(categoryCreatedEvent.getCode());
		assertThat(category.getLibelle()).isEqualTo(categoryCreatedEvent.getLibelle());

	}
}
