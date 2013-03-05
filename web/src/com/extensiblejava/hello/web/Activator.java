package com.extensiblejava.hello.web;

import org.osgi.framework.*;
import org.osgi.service.http.*;
import com.extensiblejava.facade.*;
/* import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.jetty.servlet.ServletHolder; */


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
//import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
	//private Server server;
	//private ServiceTracker httpServiceTracker;

	//See http://felix.apache.org/documentation/subprojects/apache-felix-http-service.html
	public void start(BundleContext context) throws Exception {
		ServiceReference sRef = context.getServiceReference(HttpService.class.getName());
		ServiceReference lfRef = context.getServiceReference(LoanFacade.class.getName());
    	if (sRef != null) {
    		System.out.println("registering servlets");
     		HttpService service = (HttpService) context.getService(sRef);
     		LoanFacade lf = (LoanFacade) context.getService(lfRef);
      		service.registerServlet("/hello", new HelloWorldServlet(), null, null);
      		service.registerServlet("/loan", new LoanServlet(lf), null, null);
      		service.registerResources("/html", "/html", null);
    	}
	}
	
	public void stop(BundleContext context) {
		ServiceReference sRef = context.getServiceReference(HttpService.class.getName());
		if (sRef != null) {
			HttpService service = (HttpService) context.getService(sRef);
			service.unregister("/hello");
			service.unregister("/loan");
			service.unregister("/html");
		}
	}
}
