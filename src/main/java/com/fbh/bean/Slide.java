package com.fbh.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Slide
 * @Description: 广告表
 * @author:冯炳航
 * @date: 2020年1月7日 上午10:46:44
 */
public class Slide implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = -1900232638120278545L;

	public Slide() {
		super();
	}

	public Slide(Integer id, String title, String picture, String url) {
		super();
		this.id = id;
		this.title = title;
		this.picture = picture;
		this.url = url;
	}

	// 图片id
	private Integer id;
	// 标题
	private String title;
	// 幻灯片图片
	private String picture;
	// 跳转地址
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
