package com.tibco.bw.soap.share.reqrep;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.pe.plugin.ActivityContext;
import com.tibco.pe.plugin.ProcessContext;
import com.tibco.xml.datamodel.XiNode;
import com.tibco.xml.schema.SmParticleTerm;
import com.tibco.xml.xdata.bind.BindingRunner;

@Weave(type=MatchType.BaseClass)
public abstract class SoapRequestor {

	@Trace
	public SoapReceiver execute(SoapMessageExchangeContext paramSoapMessageExchangeContext, XiNode paramXiNode, boolean paramBoolean, BindingRunner paramBindingRunner1, SmParticleTerm paramSmParticleTerm1, BindingRunner paramBindingRunner2, SmParticleTerm paramSmParticleTerm2) {
		SoapReceiver receiver = Weaver.callOriginal();
		if(receiver != null && receiver.token == null) {
			receiver.token = NewRelic.getAgent().getTransaction().getToken();
		}
		return receiver;
	}
	
	public SoapReceiver execute(ProcessContext paramProcessContext, ActivityContext paramActivityContext, XiNode paramXiNode, boolean paramBoolean1, boolean paramBoolean2) {
		SoapReceiver receiver = Weaver.callOriginal();
		if(receiver != null && receiver.token == null) {
			receiver.token = NewRelic.getAgent().getTransaction().getToken();
		}
		return receiver;
	}
}
