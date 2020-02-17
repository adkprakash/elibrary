package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.BookDAO;

public class DeleteBook extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String callno = request.getParameter("callno");
		try {
			BookDAO bDAO = new BookDAO();
			bDAO.deleteBook(callno);
			response.sendRedirect("ViewBook");
		}catch(Exception ex){
			System.out.println(ex);
		}
	}

}
