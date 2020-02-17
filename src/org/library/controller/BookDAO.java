package org.library.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.library.model.Book;
import org.library.model.Issue;
import org.library.util.DBConnection;

public class BookDAO {
	public int insertBooks(Book ob) {
		int count = 0;
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into book (callno, name, author, publisher, quantity, issued) value (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ob.getCallno());
			pst.setString(2, ob.getName());
			pst.setString(3, ob.getAuthor());
			pst.setString(4, ob.getPublisher());
			pst.setInt(5, ob.getQuantity());
			pst.setInt(6, 0);
			count = pst.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return count;
	}
	public List<Book> viewBooks(){
		List<Book> bookList = new ArrayList();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "select * from book";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Book ob = new Book();
				ob.setId(rs.getInt("id"));
				ob.setCallno(rs.getString("callno"));
				ob.setName(rs.getString("name"));
				ob.setAuthor(rs.getString("author"));
				ob.setPublisher(rs.getString("publisher"));
				ob.setQuantity(rs.getInt("quantity"));
				ob.setIssued(rs.getInt("issued"));
				bookList.add(ob);
			}
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return bookList;
	}
	public int deleteBook(String callno) {
		int count = 0;
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from book where callno=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, callno);
			count = pst.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return count;
	}
	public int getIssued(String callno){
		int issued=0;
		try{
			Connection con=DBConnection.getConnection();
			String sql = "select * from book where callno=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, callno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				issued = rs.getInt("issued");
			}
			con.close();
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return issued;
	}
	public boolean checkIssue(String callno){
		boolean status = false;
		try{
			Connection con=DBConnection.getConnection();
			String sql = "select * from book where callno=? and quantity > issued";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, callno);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				status = true;
			}
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
		return status;
	}
	public int issueBook(Issue ob) {
		String callno = ob.getCallno();
		boolean status = checkIssue(callno);
		if(status) {
			int count = 0;
			try {
				Connection con = DBConnection.getConnection();
				String sql = "insert into issue (callno, id, name, phone, issueddate, returnstatus) values (?,?,?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, ob.getCallno());
				pst.setString(2, ob.getId());
				pst.setString(3, ob.getName());
				pst.setLong(4, ob.getPhone());
				pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
				pst.setString(6, "No");
				count = pst.executeUpdate();
				if(count > 0) {
					String sql1 = "update book set issued=? where callno=?";
					PreparedStatement st = con.prepareStatement(sql1);
					st.setInt(1, getIssued(callno)+1);
					st.setString(2, callno);
					count = st.executeUpdate();
					con.close();
				}
			}catch(Exception ex) {
				System.out.println(ex);
			}
			return count;
		}else {
			return 0;
		}
	}
	public List<Issue> viewIssuedBook(){
		List<Issue>issuedList = new ArrayList();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "select * from issue";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Issue ob = new Issue();
				ob.setCallno(rs.getString("callno"));
				ob.setName(rs.getString("name"));
				ob.setId(rs.getString("id"));
				ob.setPhone(rs.getLong("phone"));
				ob.setIssueddate(rs.getDate("issueddate"));
				ob.setReturnstatus(rs.getString("returnstatus"));
				issuedList.add(ob);
			}
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return issuedList;
	}
	public int returnBook(String callno, int sid){
		int count = 0;
			try{
				Connection con = DBConnection.getConnection();
				String sql = "update issue set returnstatus='Yes' where callno=? and id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, callno);
				pst.setInt(2, sid);
				count = pst.executeUpdate();
				if(count > 0){
					String sql1 = "update book set issued=? where callno=?";
					PreparedStatement st = con.prepareStatement(sql1);
					st.setInt(1, getIssued(callno)-1);
					st.setString(2, callno);
					count = st.executeUpdate();
				}
				con.close();
			}catch(Exception ex){ 
				System.out.println(ex);
			}
			return count;
	}
}
