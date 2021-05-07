package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.model.User;
import com.ers.servlet.RequestDispatcher;

public class UserDaoImpl implements UserDao{
	private static Logger log = Logger.getLogger(RequestDispatcher.class);
	
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String SELECT_USER_BY_ID = "select * from users where userid = ?";
	private static final String SELECT_USER_BY_USERNAME = "select * from users where username = ?";

	public UserDaoImpl() {
		super();
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
			}
//			System.out.println(userList);
		}
		catch(SQLException e) {
			e.printStackTrace();
			log.info("Users were not retrieved");
		}
		return userList;
	}
	
	
	@Override
	public User getUserById(int userid) {
		List<User> userList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);
			ps.setInt(1,userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			log.info("User was not retrieved by id");
		}
		return userList.get(0);
	}

	
	@Override
	public User getUserByUsername(String username) {
		List<User> userList = new ArrayList<>();
		System.out.println("in dao impl");
		try(Connection con = DatabaseConnection.getDbConnection()){
			System.out.println("connection " + con);
			PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_USERNAME);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			log.info("User was not retrieved by username");
			return null;
		}
		return userList.get(0);
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userid) {
		// TODO Auto-generated method stub
		
	}

}
