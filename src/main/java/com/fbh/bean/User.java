package com.fbh.bean;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fbh.enums.Gender;

/**
 * 
 * @ClassName: User
 * @Description: 用户表
 * @author:冯炳航
 * @date: 2020年1月7日 上午10:44:45
 */
public class User implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = -1683293756503330143L;

	public User() {
		super();
	}

	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	// 用户id
	private Integer id;
	// 用户名称
	@NotBlank(message = "用户名不能为空")
	private String username;
	// 密码
	@NotBlank(message = "密码不能为空")
	private String password;
	// 昵称
	private String nickname;
	// 生日
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	// 性别
	private Gender gender;
	// 能否使用 0：正常 1：禁用
	private Integer locked;
	// 注册时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	// 最后修改时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated;
	// 身份 0：普通用户 1：管理员
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked + ", created=" + created
				+ ", updated=" + updated + ", role=" + role + "]";
	}

}
