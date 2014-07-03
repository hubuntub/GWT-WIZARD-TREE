package com.mycompany.example.client.wizard;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.example.client.MDMEventBus;

public class SelectValueStep extends AbstractStep {
	protected MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	protected SuggestBox suggestBox;
	Logger log = Logger.getLogger("SelectValueStep");

	@Override
	public Widget getWidget() {
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");

		Label label = new Label("select from " + previousValue);
		suggestBox = new SuggestBox(oracle);
		suggestBox.setAutoSelectEnabled(true);
		suggestBox.setWidth("200px");
		wireSearchInput();
		horizontalPanel.add(label);
		horizontalPanel.add(suggestBox);
		horizontalPanel.setCellHorizontalAlignment(suggestBox,
				HasHorizontalAlignment.ALIGN_LEFT);
		return horizontalPanel;
	}

	public void wireSearchInput() {
		suggestBox
				.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

					@Override
					public void onSelection(SelectionEvent<Suggestion> event) {
						Suggestion suggestion = event.getSelectedItem();
						value = suggestion.getReplacementString();
						MDMEventBus.EVENT_BUS.fireEvent(new WizardAdvanceEvent(
								SelectValueStep.this));
					}
				});

	}

	@Override
	public void setData() {
		switch (elementType) {
		case DOC_TYPE:
			getAllDocTypes();
			break;
		case PRIMARY_BO:
			getPrimaryBO();
			break;
		case BO:
			getBO();
			break;

		default:
			break;
		}

	}

	private void getPrimaryBO() {
		List<String> result = ServiceFactory.getPrimaryBO(previousValue);
		oracle.clear();
		oracle.addAll(result);
	}

	private void getBO() {
		List<String> result = ServiceFactory.getBOBy(previousValue);
		oracle.clear();
		oracle.addAll(result);

	}

	private void getAllDocTypes() {
		List<String> result = ServiceFactory.getAllDocTypes();
		oracle.clear();
		oracle.addAll(result);

	}

	@Override
	public void reset() {
		suggestBox.setText("");
	}

}
