package com.syscom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.syscom.beans.Category;
import com.syscom.dao.CategoryDao;
import com.syscom.service.CategoryService;

/**
 * Implémentation du contrat d'interface des services métiers des utilisateurs
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public void create(Category category) {
		logger.info("Création de la categorie {}", category);
		Assert.notNull(category, "Category must not be null");
		categoryDao.save(category);
	}

}
