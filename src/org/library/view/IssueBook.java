package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.BookDAO;
import org.library.model.Issue;

public class IssueBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String callno = request.getParameter("callno");
		String id = request.getParameter("id");
		String name=  request.getParameter("name");
		long phone = Long.parseLong(request.getParameter("phone"));
		try {
			PrintWriter pw = response.getWriter();
			pw.println("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Issue Book</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			Issue ob = new Issue();
			ob.setCallno(callno);
			ob.setId(id);
			ob.setName(name);
			ob.setPhone(phone);
			BookDAO bDAO = new BookDAO();
			int count = bDAO.issueBook(ob);
			if(count > 0){
				pw.println("<h3>Book issued successfully</h3>");
			}else{
				pw.println("<h3>Sorry, unable to issue book.</h3>");
			}
			request.getRequestDispatcher("issuebookform.html").include(request, response);
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
