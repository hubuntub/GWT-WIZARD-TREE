package com.mycompany.example.server;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.example.client.GreetingService;
import com.mycompany.example.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }

@Override
public List<String> getPrimaryBOTypeBy(String previousValue) {
	List<String> list = new ArrayList<String>();
	list.add("primaryBoType 1");
	return list;
}

@Override
public List<String> getBOTypeBy(String previousValue) {
	List<String> list = new ArrayList<String>();
	list.add("BoType 1");
	list.add("BoType 2");
	list.add("BoType 3");
	list.add("BoType 4");
	return list;
}

@Override
public List<String> getBOBy(String previousValue) {
	List<String> list = new ArrayList<String>();
	list.add("Bo 1");
	list.add("Bo 2");
	list.add("Bo 3");
	list.add("Bo 4");
	return list;
}

@Override
public List<String> getAllDocTypes() {
	List<String> list = new ArrayList<String>();
	list.add("DocType 1");
	list.add("DocType 2");
	list.add("DocType 3");
	list.add("DocType 4");
	return list;
}

@Override
public List<String> getPrimaryBO(String previousValue) {
	List<String> list = new ArrayList<String>();
	list.add("PrimaryBO 1");
	list.add("PrimaryBO 2");
	list.add("PrimaryBO 3");
	list.add("PrimaryBO 4");
	return list;
}

@Override
public boolean hasPrimaryBOType(String value) {
	// TODO Auto-generated method stub
	return true;
}
}
