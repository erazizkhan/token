package com.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@SuppressWarnings("serial")
public class Delete extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		PrintWriter out = arg1.getWriter();
		try {
			if(arg0.getParameter("id")!="") {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","root");
				PreparedStatement stmt = connection.prepareStatement("select * from mytable where id=?");
				PreparedStatement stmt2 = connection.prepareStatement("delete from mytable where id=?");
				stmt.setInt(1, Integer.parseInt(arg0.getParameter("id")));
				ResultSet rs = stmt.executeQuery();
				boolean r = rs.next();
				if(!r) {
					out.println("<html>");
					out.println("<body>");
					out.println("<h2> No user found</h2>");
					out.println("<h2> <a href='index.html'>Index</a> || <a href='list'>List</a> </h2>");
					out.println("</body>");
					out.println("</html>");
				}else {
					out.println("<html>");
					out.println("<body>");
					out.println("<h2>ID: "+ rs.getInt(1) +"</h2>");
					out.println("<h2>Name: "+ rs.getString(2) +"</h2>");
					out.println("<h2>Salary: "+ rs.getInt(3) +"</h2>");
					stmt2.setInt(1, Integer.parseInt(arg0.getParameter("id")));
					int i = stmt2.executeUpdate();
					out.println("<h2>"+i+" Record Deleted</h2>");
					out.println("<h2> <a href='index.html'>Index</a> || <a href='list'>List</a> </h2>");
					out.println("</body>");
					out.println("</html>");
				}
				connection.close();
				
			}else {
				out.println("<html>");
				out.println("<body>");
				out.println("<h2> Please enter ID</h2>");
				out.println("<h2><a href='delete.html'>Delete</a></h2>");
				out.println("<h2> <a href='index.html'>Index</a> || <a href='insert.html'>Insert</a> </h2>");
				out.println("</body>");
				out.println("</html>");
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
