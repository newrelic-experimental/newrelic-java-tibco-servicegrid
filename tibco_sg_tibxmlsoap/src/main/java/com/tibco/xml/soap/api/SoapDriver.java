package com.tibco.xml.soap.api;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibxmlsoap.OutboundTransportWrapper;
import com.tibco.xml.soap.api.transport.TransportContext;

@Weave(type=MatchType.Interface)
public abstract class SoapDriver {

	@Trace(dispatcher=true)
	public void sendMessage(SoapMessage soapMessage) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapDriver",getClass().getName(),"sendMessage"});
		TransportContext transportCtx = soapMessage.getTransportContext();
		OutboundTransportWrapper wrapper = new OutboundTransportWrapper(transportCtx);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public void sendMessage(SoapMessage soapMessage, SoapApplication paramSoapApplication, int paramInt) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapDriver",getClass().getName(),"sendMessage"});
		TransportContext transportCtx = soapMessage.getTransportContext();
		OutboundTransportWrapper wrapper = new OutboundTransportWrapper(transportCtx);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public void noResponse(TransportContext transportCtx) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapDriver",getClass().getName(),"noResponse"});
		OutboundTransportWrapper wrapper = new OutboundTransportWrapper(transportCtx);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		Weaver.callOriginal();
	}

	@Trace(dispatcher=true)
	public abstract void dispatchMessage(SoapMessage paramSoapMessage);
}
