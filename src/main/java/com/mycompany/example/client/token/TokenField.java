package com.mycompany.example.client.token;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.Resources;
import com.mycompany.example.client.wizard.ElementType;
import com.mycompany.example.client.wizard.INSTANCE;
public class TokenField extends Composite {
	static Resources resources = GWT.create(Resources.class);
	protected HorizontalPanel tokenField = new HorizontalPanel();
	private static final String HEIGHT = "40px";
	protected ElementType color;
	protected String content;
	public TokenField(String content, ElementType color){
		this.color = color;
		this.content = content;
		initWidget(tokenField);
		build();
	}
	private void build() {
		Label label = new Label(content);
		label.setStyleName(INSTANCE.tokenLabel());
		PushButton deleteImage = new PushButton(new Image(resources.cancelButton()));
		deleteImage.setSize("16px", "16px");
		deleteImage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				MDMEventBus.EVENT_BUS.fireEvent(new TokenClickEvent(TokenField.this));
			}
		});
        tokenField.add(label);
        tokenField.add(deleteImage);
        tokenField.addStyleName(color.getStyleToken());
		tokenField.setHeight(HEIGHT);
	}

	public ElementType getColor() {
		return color;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenField other = (TokenField) obj;
		if (color != other.color)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	
}
