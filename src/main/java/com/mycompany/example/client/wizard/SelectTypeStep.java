package com.mycompany.example.client.wizard;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.event.SelectorSelectionEvent;
import com.mycompany.example.client.event.SelectorSelectionHandler;

public class SelectTypeStep extends AbstractStep {

	// protected Selector selector = new Selector();
	//
	// Logger log = Logger.getLogger("SelectTypeStep");
	//
	// public SelectTypeStep() {
	//
	// }
	//
	// @Override
	// public Widget getWidget() {
	// HorizontalPanel horizontalPanel = new HorizontalPanel();
	// horizontalPanel.setWidth("100%");
	//
	// Label label = new Label("select from " + previousValue + ":");
	// label.addStyleName(getElementType().getStyleLabel());
	// selector.setWidth("200px");
	// wireSelector();
	//
	// horizontalPanel.add(label);
	// horizontalPanel.add(selector);
	// horizontalPanel.setCellHorizontalAlignment(selector,
	// HasHorizontalAlignment.ALIGN_LEFT);
	//
	// return horizontalPanel;
	// }
	//
	// private void wireSelector() {
	// selector.addSelectionHandler(new SelectorSelectionHandler() {
	//
	// @Override
	// public void selection(final SelectorSelectionEvent event) {
	// if (event.getSource() == selector.getSelected()) {
	// value = selector.getSelection();
	// if (value != null) {
	// MDMEventBus.EVENT_BUS.fireEvent(new WizardAdvanceEvent(
	// SelectTypeStep.this));
	// }
	// }
	//
	// }
	// });
	//
	// }
	//
	// @Override
	// public void reset() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void addAll(final List<String> result) {
	// selector.clear();
	// selector.addAll(result);
	// }
	protected MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	protected SuggestBox suggestBox;
	Logger log = Logger.getLogger("SelectTypeStep");

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
								SelectTypeStep.this));
					}
				});

	}

	@Override
	public void reset() {
		suggestBox.setText("");
	}

	@Override
	public void addAll(List<String> result) {
		oracle.clear();
		oracle.addAll(result);
	}
}
