package com.syscom.service;

import com.syscom.beans.User;

public interface UserService {

	/**
	 * Création ou modification d'un utilisateur.
	 * 
	 */
	void upsert(User user);

	/**
	 * Rechercher un utilisateur à partir d'un login.
	 * 
	 * @param login login de l'utilisateur.
	 * @return l'utilisateur.
	 */
	User findByLogin(String login);

	/**
	 * Suppression d'un utilisateur à partir de son login
	 * 
	 * @param login login de l'utilisateur
	 */
	void delete(String login);

}
