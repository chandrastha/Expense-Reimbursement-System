package com.ers.service;

import com.ers.dao.UserDaoImpl;
import com.ers.model.User;

public class UserService {
	
	private UserDaoImpl uDao = new UserDaoImpl();
	
	public UserService() {
		super();
	}
	
	public UserService(UserDaoImpl userDaoImpl) {
		// TODO Auto-generated constructor stub
	}

	//service methods
	public User verifyLoginCredentials(String uname, String pword){
		User user = null;
		System.out.println("in user service ");
//		System.out.println("."+uname+".");
		User validUser = uDao.getUserByUsername(uname);
//		System.out.println(validUser);

		if(validUser != null && validUser.getPassword()!=null ) {
			
			if(validUser.getPassword().equals(pword)) {
				user = validUser;
		}
		else
			System.out.println("Password does not match");
		}
		else{
			System.out.println("User does not exist");
		}
		return user;
		}

//	public static void main(String[] args) {
//		UserService us = new UserService();
//		 User isVerified = us.verifyLoginCredentials("nitu1","stha123");
//		 if(isVerified.getUsername()!=null) {
//			 System.out.println("Welcome " + isVerified.getUsername());
//		 }
//		 else {
//			 System.out.println("Wrong Credentials");
//		 
//		 }	 
//		
//	}
	
	public User getUserByUserName(String username) {
		User user = new User();
		user = uDao.getUserByUsername(username);
		return user;
	}
	
	public int getUserIdNumber(User user) {
		return user.getUserid();
	}

}
