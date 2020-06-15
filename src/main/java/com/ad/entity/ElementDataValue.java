package com.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "elementdatavalue")
public class ElementDataValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "version", nullable = false)
	private String painversion;

	@Column(name = "element", nullable = false)
	private String element;

	@Column(name = "value", nullable = false)
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPainversion() {
		return painversion;
	}

	public void setPainversion(String painversion) {
		this.painversion = painversion;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ElementDataValue [id=" + id + ", painversion=" + painversion + ", element=" + element + ", value="
				+ value + "]";
	}

}
