package com.tibco.spin.soap.processors;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.spin.config.IOperationBindingConfig;
import com.tibco.spin.config.ISoapServiceConfig;
import com.tibco.spin.soap.processors.context.MessageContext;
import com.tibco.spin.soap.processors.context.OperationContext;
import com.tibco.spin.soap.processors.context.ServiceContext;

@Weave
public abstract class SoapPipeline {
	
	@Trace(dispatcher=true)
	protected void process(MessageContext msgContext) {
		String service = "UnknownService";
		ServiceContext serviceCtx = msgContext.getServiceContext();
		if(serviceCtx != null) {
			ISoapServiceConfig serviceConfig = serviceCtx.getServiceConfig();
			if(serviceConfig != null) {
				service = serviceConfig.getName() != null ? serviceConfig.getName().getLocalName() : "UnknownService";
			}
		}
		String operation = "UnknownOperation";
		OperationContext operationCtx = msgContext.getOperationContext();
		if(operationCtx != null) {
			IOperationBindingConfig operationConfig = operationCtx.getOperationConfig();
			if(operationConfig != null) {
				operation = operationConfig.getName() != null ? operationConfig.getName().getLocalName() : "UnknownOperation";
			}
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[]{"Custom","SoapPipeline",service,operation});
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "SoapPipeline", new String[] {service,operation});
		Weaver.callOriginal();
	}
}
