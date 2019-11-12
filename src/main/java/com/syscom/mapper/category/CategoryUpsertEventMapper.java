package com.syscom.mapper.category;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.syscom.beans.Category;
import com.syscom.event.category.CategoryUpsertEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryUpsertEventMapper {

	@Mapping(target = "id", ignore = true)
	Category eventToBean(CategoryUpsertEvent categoryUpsertEvent);

}
