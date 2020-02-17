package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
import org.library.model.Librarian;

public class AddLibrarian extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long phone = Long.parseLong(request.getParameter("phone"));
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Add Librarian</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navadmin.html").include(request, response);
			pw.println("<div class = 'container'>");
			Librarian ob = new Librarian();
			ob.setName(name);
			ob.setEmail(email);
			ob.setPassword(password);
			ob.setPhone(phone);
			LibrarianDAO lDAO = new LibrarianDAO();
			int count = lDAO.insertLibrarian(ob);
			if(count > 0) {
				pw.println("<h3>Librarian added successfully</h3>");
			}
			request.getRequestDispatcher("addlibrarianform.html").include(request, response);
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}