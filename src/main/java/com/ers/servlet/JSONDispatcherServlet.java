package com.ers.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.controller.ErsController;
import com.ers.controller.ReimbursementController;
import com.ers.model.Reimbursement;
import com.ers.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDispatcherServlet {
	private static Logger log = Logger.getLogger(JSONDispatcherServlet.class);
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		case "/ProjectOne/getsessionuser.json":
			log.info("At getSession user dispatch");
			ErsController.getSessionUser(req, res);
			break;
			
		case "/ProjectOne/manager/getreimbursements.json":
			log.info("At manager getreimbursement dispatch");
			switch(req.getMethod()) {
			case "GET":
				ErsController.getAllReimbRequests(req, res);
				break;
				
			case "POST":
				ErsController.getAllReimbRequests(req, res);
				break;
			}
			break;
			
		case "/ProjectOne/employee/getreimbursements.json":
			log.info("At employee getreimbursement dispatch");
			ErsController.getReimbById(req, res);
			break;
			
		case "/ProjectOne/employee/newreimbursement.json":
			log.info("At employee new reimbursement dispatch");
			ErsController.addNewReimb(req,res);
			break;
			
		case "/ProjectOne/manager/approve.json":
			log.info("At manager approve dispatch");
			ErsController.approveReimb(req,res);
			break;
			
		case "/ProjectOne/manager/deny.json":
			log.info("At manager deny dispatch");
			ErsController.denyReimb(req,res);
			break;
			
		

			default:
				res.getWriter().write(new ObjectMapper().writeValueAsString(new User()));
		}
	}
	
}
