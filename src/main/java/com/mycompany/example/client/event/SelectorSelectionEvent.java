package com.mycompany.example.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

public class SelectorSelectionEvent extends GwtEvent<SelectorSelectionHandler> {
    public static final Type<SelectorSelectionHandler> TYPE = new Type<SelectorSelectionHandler>();

    protected final String content;
    protected final Widget source;

    public SelectorSelectionEvent(final Widget source, final String content) {
        this.content = content;
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    @Override
    public Widget getSource() {
        return source;
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<SelectorSelectionHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final SelectorSelectionHandler handler) {
        handler.selection(this);
    }

}
