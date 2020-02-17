package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
public class LibrarianLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType ("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Librarian Section</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");  
			
			LibrarianDAO lDAO = new LibrarianDAO();
			if(lDAO.register(email, password)) {
//				pw.println("<h3>Login Successful</h3>");
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				request.getRequestDispatcher("navlibrarian.html").include(request, response);
				request.getRequestDispatcher("image1.html").include(request, response);
			}else {
				pw.println("<div class='container'>");
				pw.println("<h3>Email or Password error</h3>");
				request.getRequestDispatcher("index.html").include(request, response);
				pw.println("</div>");
			}
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
