package com.tibco.xml.soap.impl;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibxmlsoap.InboundTranportWrapper;
import com.tibco.xml.soap.api.transport.TransportContext;
import com.tibco.xml.soap.api.transport.TransportMessage;
import com.tibco.xml.soap.api.transport.TransportUri;

@Weave(type=MatchType.BaseClass)
public abstract class BaseSoapDriver {

	@Trace(dispatcher=true)
	public void processMessage(TransportMessage transportMsg) {
		TransportContext transportCtx = transportMsg.getTransportContext();
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(transportCtx != null) {
			TransportUri transportUri = transportCtx.getTransportUri();
			if(transportUri != null) {
				String uri = transportUri.toExternalForm();
				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "BaseSoapDriver", new String[] {uri});
			}
		}
		if(!transaction.isTransactionNameSet()) {
			transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "BaseSoapDriver", new String[] {"/UnknownTransportURI"});
		}
		InboundTranportWrapper wrapper = new InboundTranportWrapper(transportCtx);
		AgentBridge.getAgent().getTransaction(false).provideHeaders(wrapper);
		Weaver.callOriginal();

	}
}
