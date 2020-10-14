package com.tibco.xml.soap.api;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.xml.soap.api.transport.TransportContext;

@Weave(type=MatchType.Interface)
public abstract class SoapApplication {

	@Trace
	public void processMessage(SoapDriver paramSoapDriver, SoapMessage paramSoapMessage) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapApplication",getClass().getName(),"processMessage"});
		Weaver.callOriginal();

	}

	@Trace
	public void processReplyException(TransportContext transportContext, Exception paramException) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapApplication",getClass().getName(),"processReplyException"});
		Weaver.callOriginal();
	}

}
