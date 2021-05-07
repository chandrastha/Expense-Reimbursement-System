package com.ers.model;

import java.sql.Date;

public class Reimbursement {
	private int re_id;
	private double re_amount;
	private Date re_submitted;
	private Date re_resolved;
	private String re_description;
	private int re_author;
	private int re_resolver;
	private int re_statusid;
	private int re_typeid;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(double re_amount, Date re_submitted, Date re_resolved, String re_description, int re_author,
			int re_resolver, int re_status_id, int re_typeid) {
		super();
		this.re_amount = re_amount;
		this.re_submitted = re_submitted;
		this.re_resolved = re_resolved;
		this.re_description = re_description;
		this.re_author = re_author;
		this.re_resolver = re_resolver;
		this.re_statusid = re_status_id;
		this.re_typeid = re_typeid;
	}
	
	public Reimbursement(int re_id, double re_amount, Date re_submitted, Date re_resolved, String re_description,
			int re_author, int re_resolver, int re_status_id, int re_typeid) {
		super();
		this.re_id = re_id;
		this.re_amount = re_amount;
		this.re_submitted = re_submitted;
		this.re_resolved = re_resolved;
		this.re_description = re_description;
		this.re_author = re_author;
		this.re_resolver = re_resolver;
		this.re_statusid = re_status_id;
		this.re_typeid = re_typeid;
	}
	public double getRe_amount() {
		return re_amount;
	}
	public void setRe_amount(double re_amount) {
		this.re_amount = re_amount;
	}
	public Date getRe_submitted() {
		return re_submitted;
	}
	public void setRe_submitted(Date re_submitted) {
		this.re_submitted = re_submitted;
	}
	public Date getRe_resolved() {
		return re_resolved;
	}
	public void setRe_resolved(Date re_resolved) {
		this.re_resolved = re_resolved;
	}
	public String getRe_description() {
		return re_description;
	}
	public void setRe_description(String re_description) {
		this.re_description = re_description;
	}
	public int getRe_author() {
		return re_author;
	}
	public void setRe_author(int re_author) {
		this.re_author = re_author;
	}
	public int getRe_resolver() {
		return re_resolver;
	}
	public void setRe_resolver(int re_resolver) {
		this.re_resolver = re_resolver;
	}
	public int getRe_status_id() {
		return re_statusid;
	}
	public void setRe_status_id(int re_status_id) {
		this.re_statusid = re_status_id;
	}
	public int getRe_typeid() {
		return re_typeid;
	}
	public void setRe_typeid(int re_typeid) {
		this.re_typeid = re_typeid;
	}
	public int getRe_id() {
		return re_id;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(re_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + re_author;
		result = prime * result + ((re_description == null) ? 0 : re_description.hashCode());
		result = prime * result + re_id;
		result = prime * result + ((re_resolved == null) ? 0 : re_resolved.hashCode());
		result = prime * result + re_resolver;
		result = prime * result + re_statusid;
		result = prime * result + ((re_submitted == null) ? 0 : re_submitted.hashCode());
		result = prime * result + re_typeid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(re_amount) != Double.doubleToLongBits(other.re_amount))
			return false;
		if (re_author != other.re_author)
			return false;
		if (re_description == null) {
			if (other.re_description != null)
				return false;
		} else if (!re_description.equals(other.re_description))
			return false;
		if (re_id != other.re_id)
			return false;
		if (re_resolved == null) {
			if (other.re_resolved != null)
				return false;
		} else if (!re_resolved.equals(other.re_resolved))
			return false;
		if (re_resolver != other.re_resolver)
			return false;
		if (re_statusid != other.re_statusid)
			return false;
		if (re_submitted == null) {
			if (other.re_submitted != null)
				return false;
		} else if (!re_submitted.equals(other.re_submitted))
			return false;
		if (re_typeid != other.re_typeid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [re_id=" + re_id + ", re_amount=" + re_amount + ", re_submitted=" + re_submitted
				+ ", re_resolved=" + re_resolved + ", re_description=" + re_description + ", re_author=" + re_author
				+ ", re_resolver=" + re_resolver + ", re_status_id=" + re_statusid + ", re_typeid=" + re_typeid + "]";
	}
	
}
