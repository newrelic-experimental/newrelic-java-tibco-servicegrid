package com.tibco.amf.binding.soap.runtime.transport.jms.inbound;

import javax.jms.Message;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibco.sg.amfsoap.JmsMetricUtil;
import com.tibco.amf.binding.soap.runtime.SoapBusinessInterface;
import com.tibco.corona.binding.bindingsoapmodel.SOAPOperationConfiguration;

@Weave(type=MatchType.BaseClass)
public abstract class BaseJmsMessageEndpoint {
	
	@Trace(dispatcher=true)
	public void onMessage(Message paramMessage, SoapBusinessInterface paramSoapBusinessInterface, SOAPOperationConfiguration paramSOAPOperationConfiguration) {
		TracedMethod tracer = NewRelic.getAgent().getTracedMethod();
		JmsMetricUtil.processConsume(paramMessage, tracer);
		String soapAction = paramSOAPOperationConfiguration.getAction();
		if(soapAction == null) {
			soapAction = "UnknownSoapAction";
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JMSMessageEndpoint",getClass().getName(),soapAction});
		Weaver.callOriginal();
	}

}
