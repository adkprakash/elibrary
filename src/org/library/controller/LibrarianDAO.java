package org.library.controller;

import org.library.model.Librarian;
import org.library.util.DBConnection;

import java.sql.*;
import java.util.*;
public class LibrarianDAO {

	public int insertLibrarian(Librarian ob) {
		int count = 0;
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into librarian (name, email, password, phone) values (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ob.getName());
			pst.setString(2, ob.getEmail());
			pst.setString(3, ob.getPassword());
			pst.setLong(4, ob.getPhone());
			count = pst.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return count;
	}
	public List<Librarian> viewLibrarian(){
		List<Librarian> librarianList = new ArrayList();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "select * from librarian";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				Librarian ob = new Librarian();
				ob.setId(rs.getInt("id"));
				ob.setName(rs.getString("name"));
				ob.setEmail(rs.getString("email"));
				ob.setPassword(rs.getString("password"));
				ob.setPhone(rs.getLong("phone"));
				librarianList.add(ob);
			}
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return librarianList;
	}
	public static Librarian viewById(int id){
		Librarian ob = new Librarian();
		try{
			Connection con = DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("select * from librarian where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				ob.setId(rs.getInt(1));
				ob.setName(rs.getString(2));
				ob.setEmail(rs.getString(3));
				ob.setPassword(rs.getString(4));
				ob.setPhone(rs.getLong(5));
			}
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return ob;
	}
	public int deleteLibrarian(Librarian ob) {
		int count = 0;
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from librarian where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, ob.getId());
			count = pst.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return count;
	}
	public int updateLibrarian(Librarian ob) {
		int count = 0;
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update librarian set name=?, email=?, password=?, phone=? where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ob.getName());
			pst.setString(2, ob.getEmail());
			pst.setString(3, ob.getPassword());
			pst.setLong(4, ob.getPhone());
			pst.setInt(5, ob.getId());
			count = pst.executeUpdate();
			con.close();
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return count;
	}
	public boolean register(String email, String password){
		boolean status = false;
		try{
			Connection con = DBConnection.getConnection();
			String sql = "select * from librarian where email=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				status = true;
			}
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
		return status;
	}
}