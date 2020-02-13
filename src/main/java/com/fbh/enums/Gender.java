package com.fbh.enums;

public enum Gender {
	// 定义枚举常量
	MAN("男"), WOMAN("女");

	// 封装私有属性
	private String displayName;

	// 上述的属性的get方法
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	// 有参的构造方法
	private Gender(String displayName) {
		this.displayName = displayName;
	}

	// 提供一个获得常量的方法
	public String getName() {
		return this.name();
	}

	// 提供一个获得下标的方法
	public int getOrdinal() {
		// 返回枚举常量的序数(它在枚举声明中的位置,其中初始常量序数为零)
		return this.ordinal();
	}

}
