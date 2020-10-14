package com.tibco.spin.transport;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class HttpClientFacade {

	@SuppressWarnings("rawtypes")
	@Trace
	public Result doMethod(String urlStr, String method, Map paramMap, byte[] paramArrayOfByte) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom",getClass().getName(),method,urlStr});
		return Weaver.callOriginal();
	}
	
	@Weave
	public static class Result {
		
	}
}
