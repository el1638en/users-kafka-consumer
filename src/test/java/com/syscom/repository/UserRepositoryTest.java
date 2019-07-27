package com.syscom.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.syscom.AbstractTest;
import com.syscom.beans.User;
import com.syscom.repository.UserRepository;

public class UserRepositoryTest extends AbstractTest {

	@Autowired
	private UserRepository userRepository;

	private User user;

	// Donnees de test pour les utilisateurs
	private static final String LOGIN = "LOGIN";
	private static final String NAME = "NAME";
	private static final String FIRST_NAME = "FIRST_NAME";
	private static final String PASSWORD = "PASSWORD";
	private static final LocalDate BIRTH_DAY = LocalDate.now().minusDays(1);

	@Before
	public void setUp() {
		user = User.builder().name(NAME).firstName(FIRST_NAME).login(LOGIN).password(PASSWORD).birthDay(BIRTH_DAY)
				.build();
		user = userRepository.save(user);
	}

	@Test
	public void testFindByWrongLogin() throws Exception {
		// GIVEN
		String wrongLogin = "WRONG_LOGIN";

		// WHEN
		User user = userRepository.findByLogin(wrongLogin);

		// THEN
		assertThat(user).isNull();
	}

	@Test
	public void testFindByLogin() throws Exception {
		// GIVEN

		// WHEN
		User userResult = userRepository.findByLogin(LOGIN);

		// THEN
		assertThat(userResult).isEqualTo(this.user);
	}

	@Test
	public void testDeleByLogin() throws Exception {
		// GIVEN

		// WHEN
		userRepository.deleteByLogin(LOGIN);

		// THEN
		assertThat(userRepository.findByLogin(LOGIN)).isNull();
	}
}
