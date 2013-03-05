package com.extensiblejava.hello.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//See http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/Servlet-Tutorial-Form-Data.html
public class HelloWorldServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.getWriter().write("<html><body><p>Hello OSGI World -- Welcome to Jetty embedded in Felix.</p><p>Say <a href=\"http://localhost:8080/html/hello.html\"/>hello.</a></p><p>Say <a href=\"http://localhost:8080/html/goodbye.html\"/>goodbye.</a></p></body></html>"); //$NON-NLS-1$
	}
	
	protected void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
