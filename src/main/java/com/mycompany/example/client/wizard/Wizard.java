package com.mycompany.example.client.wizard;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mycompany.example.client.MDMAsyncCallback;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.token.TokensLayout;

public class Wizard extends Composite {
	private boolean ret = false;
	protected VerticalPanel mainPanel = new VerticalPanel();
	protected TokensLayout tokensLayout = new TokensLayout();
	Logger log = Logger.getLogger("Wizard");

	public Wizard() {
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
			GWT.log("handleSteAndAdvance DOC_TYPE");
			addToken(step);
			createStep(ElementType.BO_TYPE, new SelectTypeStep(), value);
			break;
		case BO_TYPE:
			GWT.log("handleSteAndAdvance BO_TYPE");
			if (step instanceof SelectTypeStep) {
				createStep(ElementType.BO, new SelectValueStep(), value);
			}
			break;
		case BO:
			GWT.log("handleSteAndAdvance BO");
			addToken(step);
			hasPrimaryBoType(step);
			break;
		case PRIMARY_BO_TYPE:
			GWT.log("handleSteAndAdvance PRIMARY_BO_TYPE");
			if (step instanceof SelectTypeStep) {
				createStep(ElementType.PRIMARY_BO, new SelectValueStep(), value);
			}
			break;
		case PRIMARY_BO:
			GWT.log("handleSteAndAdvance PRIMARY_BO");
			addToken(step);
			updateLayout();
			break;
		default:
			break;
		}
	}

	private void hasPrimaryBoType(AbstractStep step) {
		final String previousValue = step.getPreviousValue();
		ServiceFactory.hasPrimaryBOType(previousValue,getBooleanCallBack(step));
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

	private void updateLayout() {
		mainPanel.clear();
		mainPanel.add(tokensLayout);

	}

	private void createStep(ElementType docType, AbstractStep step, String value) {
		step.setElementType(docType);
		step.setPreviousValue(value);
		setData(step);
		updateLayout();
		mainPanel.add(step.getWidget());
	}

	public void setData(AbstractStep step) {
		ElementType elementType = step.getElementType();
		String previousValue = step.getPreviousValue();
		switch (elementType) {
		case BO_TYPE:
			GWT.log("setData BO_TYPE");
			ServiceFactory.getBOTypeBy(previousValue, getAllCallBack(step));
			break;
		case PRIMARY_BO_TYPE:
			GWT.log("setData PRIMARY_BO_TYPE");
			ServiceFactory.getPrimaryBOTypeBy(previousValue,
					getAllCallBack(step));
			break;
		case DOC_TYPE:
			GWT.log("setData DOC_TYPE");
			ServiceFactory.getAllDocTypes(getAllCallBack(step));
			break;
		case PRIMARY_BO:
			GWT.log("setData PRIMARY_BO");
			ServiceFactory.getPrimaryBO(previousValue, getAllCallBack(step));
			break;
		case BO:
			GWT.log("setData BO");
			ServiceFactory.getBOBy(previousValue, getAllCallBack(step));
			break;
		default:
			break;
		}
	}

	private MDMAsyncCallback getAllCallBack(final AbstractStep abstractStep) {
		return new MDMAsyncCallback<List<String>>() {

			@Override
			public void doSuccess(final List<String> result) {
				GWT.log("MDMAsyncCallbackgetAllCallBack (" + abstractStep
						+ " " + abstractStep.elementType + " )");
				GWT.log("MDMAsyncCallbackgetAllCallBack [" + abstractStep.getPreviousValue()
						+ " " + abstractStep.getValue() + " ]");
				if (result.size() == 1) {
					GWT.log("MDMAsyncCallbackgetAllCallBack (" + abstractStep
							+ " " + abstractStep.elementType + " ) => value: "
							+ result.get(0));
					abstractStep.setValue(result.get(0));
					handleStepAndAdvance(abstractStep);
					abstractStep.addAll(result);
				} else {
					abstractStep.addAll(result);
				}

			}
		};
	}

	private MDMAsyncCallback getBooleanCallBack(final AbstractStep abstractStep) {
		return new MDMAsyncCallback<Boolean>() {

			@Override
			public void doSuccess(final Boolean result) {
				GWT.log("getBooleanCallBack (" + abstractStep + " "
						+ abstractStep.elementType + " ) => value: "
						+ abstractStep.getPreviousValue());
				if (result.booleanValue()) {
					createStep(ElementType.PRIMARY_BO_TYPE,
							new SelectTypeStep(),
							abstractStep.getPreviousValue());
				}
			}
		};
	}

}
