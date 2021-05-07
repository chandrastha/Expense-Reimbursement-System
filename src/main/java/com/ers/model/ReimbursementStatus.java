package com.ers.model;

public class ReimbursementStatus {

	private int re_statusid;
	private String re_status;
	
	public ReimbursementStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbursementStatus(int re_statusid, String re_status) {
		super();
		this.re_statusid = re_statusid;
		this.re_status = re_status;
	}

	public ReimbursementStatus(String re_status) {
		super();
		this.re_status = re_status;
	}

	public int getRe_statusid() {
		return re_statusid;
	}

	public void setRe_statusid(int re_statusid) {
		this.re_statusid = re_statusid;
	}

	public String getRe_status() {
		return re_status;
	}

	public void setRe_status(String re_status) {
		this.re_status = re_status;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((re_status == null) ? 0 : re_status.hashCode());
		result = prime * result + re_statusid;
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (re_status == null) {
			if (other.re_status != null)
				return false;
		} else if (!re_status.equals(other.re_status))
			return false;
		if (re_statusid != other.re_statusid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [re_statusid=" + re_statusid + ", re_status=" + re_status + "]";
	}
	
}
