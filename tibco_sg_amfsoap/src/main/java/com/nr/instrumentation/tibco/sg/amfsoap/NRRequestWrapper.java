package com.nr.instrumentation.tibco.sg.amfsoap;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.newrelic.api.agent.ExtendedRequest;
import com.newrelic.api.agent.HeaderType;


public class NRRequestWrapper extends ExtendedRequest
{
  private final HttpServletRequest request;

  public NRRequestWrapper(HttpServletRequest req)
  {
    request = req;
  }

  public String getRequestURI()
  {
    return request.getRequestURI();
  }

  public String getHeader(String name)
  {
    return request.getHeader(name);
  }

  public String getRemoteUser()
  {
    return request.getRemoteUser();
  }

  @SuppressWarnings("rawtypes")
public Enumeration getParameterNames()
  {
    return request.getParameterNames();
  }

  public String[] getParameterValues(String name)
  {
    return request.getParameterValues(name);
  }

  public Object getAttribute(String name)
  {
    return request.getAttribute(name);
  }

  public String getCookieValue(String name)
  {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (name.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  public HeaderType getHeaderType()
  {
    return HeaderType.HTTP;
  }

  public String getMethod()
  {
    return request.getMethod();
  }
}
