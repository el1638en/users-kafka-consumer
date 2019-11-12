package com.syscom.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.syscom.AbstractTest;
import com.syscom.beans.User;
import com.syscom.event.user.UserUpsertEvent;
import com.syscom.repository.UserRepository;

public class UserServiceTest extends AbstractTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Captor
	private ArgumentCaptor<String> keyCaptor;

	@Captor
	private ArgumentCaptor<UserUpsertEvent> userEventCaptor;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private User user;

	@Before
	public void setUp() {
		user = User.builder().firstName("FIRST_NAME").id(1L).name("NAME").password("PASSWORD").login("LOGIN")
				.birthDay(LocalDate.now()).build();
		Mockito.when(userRepository.save(user)).thenReturn(user);
	}

	@Test
	public void whenCreateNullUserThenThrowException() throws Exception {
		// GIVEN
		exceptionRule.expect(IllegalArgumentException.class);

		// WHEN
		userService.upsert(null);

		// THEN
		verifyZeroInteractions(userRepository);
	}

	@Test
	public void testCreateUser() throws Exception {
		// GIVEN

		// WHEN
		userService.upsert(user);

		// THEN
		verify(userRepository, times(1)).save(user);
	}

}
