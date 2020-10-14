package com.tibco.amf.platform.runtime.extension.support;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.amf.platform.runtime.extension.context.CallbackContext;

@Weave(type=MatchType.Interface)
public abstract class GenericReplyCallback<M> {

	public void invoke(CallbackContext callbackContext, M paramM) {
		if (callbackContext != null) {
			String service = callbackContext.getServiceName();
			String operation = callbackContext.getOperationName() != null ? callbackContext.getOperationName().getLocalPart() : "Unknown";
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "ReplyCallback",new String[] { "ReplyCallback", service, operation });
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] { "Custom", "GenericReplyCallback", getClass().getName(), service, operation });
		}
		Weaver.callOriginal();
	}
}