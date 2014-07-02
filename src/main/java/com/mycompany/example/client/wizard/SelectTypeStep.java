package com.mycompany.example.client.wizard;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.example.client.MDMEventBus;

public class SelectTypeStep extends AbstractStep {

	protected Selector selector = new Selector();

	Logger log = Logger.getLogger("SelectTypeStep");

	public SelectTypeStep() {

	}

	@Override
	public Widget getWidget() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");

		Label label = new Label("select from " + previousValue + ":");
		label.addStyleName(getElementType().getStyleLabel());
		selector.setWidth("200px");
		wireSelector();

		horizontalPanel.add(label);
		horizontalPanel.add(selector);
		horizontalPanel.setCellHorizontalAlignment(selector, HasHorizontalAlignment.ALIGN_LEFT);
		
		return horizontalPanel;
	}

	private void wireSelector() {
		// TODO Auto-generated method stub
		MDMEventBus.EVENT_BUS.addHandler(ClickEvent.getType(), new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				value = selector.getSelection();
				log.log(Level.INFO, "On click selector " + value);
				MDMEventBus.EVENT_BUS.fireEvent(new WizardAdvanceEvent(
						SelectTypeStep.this));
			}
		});
		
	}

	@Override
	public void setData() {
		switch (elementType) {
		case BO_TYPE:
			getBoType();
			break;
		case PRIMARY_BO_TYPE:
			getPrimaryBOType();
			break;

		default:
			break;
		}
	}

	private void getBoType() {
		List<String> result = ServiceFactory.getBOTypeBy(previousValue);
		selector.clear();
		selector.build(result);
	}

	private void getPrimaryBOType() {
		List<String> result = ServiceFactory.getPrimaryBOTypeBy(previousValue);
		selector.clear();
		selector.build(result);
	}

}
