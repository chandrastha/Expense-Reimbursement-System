package com.ers.dao;

import java.util.List;

import com.ers.model.Reimbursement;

public interface ReimbursementDao {
 
		public List <Reimbursement> getAllReimbursements(); //passed
		
		public Reimbursement getReimbursementById(int re_id); //passed
		
		public List<Reimbursement> getReimbursementByEmployeeeId(int re_author); //passed
		
		public List<Reimbursement> getReimbursementByType(int re_typeid); //passed
		
		public boolean addReimbursement(Reimbursement re); //passed
		
		public boolean deleteReimbursement(int re_id); //passed
		
		public boolean updateReimbursement(Reimbursement re, int re_id); //passed
		
		public void approveExpenseById(int re_id, int re_resolver); //unImplemented
		
		public void denyExpenseById(int re_id,  int re_resolver); //unImplemented
}
