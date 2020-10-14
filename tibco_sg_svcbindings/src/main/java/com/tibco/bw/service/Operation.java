package com.tibco.bw.service;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.xml.datamodel.XiNode;

@Weave(type=MatchType.Interface)
public abstract class Operation {

	@Trace(async=true)
	public void onMessage(ExchangeContext exchangeCtx, XiNode xiNode) {
		ReplyHandler replyHandler = exchangeCtx.getReplyHandler();
		if(replyHandler.token == null) {
			replyHandler.token = NewRelic.getAgent().getTransaction().getToken();
		} else {
			replyHandler.token.link();
		}
		Weaver.callOriginal();
	}
}
