package com.syscom.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.syscom.beans.Category;

/**
 * 
 * Repository pour effectuer les CRUD des categories {@link Category}
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

	/**
	 * Supprimer une categorie à partir de son code.
	 * 
	 * @param code code catégorie.
	 */
	@Modifying
	@Query(name = "deleteByCode", value = "DELETE FROM Category c WHERE c.code =:code")
	void deleteByCode(@Param("code") String code);

}
