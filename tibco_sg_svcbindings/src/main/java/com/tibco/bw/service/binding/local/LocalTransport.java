package com.tibco.bw.service.binding.local;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.bw.service.ReplyHandler;
import com.tibco.bw.service.config.common.EndpointOperationReference;
import com.tibco.xml.datamodel.XiNode;

@Weave
public abstract class LocalTransport {

	@Trace(async=true)
	public void execute(EndpointOperationReference endpointOperationRef, XiNode xiNode, ReplyHandler replyHandler) {
		if(replyHandler.token != null) {
			replyHandler.token.link();
		}
		Weaver.callOriginal();
	}
}
