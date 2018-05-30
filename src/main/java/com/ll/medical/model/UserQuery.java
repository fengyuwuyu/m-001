package com.ll.medical.model;

import com.ll.medical.model.base.BaseQuery;

public class UserQuery extends BaseQuery {

	private String username;
	private Integer roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserQuery [username=" + username + ", roleId=" + roleId + ", toString()=" + super.toString() + "]";
	}



}
