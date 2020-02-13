package com.fbh.vo;

import com.fbh.bean.User;

public class UserVO extends User {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 2710123665016723001L;

	public UserVO() {
		super();
	}

	private Integer ordinal;

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

}
