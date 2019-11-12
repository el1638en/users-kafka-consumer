package com.syscom.service.impl;

import static org.apache.commons.lang3.StringUtils.upperCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.syscom.beans.User;
import com.syscom.repository.UserRepository;
import com.syscom.service.UserService;

/**
 * Implémentation du contrat d'interface des services métiers des utilisateurs
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void upsert(User user) {
		logger.info("Création/Modification de l'utilisateur {}", user);
		Assert.notNull(user, "User must not be null");
		User findUser = userRepository.findByLogin(user.getLogin());
		if (findUser == null) {
			userRepository.save(user);
			logger.info("Création réussie de l'utilisateur {}", user);
		} else {
			findUser.setBirthDay(user.getBirthDay());
			findUser.setName(user.getName());
			findUser.setFirstName(user.getFirstName());
			findUser.setPassword(user.getPassword());
			userRepository.save(findUser);
			logger.info("Modification réussie de l'utilisateur {}", user);

		}

	}

	@Override
	public User findByLogin(String login) {
		Assert.notNull(login, "Login must not be null.");
		return userRepository.findByLogin(upperCase(login));
	}

	@Override
	public void delete(String login) {
		Assert.notNull(login, "Login must not be null.");
		userRepository.deleteByLogin(login);
	}

}
