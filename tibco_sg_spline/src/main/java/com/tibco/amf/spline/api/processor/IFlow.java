package com.tibco.amf.spline.api.processor;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.spline.SplineUtil;
import com.tibco.amf.spline.api.context.ISplineExchange;
import com.tibco.amf.spline.api.context.SplineExchangeStatus;

@Weave(type=MatchType.Interface)
public abstract class IFlow {

	@Trace(dispatcher=true)
	public SplineExchangeStatus process(ISplineExchange splineExchange) {
		String[] serviceNames = SplineUtil.getServiceNames(splineExchange);
		
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","IFlow",getClass().getName(),serviceNames[0],serviceNames[1]});
		return Weaver.callOriginal();
	}
}
