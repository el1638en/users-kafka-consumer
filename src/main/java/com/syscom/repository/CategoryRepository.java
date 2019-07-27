package com.syscom.repository;

import org.springframework.data.repository.CrudRepository;

import com.syscom.beans.Category;

/**
 * 
 * Repository pour effectuer les CRUD des categories {@link Category}
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
