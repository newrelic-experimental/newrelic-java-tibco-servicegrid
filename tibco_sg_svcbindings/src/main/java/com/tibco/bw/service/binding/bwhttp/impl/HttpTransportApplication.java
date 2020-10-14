package com.tibco.bw.service.binding.bwhttp.impl;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibxmlsoap.BWRequestWrapper;
import com.tibco.bw.service.Endpoint;
import com.tibco.plugin.share.http.wssdk.ServletContext;
import com.tibco.xml.soap.api.transport.TransportContext;
import com.tibco.xml.soap.api.transport.TransportMessage;
import com.tibco.xml.soap.api.transport.TransportUri;

@Weave
public abstract class HttpTransportApplication {

	@Trace(dispatcher=true)
	public void processMessage(TransportMessage transportMsg) {
		TransportContext transportCtx = transportMsg.getTransportContext();
		Transaction transaction = NewRelic.getAgent().getTransaction();
		String uri = null;
		if(transportCtx != null) {
			TransportUri transportUri = transportCtx.getTransportUri();
			if(transportUri != null) {
				uri = transportUri.toExternalForm();
				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "HTTPTransport", new String[] {uri});
			}
		}
		if(!transaction.isTransactionNameSet()) {
			transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "HTTPTransport", new String[] {"/UnknownTransportURI"});
		}
		ServletContext servletContext = (ServletContext)transportCtx;
		BWRequestWrapper wrapper = new BWRequestWrapper(servletContext.getRequestMessage());
		AgentBridge.getAgent().getTransaction(false).provideHeaders(wrapper);
		transaction.convertToWebTransaction();
		Weaver.callOriginal();
	}
	
	@Trace
	protected void invokeServiceEndpoint(Endpoint paramEndpoint, TransportMessage paramTransportMessage) {
		NewRelic.addCustomParameter("Endpoint", paramEndpoint.getName());
		Weaver.callOriginal();
	}
	
}
