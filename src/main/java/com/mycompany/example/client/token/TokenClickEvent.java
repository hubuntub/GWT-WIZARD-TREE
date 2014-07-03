package com.mycompany.example.client.token;

import com.google.gwt.event.shared.GwtEvent;

public class TokenClickEvent extends GwtEvent<TokenClickHandler> {
	public static final Type<TokenClickHandler> TYPE = new Type<TokenClickHandler>();

	private TokenField tokenField;
	public TokenClickEvent(TokenField tokenField){
		this.setTokenField(tokenField);
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<TokenClickHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TokenClickHandler handler) {
		handler.tokenClick(this);
	}
	public TokenField getTokenField() {
		return tokenField;
	}
	public void setTokenField(TokenField tokenField) {
		this.tokenField = tokenField;
	}


}