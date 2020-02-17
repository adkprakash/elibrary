package org.library.view;

import java.io.*;
import java.util.List;

import javax.servlet.http.*;

import org.library.controller.BookDAO;
import org.library.model.Issue;

public class ViewIssuedBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>View Issued Book</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			BookDAO bDAO = new BookDAO();
			List<Issue> issuedList = bDAO.viewIssuedBook();
			pw.println("<table class='table table-bordered table-striped'>");
			pw.println("<tr>"
					+ "<th>Callno</th>"
					+ "<th>Student Name</th>"
					+ "<th>Student ID</th>"
					+ "<th>Student Phone</th>"
					+ "<th>Issued Date</th>"
					+ "<th>Return Status</th>"
					+ "</tr>");
			for(Issue i : issuedList) {
				pw.println("<tr>"
						+ "<td>"+i.getCallno()+"</td>"
						+ "<td>"+i.getName()+"</td>"
						+ "<td>"+i.getId()+"</td>"
						+ "<td>"+i.getPhone()+"</td>"
						+ "<td>"+i.getIssueddate()+"</td>"
						+ "<td>"+i.getReturnstatus()+"</td>"
						+ "</tr>");
			}
			pw.println("</table>");
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
