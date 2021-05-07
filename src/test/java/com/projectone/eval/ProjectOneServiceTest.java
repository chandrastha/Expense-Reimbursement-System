package com.projectone.eval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ers.dao.ReimbursementDaoImpl;
import com.ers.dao.UserDaoImpl;
import com.ers.model.Reimbursement;
import com.ers.model.User;
import com.ers.service.ReimbursementService;
import com.ers.service.UserService;

public class ProjectOneServiceTest {

	@Mock
	ReimbursementDaoImpl fakeReimbDaoImpl = new ReimbursementDaoImpl();
	Reimbursement fakeReimb = new Reimbursement();
	UserDaoImpl fakeUserDaoImpl = new UserDaoImpl();
	ReimbursementService fakeReimbService = new ReimbursementService();
	UserService fakeUserService = new UserService();
	User fakeUser = new User();
	User fakeUser2 = new User();
	List<User> userList = new ArrayList<>();

	@Test
	public void testGetUserByUsername() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		User testFakeUser = fakeUserDaoImpl.getUserByUsername("kumar1");
		assertEquals(fakeUser, testFakeUser);
	}
	
	
	@Test
	public void testGetUserById() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		fakeUser2 = new User(5,"Nitu1","stha123","Nitu","basnet","nitu@yahoo.com",1);
		User testFakeUser = fakeUserDaoImpl.getUserById(4);
		assertEquals(fakeUser,testFakeUser);
	}
	
	@Test
	public void testVerifyLoginCredentials() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		User TestFakeUser = fakeUserService.verifyLoginCredentials("kumar1", "stha123");
		assertEquals(fakeUser,TestFakeUser);
	}
	
	@Test
	public void testVerifyLoginCredentialsFalse() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		User TestFakeUser = fakeUserService.verifyLoginCredentials("kumar1", "stha13"); //Used wrong password
		assertEquals(null,TestFakeUser);
	}

	@Test
	public void TestServiceMethodGetUserByUserName() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		User testFakeUser = fakeUserService.getUserByUserName("kumar1");
		assertEquals(fakeUser,testFakeUser);
	}
	
	@Test
	public void TestgetUserIdNumber() {
		fakeUser = new User(4,"kumar1","stha123","kumar","basnet","kumar@yahoo.com",1);
		int id = fakeUserService.getUserIdNumber(fakeUser);
		assertEquals(4,id);
	}

	
	
}
