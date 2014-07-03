package com.mycompany.example.client.wizard;

import com.google.gwt.event.shared.GwtEvent;

public class WizardAdvanceEvent  extends GwtEvent<WizardAdvanceHandler> {
	public static final Type<WizardAdvanceHandler> TYPE = new Type<WizardAdvanceHandler>();

	private AbstractStep step;
	public WizardAdvanceEvent(AbstractStep step){
		this.step = step;
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<WizardAdvanceHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(WizardAdvanceHandler handler) {
		handler.advance(this);
	}
	public AbstractStep getAbstractStep() {
		return step;
	}

}