package com.mycompany.example.client.wizard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.event.SelectorSelectionEvent;
import com.mycompany.example.client.event.SelectorSelectionHandler;

public class Selector extends Composite implements ClickHandler, Focusable {
    private static List<Button> existingButtons = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            existingButtons.add(new Button());
        }
    }
    VerticalPanel mainPanel = new VerticalPanel();
    private String selection;
    private List<String> data = new ArrayList<>();

    private Map<String, Button> buttons = new LinkedHashMap<>();
    private int indexSelectedButton = -1;
    private Button button;
    private int index = 0;
    private Button selected;

    public Selector() {

        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        mainPanel.setSpacing(2);
        initWidget(mainPanel);
        existingButtons.get(0).setFocus(true);
        mainPanel.add(existingButtons.get(0));
    }

    public void clear() {
        mainPanel.clear();
        existingButtons.get(0).setText("");
        mainPanel.add(existingButtons.get(0));
    }

    private void build() {
        for (String element : data) {
            Button b = existingButtons.get(index);

            b.setText(element);
            b.addClickHandler(this);
            b.setStyleName("selectorButton");
            buttons.put(element, b);
            if (index != 0) {
                mainPanel.add(b);
            }

            index++;
        }
    }

    public void addAll(final List<String> data) {
        setData(data);
        if (!this.data.isEmpty()) {
            build();
        } else {
            existingButtons.get(0).setText("Not found");
        }
    }

    private void setData(final List<String> data) {
        this.data = data;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public String getSelection() {
        return selection;
    }

    @Override
    public void onClick(final ClickEvent event) {

        if (event.getSource() instanceof Button) {
            selected = (Button) event.getSource();
            selection = selected.getText();
            MDMEventBus.EVENT_BUS.fireEvent(new SelectorSelectionEvent(selected, selection));
        }
    }

    @Override
    public int getTabIndex() {
        return indexSelectedButton;
    }

    @Override
    public void setAccessKey(final char key) {
        button.setAccessKey(key);
    }

    @Override
    public void setTabIndex(final int index) {
        button.setTabIndex(index);

    }

    @Override
    public void setFocus(final boolean focused) {
        existingButtons.get(0).setFocus(true);
    }

    public Button getSelected() {
        return selected;
    }

    public HandlerRegistration addSelectionHandler(final SelectorSelectionHandler handler) {
        return MDMEventBus.EVENT_BUS.addHandler(SelectorSelectionEvent.TYPE, handler);
    }
}
