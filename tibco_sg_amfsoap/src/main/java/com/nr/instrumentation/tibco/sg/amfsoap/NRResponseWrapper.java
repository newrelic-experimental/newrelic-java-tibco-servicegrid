package com.nr.instrumentation.tibco.sg.amfsoap;

import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.ExtendedResponse;


public class NRResponseWrapper extends ExtendedResponse
{
  private final HttpServletResponse response;

  public NRResponseWrapper(HttpServletResponse resp)
  {
    response = resp;
  }


  public HeaderType getHeaderType()
  {
    return HeaderType.HTTP;
  }


@Override
public void setHeader(String name, String value) {
	response.setHeader(name, value);
}


@Override
public int getStatus() throws Exception {
	return 0;
}


@Override
public String getStatusMessage() throws Exception {
	return null;
}


@Override
public String getContentType() {
	return response.getContentType();
}


@Override
public long getContentLength() {
	return response.getBufferSize();
}


}
