package com.tibco.pe.core;

import java.util.logging.Level;

import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.pe.util.Reminder;

@Weave
public abstract class JobDispatcher {

	public void itsTime(Reminder paramReminder, long paramLong) {
		Logger logger = NewRelic.getAgent().getLogger();
		Job localJob = (Job)paramReminder.getClosure();
		if(localJob != null) {
			if(localJob.token == null) {
				localJob.token = NewRelic.getAgent().getTransaction().getToken();
				logger.log(Level.FINE, "Got token from transaction {0} in {1}.itsTime", localJob.token,getClass().getName());
			} else {
				localJob.token.link();
				logger.log(Level.FINE, "Linked to token {0} in {1}.itsTime", localJob.token,getClass().getName());
			}
			
		}
		Weaver.callOriginal();
	}
	
	@Weave
	static class JobCourier {
		
		@Trace(dispatcher=true)
		private boolean a(Job job) {
			Logger logger = NewRelic.getAgent().getLogger();
			String fullCallName = job.getFullCallName();
			NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","JobCourier",fullCallName});
			Transaction transaction = NewRelic.getAgent().getTransaction();
			if(!transaction.isTransactionNameSet()) {
				NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, true, "Job", new String[] {fullCallName});
			}
			if(job.token != null) {
				job.token.link();
				logger.log(Level.FINE, "Linked to token {0} in {1}.a", job.token,getClass().getName());
			}
			return Weaver.callOriginal();
		}
	}
}
