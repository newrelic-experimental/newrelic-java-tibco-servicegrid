package com.tibco.amf.binding.soap.runtime.transport.http;

import java.util.HashMap;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class SoapHttpRequestCallback<M> {

	@Trace
	public void processHttpPost(HashMap<String, String> actionHeaders, SoapHttpReplyCallback<byte[]> replyCallback, Subject securityObject, HttpServletRequest request, HttpServletResponse response, long timestamp) {

		String soapAction;
		soapAction = (String)actionHeaders.get("soapaction");
		if(soapAction == null) {
			soapAction = (String)actionHeaders.get("action");
		}
		if(soapAction == null) {
			soapAction = "UnknownAction";
		}
		
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","SoapHttpRequestCallback",getClass().getName(),soapAction});
		Weaver.callOriginal();
	}
}
