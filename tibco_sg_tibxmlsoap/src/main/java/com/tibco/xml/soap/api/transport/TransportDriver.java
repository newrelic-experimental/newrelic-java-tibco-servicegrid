package com.tibco.xml.soap.api.transport;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class TransportDriver {

	@Trace
	public void sendMessage(TransportMessage transportMessage) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","TransportDriver",getClass().getName(),"sendMessage"});
		Weaver.callOriginal();
	}

	@Trace
	public void sendMessage(TransportMessage transportMessage, TransportApplication transportApplication, int paramInt) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","TransportDriver",getClass().getName(),"sendMessage"});
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public void noResponse(TransportContext transportContext) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","TransportDriver",getClass().getName(),"noResponse"});
		Weaver.callOriginal();
	}

}
