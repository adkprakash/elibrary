package org.library.util;
import java.sql.*;
public class DBConnection {
	public static Connection getConnection(){
        final String username = "root";
        final String password = "";
        final String url = "jdbc:mysql://localhost:3306/elibrary";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
