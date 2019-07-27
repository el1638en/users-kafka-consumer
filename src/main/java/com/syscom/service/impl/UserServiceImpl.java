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
	public void create(User user) {
		logger.info("Création de l'utilisateur {}", user);
		Assert.notNull(user, "User must not be null");
		userRepository.save(user);
	}

	@Override
	public User findByLogin(String login) {
		Assert.notNull(login, "Login must not be null.");
		return userRepository.findByLogin(upperCase(login));
	}

}
