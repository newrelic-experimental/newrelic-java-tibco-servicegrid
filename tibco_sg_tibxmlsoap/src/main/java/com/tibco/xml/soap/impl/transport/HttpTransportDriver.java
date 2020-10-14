package com.tibco.xml.soap.impl.transport;

import java.net.Socket;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class HttpTransportDriver {

	@Trace(dispatcher=true)
	protected void handleRequest(Socket paramSocket) {
		NewRelic.getAgent().getTransaction().convertToWebTransaction();
		Weaver.callOriginal();
	}
}
