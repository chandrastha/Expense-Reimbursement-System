package com.ers.service;

import java.util.ArrayList;
import java.util.List;

import com.ers.dao.ReimbursementDaoImpl;
import com.ers.dao.UserDaoImpl;
import com.ers.model.Reimbursement;
import com.ers.model.User;

public class ReimbursementService {
	
	private ReimbursementDaoImpl rDao = new ReimbursementDaoImpl();
	
	public ReimbursementService() {
		super();
	}
	
	public ReimbursementService(ReimbursementDaoImpl reimbursementDaoImpl) {

	}

	public List<Reimbursement> getAllReimbursements(){
		List<Reimbursement> reimb = new ArrayList<>();
		reimb = rDao.getAllReimbursements();
		System.out.println("in reimb service get all reibmursements");
		return reimb;
	}
	
	public Reimbursement selectReimbByReimbId(int id) {
		Reimbursement re = rDao.getReimbursementById(id);
		return re;
	}
	
	public  List<Reimbursement> getReimbByUserId(int id){
		List<Reimbursement> reimb = new ArrayList<>();
		reimb = rDao.getReimbursementByEmployeeeId(id);
		return reimb;
		
	}
	
//	public static void main(String[] args) {
//		ReimbursementService rs = new ReimbursementService();
//		System.out.println(rs.getReimbByUserId(3));
//	}
	
	public void createReimb(Reimbursement newReimb) {
		rDao.addReimbursement(newReimb);
	}
	
	public void ApproveReimbById(int id, int approverId) {
		rDao.approveExpenseById(id, approverId);
	}
	
	public void denyReimbById(int id, int denyId) {
		rDao.denyExpenseById(id, denyId);
	}
	
	public void deleteReimbById(int id) {
		rDao.deleteReimbursement(id);
	}
	
}
