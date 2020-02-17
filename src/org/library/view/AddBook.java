package org.library.view;

import java.io.*;
import javax.servlet.http.*;

import org.library.controller.BookDAO;
import org.library.model.Book;

public class AddBook extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String callno = request.getParameter("callno");
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Add Book</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			Book ob = new Book();
			ob.setCallno(callno);
			ob.setName(name);
			ob.setAuthor(author);
			ob.setPublisher(publisher);
			ob.setQuantity(quantity);
			BookDAO bDAO = new BookDAO();
			int count = bDAO.insertBooks(ob);
			if(count > 0) {
				pw.println("<h3>Book added successfully</h3>");
			}
			request.getRequestDispatcher("addbookform.html").include(request, response);
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}