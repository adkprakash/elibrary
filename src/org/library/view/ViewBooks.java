package org.library.view;

import java.io.*;
import java.util.List;

import javax.servlet.http.*;

import org.library.controller.BookDAO;
import org.library.model.Book;

public class ViewBooks extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>View Librarian</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			request.getRequestDispatcher("navlibrarian.html").include(request, response);
			pw.println("<div class = 'container'>");
			BookDAO bDAO = new BookDAO();
			List<Book> bookList = bDAO.viewBooks();
			pw.println("<table class='table table-bordered table-striped'>");
			pw.println("<tr>"
					+ "<th>Id</th>"
					+ "<th>Callno</th>"
					+ "<th>Name</th>"
					+ "<th>Author</th>"
					+ "<th>Publisher</th>"
					+ "<th>Quantity</th>"
					+ "<th>Issued</th>"
					+ "<th>Status</th>"
					+ "</tr>");
			for(Book b : bookList) {
				pw.println("<tr>"
						+ "<td>"+b.getId()+"</td>"
						+ "<td>"+b.getCallno()+"</td>"
						+ "<td>"+b.getName()+"</td>"
						+ "<td>"+b.getAuthor()+"</td>"
						+ "<td>"+b.getPublisher()+"</td>"
						+ "<td>"+b.getQuantity()+"</td>"
						+ "<td>"+b.getIssued()+"</td>"
						+ "<td><a href='DeleteBook?callno="+b.getCallno()+"'>Delete</a></td>"
						);
			}
			pw.println("</table>");
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
