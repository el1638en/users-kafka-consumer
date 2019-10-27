package com.syscom.mapper.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.syscom.beans.User;
import com.syscom.event.user.UserUpsertEvent;

@RunWith(SpringRunner.class)
@Import(value = { UserUpsertEventMapperImpl.class })
public class UserCreatedEventMapperTest {

	@Autowired
	private UserUpsertEventMapper userUpsertEventMapper;

	@Test
	public void testEventToBean() throws Exception {
		// GIVEN
		UserUpsertEvent userCreatedEvent = new UserUpsertEvent();
		userCreatedEvent.setBirthDay(LocalDate.now());
		userCreatedEvent.setName("name");
		userCreatedEvent.setFirstName("firstName");
		userCreatedEvent.setLogin("login");
		userCreatedEvent.setPassword("password");

		// WHEN
		User user = userUpsertEventMapper.eventToBean(userCreatedEvent);

		// THEN
		assertThat(user.getBirthDay()).isEqualTo(userCreatedEvent.getBirthDay());
		assertThat(user.getName()).isEqualTo(userCreatedEvent.getName());
		assertThat(user.getFirstName()).isEqualTo(userCreatedEvent.getFirstName());
		assertThat(user.getLogin()).isEqualTo(userCreatedEvent.getLogin());
		assertThat(user.getPassword()).isEqualTo(userCreatedEvent.getPassword());
	}
}
