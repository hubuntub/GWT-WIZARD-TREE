package com.mycompany.example.client.wizard;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.token.TokensLayout;

public class Wizard extends Composite{
	private boolean ret = false;
	protected VerticalPanel mainPanel = new VerticalPanel();
	protected TokensLayout tokensLayout = new TokensLayout();
	Logger log = Logger.getLogger("Wizard");
	
	
	public Wizard(){
		mainPanel.setHeight("100%");
		tokensLayout.setWidth("100%");
		mainPanel.setSpacing(10);
		
		initWidget(mainPanel);
		MDMEventBus.EVENT_BUS.addHandler(WizardAdvanceEvent.TYPE,
				new WizardAdvanceHandler() {

					@Override
					public void advance(WizardAdvanceEvent event) {
						AbstractStep step = event.getAbstractStep();
						handleStepAndAdvance(step);
					}
				});
		MDMEventBus.EVENT_BUS.addHandler(WizardCancelEvent.TYPE,
				new WizardCancelHandler() {

					@Override
					public void cancel(WizardCancelEvent event) {
						AbstractStep step = event.getAbstractStep();
						handleStepAndCancel(step);

					}

					
				});
	}
	private void handleStepAndCancel(AbstractStep step) {
		tokensLayout.removeToken(step);
		ElementType type = step.getElementType();
		String previousValue = step.getPreviousValue();
		log.log(Level.INFO, "cancel: type" + type);
		log.log(Level.INFO, "cancel: previousValue" + previousValue);
		switch (type) {
		case DOC_TYPE:
			start();
			break;
		
		case BO:
			createStep(ElementType.BO_TYPE, new SelectTypeStep(), previousValue);
			break;
		case PRIMARY_BO:
			createStep(ElementType.PRIMARY_BO_TYPE, new SelectTypeStep(),
					previousValue);
			break;
		default:
			break;
			
		}
	}
	protected void handleStepAndAdvance(AbstractStep step) {
		ElementType type = step.getElementType();
		String value = step.getValue();
		String previousValue = step.getPreviousValue();
		switch (type) {
		case DOC_TYPE:
			addToken(step);
			createStep(ElementType.BO_TYPE, new SelectTypeStep(), value);
			break;
		case BO_TYPE:
			if (step instanceof SelectTypeStep) {
				createStep(ElementType.BO, new SelectValueStep(), value);
			} 
			break;
		case BO:
			addToken(step);
			if (hasPrimaryBoType(previousValue)) {
				createStep(ElementType.PRIMARY_BO_TYPE, new SelectTypeStep(),
						value);
			}
			break;
		case PRIMARY_BO_TYPE:
			if (step instanceof SelectTypeStep) {
				createStep(ElementType.PRIMARY_BO, new SelectValueStep(), value);
			} 
			break;
		case PRIMARY_BO:
			addToken(step);
			updateLayout(); 
			break;
		default:
			break;
		}
	}
	
	
	private boolean hasPrimaryBoType(String value) {
		log.log(Level.INFO, "HasPrimaryBOTYPEBy " + value);
		// TODO Call services to check
		return ServiceFactory.hasPrimaryBOType(value);
	}

	private void addToken(AbstractStep step) {
		// TODO Add a token to the token layout
		// Put most of the code in the token layout
		tokensLayout.createToken(step);
		
	}

	// Start the wizard (first step)
	public void start() {
		createStep(ElementType.DOC_TYPE, new SelectValueStep(), "doctype");
		
	}

	private void updateLayout(){
		mainPanel.clear();
		mainPanel.add(tokensLayout);
		
	}
	private void createStep(ElementType docType, AbstractStep step, String value) {
		step.setElementType(docType);
		step.setPreviousValue(value);
		step.setData();
		updateLayout();
		mainPanel.add(step.getWidget());
	}

}
