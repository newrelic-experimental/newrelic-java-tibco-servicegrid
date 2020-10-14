package com.nr.instrumentation.tibco.sg.amfsoap;

import java.util.logging.Level;

import javax.jms.JMSException;
import javax.jms.Message;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.InboundHeaders;
import com.newrelic.api.agent.NewRelic;

public class NRMessageInbound implements InboundHeaders {

	private Message message;
	
	public NRMessageInbound(Message msg) {
		message = msg;
	}
	
	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		try {
			return message.getStringProperty(name);
		} catch (JMSException e) {
			NewRelic.getAgent().getLogger().log(Level.FINER,  e, "Error reading property from message");
		}
		return null;
	}

}
