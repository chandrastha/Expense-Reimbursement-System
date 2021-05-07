package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.ers.model.Reimbursement;



public class ReimbursementDaoImpl implements ReimbursementDao{
	
	public static long millisecondTime = System.currentTimeMillis();
	public static Date currentDate = new java.sql.Date(millisecondTime);
	
	private static final String SELECT_ALL_REIMBURSEMENTS = "select * from reimb";
	private static final String SELECT_REIMBURSEMENT_BY_REID = "select * from reimb where re_id = ?";
	private static final String SELECT_REIMBURSEMENT_BY_USER_ID = "select * from reimb where re_author = ?";
	private static final String ADD_REIMBURSEMENT = "insert into reimb (re_amount,re_submitted,re_description,re_author,re_statusid,re_typeid) values (?,?,?,?,?,?)";
	private static final String DELETE_REIMBURSEMENT  = "delete from reimb where re_id = ?";
	private static final String UPDATE_REIMBURSEMENT = "update reimb set re_amount = ?, re_submitted= ?, re_resolved = ?, re_description= ?, re_author = ?, re_resolver = ? , re_statusid = ?, re_typeid = ? where re_id = ?";
	private static final String SELECT_REIMBURSEMENT_BY_TYPE_ID = "select * from reimb where re_typeid = ?";
	private static final String APPROVE_REIMBURSEMENT = "update reimb set re_statusid = 2 , re_resolved = ? , re_resolver = ? where re_id = ?";
	private static final String DENY_REIMBURSEMENT = "update reimb set re_statusid = 3 , re_resolved = ? , re_resolver = ? where re_id = ?";

	
	public ReimbursementDaoImpl() {
		super();
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		
		List<Reimbursement> reList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_REIMBURSEMENTS);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reList.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reList;
		
	}

	@Override
	public Reimbursement getReimbursementById(int re_id) {
		List<Reimbursement> reList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_REIMBURSEMENT_BY_REID);
			ps.setInt(1,re_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				reList.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reList.get(0);
	}
	
	@Override
	public List<Reimbursement> getReimbursementByType(int re_typeid) {
		List<Reimbursement> reList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_REIMBURSEMENT_BY_TYPE_ID);
			ps.setInt(1,re_typeid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				reList.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reList;
		
	}
	
	@Override
	public List<Reimbursement>  getReimbursementByEmployeeeId(int re_author) {
		List<Reimbursement> reList = new ArrayList<>();
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(SELECT_REIMBURSEMENT_BY_USER_ID);
			ps.setInt(1,re_author);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				reList.add(new Reimbursement(rs.getInt(1),rs.getDouble(2),rs.getDate(3),rs.getDate(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return reList;
	}

	
//	public static void main(String[] args) {
//		ReimbursementDaoImpl rDaoImpl = new ReimbursementDaoImpl();
//		System.out.println(rDaoImpl.getReimbursementByEmployeeeId(3));
//	}
	@Override
	public boolean addReimbursement(Reimbursement re) {
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(ADD_REIMBURSEMENT);
			
			ps.setDouble(1,re.getRe_amount());
			ps.setDate(2, re.getRe_submitted());
//			ps.setDate(3, re.getRe_resolved());
			ps.setString(3, re.getRe_description());
			ps.setInt(4, re.getRe_author());
//			ps.setInt(6, re.getRe_resolver());
			ps.setInt(5, re.getRe_status_id());
			ps.setInt(6, re.getRe_typeid());
			
			ps.execute();
		}catch(SQLException exception) {
			System.out.println("Expense was not added successfully");
			exception.printStackTrace();
			return false;
		}
			System.out.println("Expense was successfully added.");
			return true;
	}


	@Override
	public boolean deleteReimbursement(int re_id) {
		boolean isDeleted = false;
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(DELETE_REIMBURSEMENT);
			ps.setInt(1,re_id);
			isDeleted  = ps.executeUpdate()>0;
		}
	catch(SQLException e) {
		e.printStackTrace();
	}
		return isDeleted;
	}

	@Override
	public boolean updateReimbursement(Reimbursement re, int re_id) {
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps = con.prepareStatement(UPDATE_REIMBURSEMENT);
			ps.setDouble(1,re.getRe_amount());
			ps.setDate(2, re.getRe_submitted());
			ps.setDate(3, re.getRe_resolved());
			ps.setString(4, re.getRe_description());
			ps.setInt(5, re.getRe_author());
			ps.setInt(6, re.getRe_resolver());
			ps.setInt(7, re.getRe_status_id());
			ps.setInt(8, re.getRe_typeid());
			ps.setInt(9, re_id);
			
			int updated = ps.executeUpdate();
			System.out.println("Number of Reimb updated " + updated);
			if(updated>0) {
				return true;
			}
	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void approveExpenseById(int re_id, int re_resolver) {
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps= con.prepareStatement(APPROVE_REIMBURSEMENT);
			ps.setDate(1, currentDate);
			ps.setInt(2, re_resolver);
			ps.setInt(3, re_id);
	
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void denyExpenseById(int re_id, int re_resolver) {
		try(Connection con = DatabaseConnection.getDbConnection()){
			PreparedStatement ps= con.prepareStatement(DENY_REIMBURSEMENT);
			ps.setDate(1, currentDate);
			ps.setInt(2, re_resolver);
			ps.setInt(3, re_id);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
