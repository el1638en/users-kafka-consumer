package com.syscom.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.syscom.beans.User;
import com.syscom.event.user.UserCreatedEvent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserCreatedEventMapper {

	@Mapping(target = "id", ignore = true)
	User eventToBean(UserCreatedEvent userEvent);

}
