package com.syscom.service;

import com.syscom.beans.Category;

public interface CategoryService {

	/**
	 * Création/Modification d'une categorie.
	 * 
	 */
	void upsert(Category category);

	/**
	 * Suppression d'une catégorie
	 * 
	 * @param code
	 */
	void delete(String code);
}
