package com.ad.constant;

public enum Constant {

	XSD("xsd"),
	XML("xml"),
	FOLDERPATH("src/main/resources/");

	// declaring private variable for getting values
	private String value;

	// getter method
	public String getValue() {
		return value;
	}

	// enum constructor - cannot be public or protected
	private Constant(String value) {
		this.value = value;
	}

}
