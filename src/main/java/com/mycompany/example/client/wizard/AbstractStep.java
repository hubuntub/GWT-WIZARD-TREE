package com.mycompany.example.client.wizard;

import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractStep {

	ElementType elementType;
	String value;
	String previousValue;

	public AbstractStep() {

	}

	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public abstract Widget getWidget();

	public abstract void setData();
	public abstract void reset();
}
