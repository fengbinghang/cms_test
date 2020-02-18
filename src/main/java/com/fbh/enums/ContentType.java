package com.fbh.enums;

public enum ContentType {
	HTML, IMAGE;

	//返回序数(0,1)
	public Integer getOrdinal() {
		return this.ordinal();
	}
}
