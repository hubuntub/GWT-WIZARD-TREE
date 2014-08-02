package com.mycompany.example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.mycompany.example.client.wizard.Wizard;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyGwtApp implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
 
  private final Messages messages = GWT.create(Messages.class);

  public void onModuleLoad() {

	    // Add the nameField and sendButton to the RootPanel
	    // Use RootPanel.get() to get the entire body element
	    Wizard wizard = new Wizard();
	    wizard.start();
	    wizard.setWidth("100%");
	    wizard.setHeight("100%");
	    
	    RootPanel.get("nameFieldContainer").add(wizard);

	  }
}
