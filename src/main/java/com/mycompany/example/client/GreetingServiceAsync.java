package com.mycompany.example.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GreetingServiceAsync
{

  /**
   * GWT-RPC service  asynchronous (client-side) interface
   * @see GreetingService
   */
  void greetServer( java.lang.String p0, AsyncCallback<java.lang.String> callback );


  /**
   * Utility class to get the RPC Async interface from client-side code
   */
  public static final class Util
  {
    private static GreetingServiceAsync instance;

    public static final GreetingServiceAsync getInstance()
    {
      if ( instance == null )
      {
        instance = (GreetingServiceAsync) GWT.create( GreetingService.class );
        ServiceDefTarget target = (ServiceDefTarget) instance;
        target.setServiceEntryPoint( GWT.getModuleBaseURL() + "GreetingService" );
      }
      return instance; 	
    }

    private Util()
    {
      // Utility class should not be instanciated
    }
  }


void getPrimaryBOTypeBy(String previousValue,
		AsyncCallback<List<String>> callback);


void getBOTypeBy(String previousValue, AsyncCallback<List<String>> callback);


void getBOBy(String previousValue, AsyncCallback<List<String>> callback);


void getAllDocTypes(AsyncCallback<List<String>> callback);


void getPrimaryBO(String previousValue, AsyncCallback<List<String>> callback);


void hasPrimaryBOType(String value, AsyncCallback<Boolean> callback);
}
