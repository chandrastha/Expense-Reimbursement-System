package com.ers.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.ReimbursementDaoImpl;
import com.ers.model.Reimbursement;
import com.ers.service.ReimbursementService;

public class ReimbursementController {

	static ReimbursementService reimbServ = new ReimbursementService(new ReimbursementDaoImpl());

//	public static List<Reimbursement> getAllRequests(HttpServletRequest req , HttpServletResponse res) {		
//		List<Reimbursement> reimb = new ArrayList<>();
//		System.out.println("in reimbursement controller");
//		reimb = reimbServ.getAllReimbursements();
//		return reimb;
//	}

}


