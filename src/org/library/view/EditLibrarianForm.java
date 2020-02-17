package org.library.view;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.library.controller.LibrarianDAO;
import org.library.model.Librarian;

public class EditLibrarianForm extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter pw = response.getWriter();
			
			pw.print("<!DOCTYPE html>");
			pw.print("<html>");
			pw.println("<head>");
			pw.println("<title>Edit Librarian Form</title>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.min.css'/>");
			pw.println("</head>");
			pw.println("<body>");
			
			request.getRequestDispatcher("navadmin.html").include(request, response);
			pw.println("<div class='container'>");
			int id = Integer.parseInt(request.getParameter("id"));
			
			Librarian ob = new Librarian();
			ob = LibrarianDAO.viewById(id);
			
			pw.print("<form action='EditLibrarian' method='post' style='width:300px'>");
			pw.print("<div class='form-group'>");
			pw.print("<input type='hidden' name='id' value='"+ob.getId()+"'/>");
			pw.print("<label for='name1'>Name</label>");
			pw.print("<input type='text' class='form-control' value='"+ob.getName()+"' name='name' id='name1' placeholder='Name'/>");
			pw.print("</div>");
			pw.print("<div class='form-group'>");
			pw.print("<label for='email1'>Email address</label>");
			pw.print("<input type='email' class='form-control' value='"+ob.getEmail()+"'  name='email' id='email1' placeholder='Email'/>");
			pw.print("</div>");
			pw.print("<div class='form-group'>");
			pw.print("<label for='password1'>Password</label>");
			pw.print("<input type='password' class='form-control' value='"+ob.getPassword()+"'  name='password' id='password1' placeholder='Password'/>");
			pw.print("</div>  ");
			pw.print("<div class='form-group'>");
			pw.print("<label for='phone1'>Phone Number</label>");
			pw.print("<input type='number' class='form-control' value='"+ob.getPhone()+"'  name='phone' id='phone1' placeholder='Phone'/>");
			pw.print("</div>");
			pw.print("<button type='submit' class='btn btn-primary'>Update</button>");
			pw.print("</form>");
			pw.println("</div>");
			request.getRequestDispatcher("footer.html").include(request, response);
			pw.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
