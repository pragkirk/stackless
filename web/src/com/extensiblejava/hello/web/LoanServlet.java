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
		
		String principle = req.getParameter("principle");
		String rate = req.getParameter("rate");
		String term = req.getParameter("term");
		
		resp.setContentType("application/json");
		Gson gson = new Gson();
		HashMap<String, Object> pmt = new HashMap<String, Object>();
		BigDecimal payment = this.loanFacade.getMonthlyPayment(new BigDecimal(principle), new BigDecimal(rate), new Integer(term).intValue());
		
		pmt.put("payment",payment);
		resp.getWriter().print(gson.toJson(pmt));
		
	}
	
	protected void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
