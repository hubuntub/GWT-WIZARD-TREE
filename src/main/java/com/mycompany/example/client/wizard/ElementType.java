package com.mycompany.example.client.wizard;


public enum ElementType {
	DOC_TYPE(INSTANCE.tokenBlue(), INSTANCE.labelBlue()), 
	BO_TYPE(INSTANCE.tokenPurple(), INSTANCE.labelPurple()), 
	BO(INSTANCE.tokenRed(), INSTANCE.labelRed()), 
	PRIMARY_BO_TYPE(INSTANCE.tokenOrange(), INSTANCE.labelOrange()), 
	PRIMARY_BO(INSTANCE.tokenGreen(), INSTANCE.labelGreen());

	private String styleToken;
	private String styleLabel;

	private ElementType(String styleToken, String styleLabel) {
		this.styleToken = styleToken;
		this.styleLabel = styleLabel;
	}

	public String getStyleToken() {
		return styleToken;
	}

	public String getStyleLabel() {
		return styleLabel;
	}
}
