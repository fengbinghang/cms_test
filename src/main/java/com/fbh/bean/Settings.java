package com.fbh.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Settings
 * @Description: 配置表
 * @author:冯炳航
 * @date: 2020年1月7日 下午1:19:06
 */
public class Settings implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = 733173838933096131L;

	public Settings() {
		super();
	}

	public Settings(Integer id, String site_domain, String site_name, Integer article_list_size, Integer slide_size,
			String admin_username, String admin_password) {
		super();
		this.id = id;
		this.site_domain = site_domain;
		this.site_name = site_name;
		this.article_list_size = article_list_size;
		this.slide_size = slide_size;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
	}

	// 配置id
	private Integer id;
	// 网站域名
	private String site_domain;
	// 网站名称
	private String site_name;
	// 文章列表页显示数量
	private Integer article_list_size;
	// 首页幻灯片数量
	private Integer slide_size;
	// 管理员账号
	private String admin_username;
	// 管理员密码
	private String admin_password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSite_domain() {
		return site_domain;
	}

	public void setSite_domain(String site_domain) {
		this.site_domain = site_domain;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public Integer getArticle_list_size() {
		return article_list_size;
	}

	public void setArticle_list_size(Integer article_list_size) {
		this.article_list_size = article_list_size;
	}

	public Integer getSlide_size() {
		return slide_size;
	}

	public void setSlide_size(Integer slide_size) {
		this.slide_size = slide_size;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
}
