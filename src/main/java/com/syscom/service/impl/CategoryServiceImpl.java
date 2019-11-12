package com.syscom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.syscom.beans.Category;
import com.syscom.repository.CategoryRepository;
import com.syscom.service.CategoryService;

/**
 * Implémentation du contrat d'interface des services métiers des categories
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void upsert(Category category) {
		logger.info("Création de la categorie {}", category);
		Assert.notNull(category, "Category must not be null");
		categoryRepository.save(category);
	}

	@Override
	public void delete(String code) {
		logger.info("Suppression de la categorie ayant pour code {}", code);
		Assert.notNull(code, "Code must not be null");
		categoryRepository.deleteByCode(code);
	}

}
