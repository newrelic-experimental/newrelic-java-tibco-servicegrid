package com.tibco.pe.core;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class JobListener {

	  public boolean afterExecution(JobEvent jobEvent) {
		  String activityName = jobEvent.getActivityName();
		  String processName = jobEvent.getActualProcessName();
		  String calledProcess = jobEvent.calledProcessName;
		  long executionTime = jobEvent.getExecutionTime();
		  long elapsedTime = jobEvent.getElapsedTime();
		  Map<String, Object> event = new HashMap<String, Object>();
		  event.put("ActivityName", activityName);
		  event.put("ActualProcessName", processName);
		  event.put("CalledProcessName", calledProcess);
		  event.put("ExecutionTime", executionTime);
		  event.put("Elapsed Time", elapsedTime);
		  
		  NewRelic.getAgent().getInsights().recordCustomEvent("JobEvent", event);
		  return Weaver.callOriginal();
	  }

}
