package com.fbh.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Channel
 * @Description: 栏目表
 * @author:冯炳航
 * @date: 2020年1月7日 上午10:44:35
 */
public class Channel implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = -3714482079344570582L;

	public Channel() {
		super();
	}

	public Channel(Integer id, String name, String description, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}

	// 频道id
	private Integer id;
	// 栏目名称
	private String name;
	// 描述
	private String description;
	// 图标
	private String icon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
