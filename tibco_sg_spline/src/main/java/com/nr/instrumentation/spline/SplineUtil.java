package com.nr.instrumentation.spline;

import javax.xml.namespace.QName;

import com.tibco.amf.spline.api.context.IDispatchContext;
import com.tibco.amf.spline.api.context.ISplineExchange;

public class SplineUtil {

	public static String[] getServiceNames(ISplineExchange splineExchange) {
		IDispatchContext<Object, Object> dispatchCtx = splineExchange.getDispatchContext();
		String service = "UnknownService";
		if(dispatchCtx != null) {
			QName serviceQName = dispatchCtx.getService();
			service = serviceQName != null ? serviceQName.getLocalPart() : "UnknownService";
		}
		String operation = dispatchCtx.getOperationName() != null ? dispatchCtx.getOperationName().getLocalPart() :"UnknownOperation";

		return new String[] {service,operation};
	}
}
