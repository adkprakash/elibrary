package org.library.view;

import java.io.*;
import javax.servlet.http.*;
public class LogoutLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.html");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
