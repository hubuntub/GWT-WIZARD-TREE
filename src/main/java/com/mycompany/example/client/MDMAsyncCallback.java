package com.mycompany.example.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class MDMAsyncCallback<T> implements AsyncCallback<T> {

	@Override
	public final void onSuccess(final T result) {
		doSuccess(result);
	}

	public abstract void doSuccess(T result);

	@Override
	public final void onFailure(final Throwable caught) {
		doFailure(caught);
	}

	public final void doFailure(final Throwable caught) {
		if (handleException(caught)) {
			return;
		}

	}

	protected boolean handleException(final Throwable caught) {
		return false;
	};
}