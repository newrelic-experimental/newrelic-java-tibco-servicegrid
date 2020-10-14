package com.tibco.xml.soap.helpers;

import java.io.InputStream;
import java.net.Socket;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibxmlsoap.InboundTranportWrapper;
import com.tibco.xml.soap.api.transport.TransportContext;
import com.tibco.xml.soap.api.transport.TransportMessage;
import com.tibco.xml.soap.api.transport.TransportUri;

@Weave
public abstract class HttpHelper {

	@Trace(excludeFromTransactionTrace=true)
	public static void parseHttpRequest(InputStream inputStream, TransportMessage transportMessage) {
		Weaver.callOriginal();
		TransportContext transportCtx = transportMessage.getTransportContext(); //.getTransportUri();
		if(transportCtx != null) {
			TransportUri transportURI = transportCtx.getTransportUri();
			if(transportURI != null) {
				String path = transportURI.getPath();
				if(path != null && !path.isEmpty()) {
					NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "HTTP-SOAP", path);
				}
			}
		}
		if(transportMessage != null && transportMessage.getTransportContext() != null) {
			InboundTranportWrapper wrapper = new InboundTranportWrapper(transportMessage.getTransportContext());
			AgentBridge.getAgent().getTransaction(false).provideHeaders(wrapper);
		}
		
	}
	
	@Trace(excludeFromTransactionTrace=true)
	public static void parseHttpRequest(InputStream inputStream, TransportMessage transportMessage, int paramInt) {
		Weaver.callOriginal();
		TransportContext transportCtx = transportMessage.getTransportContext(); //.getTransportUri();
		if(transportCtx != null) {
			TransportUri transportURI = transportCtx.getTransportUri();
			if(transportURI != null) {
				String path = transportURI.getPath();
				if(path != null && !path.isEmpty()) {
					NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "HTTP-SOAP", path);
				}
			}
		}
		if(transportMessage != null && transportMessage.getTransportContext() != null) {
			InboundTranportWrapper wrapper = new InboundTranportWrapper(transportMessage.getTransportContext());
			AgentBridge.getAgent().getTransaction(false).provideHeaders(wrapper);
		}
	}
	
	@Trace(excludeFromTransactionTrace=true)
	public static void parseHttpRequest(Socket socket, TransportMessage transportMessage) {
		Weaver.callOriginal();
		TransportContext transportCtx = transportMessage.getTransportContext(); //.getTransportUri();
		if(transportCtx != null) {
			TransportUri transportURI = transportCtx.getTransportUri();
			if(transportURI != null) {
				String path = transportURI.getPath();
				if(path != null && !path.isEmpty()) {
					NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "HTTP-SOAP", path);
				}
			}
		}
		if(transportMessage != null && transportMessage.getTransportContext() != null) {
			InboundTranportWrapper wrapper = new InboundTranportWrapper(transportMessage.getTransportContext());
			AgentBridge.getAgent().getTransaction(false).provideHeaders(wrapper);
		}
	}
}
