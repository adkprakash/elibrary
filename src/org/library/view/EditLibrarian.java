package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
import org.library.model.Librarian;

public class EditLibrarian extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long phone = Long.parseLong(request.getParameter("phone"));

		Librarian ob = new Librarian();
		ob.setId(id);
		ob.setName(name);
		ob.setEmail(email);
		ob.setPassword(password);
		ob.setPhone(phone);
		LibrarianDAO lDAO = new LibrarianDAO();
		lDAO.updateLibrarian(ob);
		try {
			response.sendRedirect("ViewLibrarian");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}