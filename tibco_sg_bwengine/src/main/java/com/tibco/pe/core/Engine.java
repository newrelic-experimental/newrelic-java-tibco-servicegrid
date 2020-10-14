package com.tibco.pe.core;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.pe.plugin.TraceMessageBW;

@Weave(type=MatchType.ExactClass)
public abstract class Engine {

	public abstract String getEngineName();
	
	Engine(JobPool paramJobPool) {
		String engineName = getEngineName();
		if(engineName != null && !engineName.isEmpty()) {
			NewRelic.setInstanceName(getEngineName());
			NewRelic.setProductName("Tibco");
		}
	}
	
	public void handleException(String paramString, Throwable paramThrowable) {
		NewRelic.noticeError(paramThrowable);
		Weaver.callOriginal();
	}
	
	public void logException(TraceMessageBW paramTraceMessageBW, Throwable paramThrowable) {
		NewRelic.noticeError(paramThrowable);
		Weaver.callOriginal();
	}
}
