package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
import org.library.model.Librarian;
import java.util.*;
public class ViewLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>View Librarian</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navadmin.html").include(request, response);
			pw.println("<div class = 'container'>");
			LibrarianDAO lDAO = new LibrarianDAO();
			List<Librarian> librarianList = lDAO.viewLibrarian();
			pw.println("<table class='table table-bordered table-striped'>");
			pw.println("<tr>"
					+ "<th>Id</th>"
					+ "<th>Name</th>"
					+ "<th>Email</th>"
					+ "<th>Password</th>"
					+ "<th>Phone</th>"
					+ "<th>Status</th>"
					+ "</tr>");
			for(Librarian lb : librarianList) {
				pw.println("<tr>"
						+ "<td>"+lb.getId()+"</td>"
						+ "<td>"+lb.getName()+"</td>"
						+ "<td>"+lb.getEmail()+"</td>"
						+ "<td>"+lb.getPassword()+"</td>"
						+ "<td>"+lb.getPhone()+"</td>"
						+ "<td><a href='EditLibrarianForm?id="+lb.getId()+"'>Edit</a> || "
						+ "<a href='DeleteLibrarian?id="+lb.getId()+"'>Delete</a></td>"
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
