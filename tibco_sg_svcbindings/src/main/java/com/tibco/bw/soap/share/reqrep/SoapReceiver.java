package com.tibco.bw.soap.share.reqrep;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.xml.soap.api.SoapDriver;
import com.tibco.xml.soap.api.SoapMessage;
import com.tibco.xml.soap.api.transport.TransportContext;

@Weave
public abstract class SoapReceiver {
	
	@NewField
	public Token token = null;

	@Trace(async=true)
	public void processMessage(SoapDriver paramSoapDriver, SoapMessage paramSoapMessage) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
	
	@Trace(async=true)
	public void processReplyException(TransportContext paramTransportContext, Exception paramException) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		NewRelic.noticeError(paramException);
		Weaver.callOriginal();
	}
}
