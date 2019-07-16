package com.syscom.dao;

import org.springframework.data.repository.CrudRepository;

import com.syscom.beans.Category;

/**
 * 
 * Repository pour effectuer les CRUD des categories {@link Category}
 *
 */
public interface CategoryDao extends CrudRepository<Category, Long> {

}
