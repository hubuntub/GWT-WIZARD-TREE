package com.mycompany.example.client.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.mycompany.example.client.MDMEventBus;
import com.mycompany.example.client.wizard.AbstractStep;
import com.mycompany.example.client.wizard.ElementType;
import com.mycompany.example.client.wizard.INSTANCE;
import com.mycompany.example.client.wizard.SelectValueStep;
import com.mycompany.example.client.wizard.WizardCancelEvent;

public class TokensLayout extends Composite {
	private static final String HEIGHT = "40px";
	private FlowPanel flowpanel = new FlowPanel();
	Logger log = Logger.getLogger("TokensLayout");
	private Map<AbstractStep, TokenField> wizards = new LinkedHashMap<AbstractStep, TokenField>();
	private List<TokenField> tokens = new ArrayList<TokenField>();

	public TokensLayout() {
		flowpanel.setHeight(HEIGHT);
		flowpanel.setWidth("100%");
		flowpanel.addStyleName(INSTANCE.tokenLayout());
		initWidget(flowpanel);
	}


	public void createToken(final AbstractStep abstractStep) {
		ElementType color = abstractStep.getElementType();
		String content = abstractStep.getValue();
		log.log(Level.INFO, "create token " + content);
		TokenField tokenField = new TokenField(content, color);
		if (abstractStep instanceof SelectValueStep) {
			wizards.put(abstractStep, tokenField);
		}
		MDMEventBus.EVENT_BUS.addHandler(ClickEvent.getType(), new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!(event.getSource() instanceof Button)) {
					MDMEventBus.EVENT_BUS.fireEvent(new WizardCancelEvent(
							abstractStep));
				}

			}
		});
		if (!tokenField.isAttached()) {
			flowpanel.add(tokenField);
		}
	}

	public void removeToken(final AbstractStep wizardStep) {
		flowpanel.clear();

		removeTokensAfter(wizardStep);
		for (TokenField tokenField : wizards.values()) {
			if (!tokenField.isAttached()) {
				flowpanel.add(tokenField);
			}
		}
	}

	private void removeTokensAfter(AbstractStep wizardStep) {
		Iterator<Entry<AbstractStep, TokenField>> iter = wizards.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<AbstractStep, TokenField> entry = iter.next();
			if (wizardStep.equals(entry.getKey())) {
				iter.remove();
				break;
			}
		}
//		while (iter.hasNext()) {
//			iter.next();
//			iter.remove();
//		}
	}


	public void clear() {
		tokens.clear();
	}

	public boolean isEmpty() {
		return tokens.isEmpty();
	}

}
