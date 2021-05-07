package com.ers.model;

public class ReimbursementType {
	
	private int re_typeid;
	private String re_type;
	
	public ReimbursementType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbursementType(String re_type) {
		super();
		this.re_type = re_type;
	}

	public ReimbursementType(int re_typeid, String re_type) {
		super();
		this.re_typeid = re_typeid;
		this.re_type = re_type;
	}

	public int getRe_typeid() {
		return re_typeid;
	}

	public void setRe_typeid(int re_typeid) {
		this.re_typeid = re_typeid;
	}

	public String getRe_type() {
		return re_type;
	}

	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((re_type == null) ? 0 : re_type.hashCode());
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
		ReimbursementType other = (ReimbursementType) obj;
		if (re_type == null) {
			if (other.re_type != null)
				return false;
		} else if (!re_type.equals(other.re_type))
			return false;
		if (re_typeid != other.re_typeid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementType [re_typeid=" + re_typeid + ", re_type=" + re_type + "]";
	}
	
}
