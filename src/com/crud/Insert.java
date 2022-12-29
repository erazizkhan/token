package com.crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@SuppressWarnings("serial")
public class Insert extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Connection connection;
		PrintWriter out = res.getWriter();
		try {
			if(req.getParameter("id") !="" && req.getParameter("name")!="" && req.getParameter("salary")!="") {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","root");
				PreparedStatement stmt = connection.prepareStatement("insert into mytable(id,name,salary)values(?,?,?)");
				int id = Integer.parseInt(req.getParameter("id"));
				String name = req.getParameter("name");
				int salary = Integer.parseInt(req.getParameter("salary"));
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setInt(3, salary);
				int result = stmt.executeUpdate();
				connection.close();
				out.println("<html>");
				out.println("<body>");
				out.println("<h2>"+result+" record inserted</h2>");
				out.println("<h2> <a href='index.html'>Index</a> || <a href='list'>List</a> </h2>");
				out.println("</body>");
				out.println("</html>");	
			}
			else
			{
				out.println("invalid");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
