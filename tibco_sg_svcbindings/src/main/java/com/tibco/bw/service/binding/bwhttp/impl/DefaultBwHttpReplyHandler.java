package com.tibco.bw.service.binding.bwhttp.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibxmlsoap.BWResponseWrapper;
import com.tibco.bw.service.Operation;
import com.tibco.bw.service.config.ec.OperationConfiguration;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.soap.api.transport.TransportContext;
import com.tibco.xml.soap.api.transport.TransportDriver;
import com.tibco.xml.soap.api.transport.TransportMessage;

@Weave
public abstract class DefaultBwHttpReplyHandler {
	public abstract boolean isReplyDone();
	
	@NewField
	public Token token = null;
	
	public DefaultBwHttpReplyHandler(TransportDriver paramTransportDriver, TransportContext paramTransportContext, Operation paramOperation, OperationConfiguration paramOperationConfiguration) {
		token = NewRelic.getAgent().getTransaction().getToken();
	}
	
	@Trace(async=true)
	public void reply(XiNode paramXiNode, boolean paramBoolean) {
		if(token != null) {
			token.link();
		}
		Weaver.callOriginal();
		if(isReplyDone()) {
			token.expire();
			token = null;
		}
	}
	
	@Trace(async=true)
	public void partialReply(XiNode paramXiNode, boolean paramBoolean) {
		if(token != null) {
			token.link();
		}
		Weaver.callOriginal();
		if(isReplyDone()) {
			token.expire();
			token = null;
		}
	}
	
	protected TransportMessage xiNodeToTransportMessage(XiNode paramXiNode) {
		TransportMessage tranportMsg = Weaver.callOriginal();
		DefaultBwHttpResponseMessage resp = (DefaultBwHttpResponseMessage)tranportMsg;
		BWResponseWrapper wrapper = new BWResponseWrapper(resp);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		return tranportMsg;
		
	}
}
