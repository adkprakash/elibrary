package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.BookDAO;

public class ReturnBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String callno = request.getParameter("callno");
		int sid = Integer.parseInt(request.getParameter("id"));
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Issue Book Form</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			BookDAO bDAO = new BookDAO();
			int count = bDAO.returnBook(callno, sid);
			if(count > 0) {
				pw.println("<h3>Book returned successfully</h3>");
			}else {
				pw.println("<h3>Sorry, Unable to return book</h3>");
			}
			pw.println("</div>");
			request.getRequestDispatcher("returnbookform.html").include(request, response);
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
