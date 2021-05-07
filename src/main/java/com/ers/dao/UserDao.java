package com.ers.dao;

import java.util.List;

import com.ers.model.User;

public interface UserDao {
	
	List<User> getAllUsers() throws ClassNotFoundException;
	
	public User getUserById(int userid) throws ClassNotFoundException;
	
	User getUserByUsername(String username) throws ClassNotFoundException;
	
	void insertUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(int userid);
	

}
