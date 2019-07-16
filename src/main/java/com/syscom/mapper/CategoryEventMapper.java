package com.syscom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.syscom.beans.Category;
import com.syscom.event.CategoryEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CategoryEventMapper {

	@Mappings({ 
		@Mapping(target = "id", ignore = true)
	})
	Category eventToBean(CategoryEvent categoryEvent);

}
