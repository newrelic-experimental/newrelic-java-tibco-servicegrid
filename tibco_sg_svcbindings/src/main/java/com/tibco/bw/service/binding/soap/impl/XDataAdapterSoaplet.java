package com.tibco.bw.service.binding.soap.impl;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.plugin.share.security.context.SecurityContext;
import com.tibco.xml.soap.api.SoapMessage;
import com.tibco.xml.soap.api.SoapletFinisher;

@Weave
public abstract class XDataAdapterSoaplet {
	
	@Trace
	public void processBody(SoapMessage paramSoapMessage1, SoapMessage paramSoapMessage2, SoapletFinisher paramSoapletFinisher, SecurityContext paramSecurityContext) {
		Weaver.callOriginal();
	}
}
