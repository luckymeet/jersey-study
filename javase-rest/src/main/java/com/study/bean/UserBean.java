package com.study.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User com.study.bean
 * 
 * @author waylau.com
 * 2015年3月3日
 */
@XmlRootElement
public class UserBean {

	private int userId;
	private String name;
	private int age;

	public UserBean() {
	}

	public UserBean(int userId, String name, int age) {
		this.userId = userId;
		this.name = name;
		this.age = age;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
