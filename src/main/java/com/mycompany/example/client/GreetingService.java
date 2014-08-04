package com.mycompany.example.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;

	List<String> getPrimaryBOTypeBy(String previousValue);

	List<String> getBOTypeBy(String previousValue);

	List<String> getBOBy(String previousValue);

	List<String> getAllDocTypes();

	List<String> getPrimaryBO(String previousValue);

	boolean hasPrimaryBOType(String value);
}
