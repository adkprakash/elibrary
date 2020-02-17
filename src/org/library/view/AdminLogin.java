package org.library.view;

import java.io.*;
import javax.servlet.http.*;

public class AdminLogin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Admin Section</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			if(email.equals("admin@gmail.com") && password.equals("admin123")) {
//				pw.println("<h3>Login Successful</h3>");
				HttpSession session=request.getSession();
				session.setAttribute("admin", "admin");
				request.getRequestDispatcher("navadmin.html").include(request, response);
				request.getRequestDispatcher("image.html").include(request, response);
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
