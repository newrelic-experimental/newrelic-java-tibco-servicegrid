package com.tibco.amf.platform.runtime.extension.context;

import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;

@Weave(type=MatchType.Interface)
public abstract class RequestContext {

	@NewField
	public Token token = null;
	
}
