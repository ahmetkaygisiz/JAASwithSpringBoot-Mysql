package com.akua.loginmodule;

import java.io.Serializable;
import java.security.Principal;

public class UsernamePrincipal implements Principal, Serializable {

	private static final long serialVersionUID = 1L;
	private String username;

	public UsernamePrincipal(String username) {
		this.username = username;
	}

	public String getName() {
		return username;
	}

	public String toString() {
		return "Principal [name=" + getName() + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}