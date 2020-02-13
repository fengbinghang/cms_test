package com.fbh.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Category
 * @Description: 分类表
 * @author:冯炳航
 * @date: 2020年1月7日 下午1:03:04
 */
public class Category implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 序列化id
	 */
	private static final long serialVersionUID = 6820119193123866150L;

	public Category() {
		super();
	}

	public Category(Integer id, String name, Channel ch) {
		super();
		this.id = id;
		this.name = name;
		this.ch = ch;
	}

	// 分类id
	private Integer id;
	// 分类名称
	private String name;
	// 栏目对象(栏目id,栏目名称)
	private Channel ch;

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

	public Channel getCh() {
		return ch;
	}

	public void setCh(Channel ch) {
		this.ch = ch;
	}
}
