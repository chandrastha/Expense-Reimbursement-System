package com.ers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public MasterServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		req.getRequestDispatcher(RequestDispatcher.process(req)).forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("in master servlet post");
		
		req.getRequestDispatcher(RequestDispatcher.process(req)).forward(req, res);
	}
}
