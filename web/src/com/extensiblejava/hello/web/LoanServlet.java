package com.extensiblejava.hello.web;

import java.util.HashMap;
import java.math.BigDecimal;

import com.extensiblejava.loan.*;
import com.extensiblejava.facade.*;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//See http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/Servlet-Tutorial-Form-Data.html
public class LoanServlet extends HttpServlet {
	private LoanFacade loanFacade;

	public LoanServlet(LoanFacade lf) {
		super();
		this.loanFacade = lf;
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.setContentType("text/html");
		//resp.getWriter().write("<html><body><p>Hello OSGI World -- Welcome to Jetty embedded in Felix.</p><p>Say <a href=\"./a/hello.html\"/>hello.</a></p><p>Say <a href=\"./a/goodbye.html\"/>goodbye.</a></p></body></html>"); //$NON-NLS-1$
		/*String cp = req.getContextPath();
		System.out.println("cp: " + cp);
		String pi = req.getPathInfo();
		System.out.println("PI: " + pi);
		String pt = req.getPathTranslated();
		System.out.println("pt: " + pt);
		String qs = req.getQueryString();
		System.out.println("qs: " + qs);
		String ri = req.getRequestURI();
		System.out.println("ri: " + ri);
		StringBuffer ru = req.getRequestURL();
		System.out.println("ru: " + ru);*/
		
		String principle = req.getParameter("principle");
		String rate = req.getParameter("rate");
		String term = req.getParameter("term");
		/*System.out.println("Principle: " + principle);
		System.out.println("rate: " + rate);
		System.out.println("term: " + term);*/
		
		resp.setContentType("application/json");
		//JsonObject json = JSON
		Gson gson = new Gson();
		HashMap<String, Object> pmt = new HashMap<String, Object>();
		//BigDecimal payment = this.loanFacade.getMonthlyPayment(new BigDecimal("15000"), new BigDecimal("12"), 60);
		BigDecimal payment = this.loanFacade.getMonthlyPayment(new BigDecimal(principle), new BigDecimal(rate), new Integer(term).intValue());
		
		pmt.put("payment",payment);
		//pmt.put("payment","455");
		resp.getWriter().print(gson.toJson(pmt));
		
	}
	
	protected void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
