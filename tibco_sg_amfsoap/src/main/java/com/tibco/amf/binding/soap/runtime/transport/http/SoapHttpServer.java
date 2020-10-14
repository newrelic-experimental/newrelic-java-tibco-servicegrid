package com.tibco.amf.binding.soap.runtime.transport.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.tibco.sg.amfsoap.NRRequestWrapper;
import com.nr.instrumentation.tibco.sg.amfsoap.NRResponseWrapper;
import com.tibco.amf.sharedresource.runtime.core.http.nativeinbound.HttpActivationSpec;

@Weave
public abstract class SoapHttpServer {

	private final HttpActivationSpec activationSpec = Weaver.callOriginal();
	
	@Trace
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Transaction transaction = NewRelic.getAgent().getTransaction();
		transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Web", new String[]{activationSpec.getPath()});
		NRRequestWrapper requestWrapper = new NRRequestWrapper(request);
		transaction.setWebRequest(requestWrapper);
		Weaver.callOriginal();
		NRResponseWrapper responseWrapper = new NRResponseWrapper(response);
		transaction.setWebResponse(responseWrapper);
	}
}
