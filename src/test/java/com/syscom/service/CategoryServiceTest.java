package com.syscom.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.syscom.AbstractTest;
import com.syscom.beans.Category;
import com.syscom.repository.CategoryRepository;

public class CategoryServiceTest extends AbstractTest {

	@MockBean
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private Category category;

	@Before
	public void setUp() {
		category = Category.builder().code("CODE").id(1L).libelle("LIBELLE").build();
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
	}

	@Test
	public void whenCreateNullCategoryThenThrowException() {
		// GIVEN
		exceptionRule.expect(IllegalArgumentException.class);

		// WHEN
		categoryService.upsert(null);

		// THEN
		verifyZeroInteractions(categoryRepository);
	}

	@Test
	public void testCreateCategory() {
		// GIVEN
		category.setCode("NEW_CODE");

		// WHEN
		categoryService.upsert(category);

		// THEN
		verify(categoryRepository, times(1)).save(category);
	}

	@Test
	public void testDeleteCategory() {
		// GIVEN
		categoryRepository.save(category);

		// WHEN
		categoryService.delete("CODE");

		// THEN
		verify(categoryRepository, times(1)).delete(category);
	}
}
