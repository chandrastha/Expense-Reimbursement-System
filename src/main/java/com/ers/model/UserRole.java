package com.ers.model;

public class UserRole {
	
	private int roleid;
	private String role;
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRole(String role) {
		super();
		this.role = role;
	}

	public UserRole(int roleid, String role) {
		super();
		this.roleid = roleid;
		this.role = role;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleid;
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
		UserRole other = (UserRole) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleid != other.roleid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [roleid=" + roleid + ", role=" + role + "]";
	}
	
}
