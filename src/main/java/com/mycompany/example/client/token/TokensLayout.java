package com.mycompany.example.client.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.wizard.AbstractStep;
import com.mycompany.example.client.wizard.ElementType;
import com.mycompany.example.client.wizard.SelectValueStep;
import com.mycompany.example.client.wizard.WizardCancelEvent;

public class TokensLayout extends Composite {
	private static final String HEIGHT = "40px";
	private HorizontalPanel flowpanel = new HorizontalPanel();
	Logger log = Logger.getLogger("TokensLayout");
	private Map<AbstractStep, TokenField> steps = new LinkedHashMap<AbstractStep, TokenField>();
	private List<TokenField> tokens = new ArrayList<TokenField>();

	public TokensLayout() {
		flowpanel.setHeight(HEIGHT);
		flowpanel.setWidth("100%");
		flowpanel.setSpacing(2);
		initWidget(flowpanel);
		MDMEventBus.EVENT_BUS.addHandler(TokenClickEvent.TYPE,
				new TokenClickHandler() {

					@Override
					public void tokenClick(TokenClickEvent event) {
						TokenField tokenField = event.getTokenField();
						AbstractStep abstractStep = getStepFrom(tokenField);
						if (abstractStep != null) {
							MDMEventBus.EVENT_BUS
									.fireEvent(new WizardCancelEvent(
											abstractStep));
						}
					}
				});
	}

	private AbstractStep getStepFrom(TokenField tokenField) {
		for (Entry<AbstractStep, TokenField> entry : steps.entrySet()) {
			if (entry.getValue().equals(tokenField)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void createToken(final AbstractStep abstractStep) {
		ElementType color = abstractStep.getElementType();
		String content = abstractStep.getValue();
		TokenField tokenField = new TokenField(content, color);
		if (abstractStep instanceof SelectValueStep) {
			steps.put(abstractStep, tokenField);
		}

		if (!tokenField.isAttached()) {
			flowpanel.add(tokenField);
		}
	}

	public void removeToken(final AbstractStep abstractStep) {
		flowpanel.clear();

		removeTokensAfter(abstractStep);
		for (TokenField tokenField : steps.values()) {
			if (!tokenField.isAttached()) {
				flowpanel.add(tokenField);
			}
		}
	}

	private void removeTokensAfter(AbstractStep abstractStep) {

		Iterator<Entry<AbstractStep, TokenField>> iter = steps.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<AbstractStep, TokenField> entry = iter.next();
			if (abstractStep.equals(entry.getKey())) {
				iter.remove();
				break;
			}
		}

		while (iter.hasNext()) {
			iter.next();
			iter.remove();
		}
	}

	public void clear() {
		tokens.clear();
	}

	public boolean isEmpty() {
		return tokens.isEmpty();
	}

}
