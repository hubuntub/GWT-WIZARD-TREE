package com.mycompany.example.client.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.mycompany.example.client.GreetingService;
import com.mycompany.example.client.GreetingServiceAsync;
import com.mycompany.example.client.MDMAsyncCallback;

public class ServiceFactory {
	protected static List<String> cacheDocType = new ArrayList<>();
	protected static Map<String, List<String>> cachePrimaryBOType = new HashMap<>();
	private final static GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public static void getPrimaryBOTypeBy(final String previousValue,
			final MDMAsyncCallback<List<String>> callback) {
		if (cachePrimaryBOType.containsKey(previousValue)) {
            GWT.log("getPrimaryBOType cached (" + previousValue + " ) => result: "
                    + cachePrimaryBOType.get(previousValue));
            callback.doSuccess(cachePrimaryBOType.get(previousValue));
        } else {
		greetingService.getPrimaryBOTypeBy(previousValue, new MDMAsyncCallback<List<String>>() {

			@Override
			public void doSuccess(List<String> result) {
				  List<String> cached = new ArrayList<>();
                  cached.addAll(result);
                  cachePrimaryBOType.put(previousValue, cached);
                  GWT.log("getPrimaryBOType first (" + previousValue + " ) => result: "
                          + cachePrimaryBOType.get(previousValue));
                  callback.doSuccess(cached);
			}
		});
        }

	}

	public static void getBOTypeBy(String previousValue,
			final MDMAsyncCallback<List<String>> callback) {
		greetingService.getBOTypeBy(previousValue, callback);
	}

	public static void getBOBy(String previousValue,
			final MDMAsyncCallback<List<String>> callback) {
		greetingService.getBOBy(previousValue, callback);
	}

	public static void getAllDocTypes(
			final MDMAsyncCallback<List<String>> callback) {
		if (cacheDocType.isEmpty()) {
			greetingService
					.getAllDocTypes(new MDMAsyncCallback<List<String>>() {

						@Override
						public void doSuccess(List<String> result) {
							cacheDocType.addAll(result);
							callback.doSuccess(cacheDocType);
						}
					});
		} else {
			callback.doSuccess(cacheDocType);
		}
	}

	public static void getPrimaryBO(String previousValue,
			final MDMAsyncCallback<List<String>> callback) {
		greetingService.getPrimaryBO(previousValue, callback);
	}

	public static void hasPrimaryBOType(String previousValue,
			final MDMAsyncCallback<Boolean> callback) {
		greetingService.hasPrimaryBOType(previousValue, callback);
	}

}
