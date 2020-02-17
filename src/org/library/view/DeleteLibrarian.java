package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
import org.library.model.Librarian;


public class DeleteLibrarian extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		Librarian ob = new Librarian();
		ob.setId(id);
		LibrarianDAO lDAO = new LibrarianDAO();
		lDAO.deleteLibrarian(ob);
		try {
			response.sendRedirect("ViewLibrarian");
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
