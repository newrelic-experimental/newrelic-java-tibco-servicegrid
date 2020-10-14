package com.tibco.pe.core;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.pe.plugin.ProcessContext;
import com.tibco.xml.datamodel.XiNode;

@Weave(type=MatchType.BaseClass)
public abstract class ActivityGroup {
	public abstract String getClassName();

	protected abstract String getURI();

	public abstract String getName();
	
	protected abstract String getGroupName();

	@Trace(dispatcher=true)
	public XiNode eval(ProcessContext processContext, XiNode paramXiNode) {
		String invocationName = processContext.getInvocationName();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","ActivityGroup",getClassName(),invocationName});
		return (XiNode)Weaver.callOriginal();
	}
}
