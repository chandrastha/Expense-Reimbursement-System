package com.ers.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ers.controller.ErsController;

public class RequestDispatcher {
	private static Logger log = Logger.getLogger(RequestDispatcher.class);

	public static String process(HttpServletRequest req) throws IOException {
		
		
		switch(req.getRequestURI()) {
		case "/ProjectOne/login.change" :
			log.info("in Login.change dispatcher");
			return ErsController.login(req);
			
		case "/ProjectOne/manager/logout.change":
			log.info("in employee logout dispatcher");
			  return ErsController.logout(req);
			
		case "/ProjectOne/employee/logout.change":
			log.info("in employee logout dispatcher");
			  return ErsController.logout(req);
			
		case "/ProjectOne/error.change":
			
			
		default:
			System.out.println("in default");
			return "html/unsuccessfullogin.html";
		}
		
	}
}
