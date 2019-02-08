package com.syscom.service;

import com.syscom.beans.User;

public interface UserService {

	/**
	 * Création d'un nouvel utilisateur.
	 * 
	 */
	void create(User user);

	/**
	 * Rechercher un utilisateur à partir d'un login.
	 * 
	 * @param login login de l'utilisateur.
	 * @return l'utilisateur.
	 */
	User findByLogin(String login);


}
