package com.tibco.amf.platform.runtime.extension.support;

import javax.xml.namespace.QName;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.amf.platform.runtime.extension.context.RequestContext;

@Weave(type=MatchType.Interface)
public abstract class GenericBusinessInterface<M> {

	@Trace(dispatcher=true)
	public void invoke(QName qName, RequestContext requestContext, M paramM) {
		String serviceName = requestContext.getServiceName();
		String operationName = requestContext.getOperationName().getLocalPart();
	
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","BusinessInterface",getClass().getSimpleName(),serviceName,operationName});
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, true, "BusinessInterface", new String[]{serviceName,operationName});
		Weaver.callOriginal();
	}
}
