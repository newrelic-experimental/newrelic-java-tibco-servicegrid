package com.nr.instrumentation.tibxmlsoap;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.InboundHeaders;
import com.tibco.bw.service.binding.bwhttp.BwHttpRequest;

public class BWRequestWrapper implements InboundHeaders {

	private BwHttpRequest request;
	
	public BWRequestWrapper(BwHttpRequest req) {
		request = req;
	}
	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		return request.getHeader(name);
	}

}
