package org.library.view;

import java.io.*;
import javax.servlet.http.*;

public class AddBookForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Add Book Form</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			request.getRequestDispatcher("addbookform.html").include(request, response);
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}