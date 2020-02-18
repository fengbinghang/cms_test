package com.fbh.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: Content
 * @Description: 图片类
 * @author:冯炳航
 * @date: 2020年2月18日 上午10:38:18
 */
public class Content implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 6689905937521935206L;

	public Content() {
		super();
	}

	public Content(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	private Integer id;
	private String name;
	private String content;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
