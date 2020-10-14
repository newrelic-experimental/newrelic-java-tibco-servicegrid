package com.nr.instrumentation.tibco.sg.amfsoap;

import java.util.logging.Level;

import javax.jms.JMSException;
import javax.jms.Message;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.OutboundHeaders;

public class NRMessageOutbound implements OutboundHeaders {

	private Message message;
	
	public NRMessageOutbound(Message msg) {
		message = msg;
	}
	
	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public void setHeader(String name, String value) {
		try {
			message.setStringProperty(name, value);
		} catch (JMSException e) {
			NewRelic.getAgent().getLogger().log(Level.FINER, e, "Error set string property on message");
		}
	}

}
