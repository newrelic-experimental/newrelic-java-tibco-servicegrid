package com.tibco.pe.core;

import java.util.logging.Level;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.pe.plugin.EventContext;
import com.tibco.xml.data.primitive.ExpandedName;
import com.tibco.xml.datamodel.XiNode;

@Weave(type=MatchType.BaseClass)
public abstract class JobCreator {

	public abstract String getName();
	
	@Trace(dispatcher=true)
	protected Job createJob(JobData paramJobData, Workflow paramWorkflow, EventContext paramEventContext, boolean paramBoolean) {
		Logger logger = NewRelic.getAgent().getLogger();
		String workflow = paramWorkflow.getName();
		String jobName = paramJobData.name;
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JobCreator","createJob",workflow,jobName});
		Job job = Weaver.callOriginal();
		if (job != null) {
			if (job.token == null) {
				job.token = NewRelic.getAgent().getTransaction().getToken();
				logger.log(Level.FINE, "Got token from transaction {0} in {1}.createJob", job.token,getClass().getName());
			} else {
				job.token.link();
				logger.log(Level.FINE, "Linked to token {0} in {1}.onError", job.token,getClass().getName());
			}
		}
		return job;
	}
	
	@Trace(dispatcher=true)
	protected Job createJob(Workflow paramWorkflow, XiNode paramXiNode, EventContext paramEventContext, boolean paramBoolean) {
		Logger logger = NewRelic.getAgent().getLogger();
		String workflow = paramWorkflow.getName();
		ExpandedName expandedName = paramXiNode.getName();
		
		String name = "XiNode";
		StringBuffer sb = new StringBuffer();
		if(expandedName != null) {
			if(expandedName.getNamespaceURI() != null) {
				sb.append(expandedName.getNamespaceURI());
			}
			if(expandedName.getLocalName() != null) {
				if(!sb.toString().isEmpty()) {
					sb.append(':');
				}
				sb.append(expandedName.getLocalName());
			}
		}
		if(!sb.toString().isEmpty()) {
			name = sb.toString();
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JobCreator","createJob",workflow,name});
		Job job = Weaver.callOriginal();
		if (job != null) {
			if (job.token == null) {
				job.token = NewRelic.getAgent().getTransaction().getToken();
				logger.log(Level.FINE, "Got token from transaction {0} in {1}.createJob", job.token,getClass().getName());
			} else {
				job.token.link();
				logger.log(Level.FINE, "Linked to token {0} in {1}.onError", job.token,getClass().getName());
			}
		}
		return job;
	}
}
