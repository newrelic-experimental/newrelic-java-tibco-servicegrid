package com.tibco.bw.service;

import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.schema.SmElement;

@Weave(type=MatchType.Interface)
public abstract class ReplyHandler {
	
	@NewField
	public Token token = null;

	public void reply(XiNode paramXiNode) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}

	public void noReply() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}

	public void fault(XiNode paramXiNode, String paramString, SmElement paramSmElement) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
}
