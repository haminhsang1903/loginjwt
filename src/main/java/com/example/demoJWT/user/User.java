package com.example.demoJWT.user;

/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright 2019 {@author Loda} (https://loda.me). This project is licensed
 * under the MIT license.
 *
 * @since 4/30/2019 Github: https://github.com/loda-kun
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String password;

	@Column(nullable = false, unique = true)
	private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User() {
		super();
	}

}
