package com.mycompany.example.client;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

public class RolloverImage extends Image {
    ImageResource outImageResource;

    public RolloverImage(final ImageResource outImageResource, final ImageResource overImageResource) {
        super(outImageResource);
        this.outImageResource = outImageResource;

        addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                setResource(overImageResource);

            }
        });
        addMouseOutHandler(new MouseOutHandler() {

            @Override
            public void onMouseOut(MouseOutEvent event) {
                setResource(outImageResource);
            }
        });
    }

    public void reset() {
        setResource(outImageResource);
    }
}