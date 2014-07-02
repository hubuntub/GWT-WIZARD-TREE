package com.mycompany.example.client.wizard;

import java.util.ArrayList;
import java.util.List;

public class ServiceFactory {

	public static List<String> getPrimaryBOTypeBy(String previousValue) {
		List<String> list = new ArrayList<String>();
		list.add("primaryBoType 1");
		list.add("primaryBoType 2");
		list.add("primaryBoType 3");
		list.add("primaryBoType 4");
		list.add("primaryBoType 5");
		return list;
	}

	public static List<String> getBOTypeBy(String previousValue) {
		List<String> list = new ArrayList<String>();
		list.add("BoType 1");
		list.add("BoType 2");
		list.add("BoType 3");
		list.add("BoType 4");
		return list;
	}

	public static List<String> getBOBy(String previousValue) {
		List<String> list = new ArrayList<String>();
		list.add("Bo 1");
		list.add("Bo 2");
		list.add("Bo 3");
		list.add("Bo 4");
		return list;
	}

	public static List<String> getAllDocTypes() {
		List<String> list = new ArrayList<String>();
		list.add("DocType 1");
		list.add("DocType 2");
		list.add("DocType 3");
		list.add("DocType 4");
		return list;
	}

	public static List<String> getPrimaryBO(String previousValue) {
		List<String> list = new ArrayList<String>();
		list.add("PrimaryBO 1");
		list.add("PrimaryBO 2");
		list.add("PrimaryBO 3");
		list.add("PrimaryBO 4");
		return list;
	}

	public static boolean hasPrimaryBOType(String value) {
		// TODO Auto-generated method stub
		return true;
	}

}
