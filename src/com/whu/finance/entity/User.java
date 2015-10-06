package com.whu.finance.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = -171638517483317372L;
	private String username;//�û���
    private String pwd;//����
    private String role;//��ɫ
     
     @Id
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
     
}
