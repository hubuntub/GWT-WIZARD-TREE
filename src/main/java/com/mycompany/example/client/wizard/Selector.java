package com.mycompany.example.client.wizard;

import java.util.List;

import com.mycompany.example.client.MDMEventBus;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Selector extends Composite implements ClickHandler,
		HasClickHandlers {

	VerticalPanel mainPanel = new VerticalPanel();
	private String selection;

	public Selector() {

		mainPanel.setWidth("100%");
		mainPanel.setHeight("100%");
		mainPanel.setStyleName("selector");
		initWidget(mainPanel);
	}

	public void clear(){
		mainPanel.clear();
	}
	public void build(List<String> data) {
		for (String element : data) {
			Button b = new Button(element);
			b.addClickHandler(this);
			b.setStyleName(INSTANCE.selectorButton());
			mainPanel.add(b);
		}
	}

	public String getSelection() {
		return selection;
	}

	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return addDomHandler(handler, ChangeEvent.getType());
	}

	@Override
	public void onClick(ClickEvent event) {

		if (event.getSource() instanceof Button) {
			Button b = (Button) event.getSource();
			selection = b.getText();
			MDMEventBus.EVENT_BUS.fireEvent(event);
		}
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addHandler(handler, ClickEvent.getType());
	}
}
