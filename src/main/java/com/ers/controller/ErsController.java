package com.ers.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ers.dao.ReimbursementDaoImpl;
import com.ers.dao.UserDaoImpl;
import com.ers.model.Reimbursement;
import com.ers.model.User;
import com.ers.service.ReimbursementService;
import com.ers.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErsController {
	
	static UserService userServ = new UserService(new UserDaoImpl());
	static ReimbursementService reimbServ = new ReimbursementService(new ReimbursementDaoImpl());
	public static long millisecondTime = System.currentTimeMillis();
	public static Date currentDate = new java.sql.Date(millisecondTime);
	private static Logger log = Logger.getLogger(ErsController.class);
	
	public static String login(HttpServletRequest req) {
		System.out.println("in Ers controller login ");
		if(!req.getMethod().equals("POST")) {
			return "html/index.html";
		}
		
		User user = userServ.verifyLoginCredentials(req.getParameter("username"),req.getParameter( "pword"));
		
		if(user == null) {
			log.info("Username and password doesn't match.");
			return "wrongcreds.change";
			
		}
		else {
			req.getSession().setAttribute("currentUser", user);
			if(user.getRoleid()==1) {
				log.info("Username and password matches.");
				return "html/employeehome.html";
			}

			else
				return "html/managerhome.html";
		}
	}
	
	public static String logout(HttpServletRequest req){
		System.out.println("in ERS controller logout function");
		req.getRequestDispatcher("html/index.html");
        HttpSession session=req.getSession();  
        session.invalidate();  
		return "html/index.change";
	}
	
	public static void getSessionUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User user = (User) req.getSession().getAttribute("currentUser");
		res.getWriter().write(new ObjectMapper().writeValueAsString(user));
		System.out.println("Current user ** is " + user.getFirstname());
	}
	
	public static void getAllReimbRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		res.setContentType("application/json");
		HttpSession session = req.getSession(false);
		User loggedInUser = (User) session.getAttribute("currentUser");
//		System.out.println("Logged in user-----------------------------------" + loggedInUser);
		if(loggedInUser!=null) {
			List<Reimbursement> allReimbRequests = reimbServ.getAllReimbursements();
			ObjectMapper om = new ObjectMapper();
			res.getWriter().write(om.writeValueAsString(allReimbRequests));
		}
	}
	
	public static void getReimbById(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
//		System.out.println("abcde");
		res.setContentType("application/json");
		HttpSession session = req.getSession(false);
		User loggedInUser = (User) session.getAttribute("currentUser");
//		System.out.println("Logged in user" + loggedInUser);
		if(loggedInUser!=null) {
			List<Reimbursement> allReimbRequests = reimbServ.getReimbByUserId(loggedInUser.getUserid());
//			System.out.println("Inside getReimbById for employees , in controller "+ allReimbRequests);
			ObjectMapper om = new ObjectMapper();
			res.getWriter().write(om.writeValueAsString(allReimbRequests));
		}
		
	}

	public static void addNewReimb(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("inside add new Reimbursement request");
		 HttpSession session = req.getSession(false);
		User loggedInUser = (User) session.getAttribute("currentUser");
		if(loggedInUser!=null) {
			ObjectMapper om = new ObjectMapper();
			Reimbursement newReimbRequest = om.readValue(req.getReader(),Reimbursement.class);
			newReimbRequest.setRe_author(loggedInUser.getUserid());
			newReimbRequest.setRe_status_id(1);
			newReimbRequest.setRe_submitted(currentDate);
			newReimbRequest.setRe_resolver(0);
			newReimbRequest.setRe_resolved(null);
			reimbServ.createReimb(newReimbRequest);
		}
		
	}

	public static void approveReimb(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("inside approve reimb request");
		HttpSession session = req.getSession(false);
		User loggedInUser = (User) session.getAttribute("currentUser");
//		System.out.println("Logged in user is : " + loggedInUser.getUserid());
		ObjectMapper om = new ObjectMapper();
		Reimbursement updatedReimb = om.readValue(req.getReader(), Reimbursement.class);
		Reimbursement actualReimb = reimbServ.selectReimbByReimbId(updatedReimb.getRe_id());
		actualReimb.setRe_resolved(currentDate);
		actualReimb.setRe_resolver(loggedInUser.getUserid());
//		actualReimb.setRe_status_id(3);
		reimbServ.ApproveReimbById(updatedReimb.getRe_id(),loggedInUser.getUserid());
		
		
	}

	public static void denyReimb(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("inside deny reimb function");
		HttpSession session = req.getSession(false);
		User loggedInUser = (User) session.getAttribute("currentUser");
//		System.out.println("Logged in user is : " + loggedInUser);
		ObjectMapper om = new ObjectMapper();
		Reimbursement updatedReimb = om.readValue(req.getReader(), Reimbursement.class);
		Reimbursement actualReimb = reimbServ.selectReimbByReimbId(updatedReimb.getRe_id());
		actualReimb.setRe_resolved(currentDate);
		reimbServ.denyReimbById(updatedReimb.getRe_id(),loggedInUser.getUserid());
		
		
	}

}
