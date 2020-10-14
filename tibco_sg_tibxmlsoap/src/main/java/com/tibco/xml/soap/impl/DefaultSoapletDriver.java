package com.tibco.xml.soap.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.xml.soap.api.transport.TransportMessage;

@Weave(type=MatchType.BaseClass)
public abstract class DefaultSoapletDriver {
	
	@Trace
	public void processMessage(TransportMessage transportMsg) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","DefaultSoapletDriver",getClass().getName(),"processMessage"});
		Weaver.callOriginal();
	}

}
