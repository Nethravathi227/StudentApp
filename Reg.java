package user_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
//import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.print.event.PrintJobAdapter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/reg")

public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse respond)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("nm");
		int age = Integer.parseInt(request.getParameter("age"));
	 
		 long phone = Long.parseLong(request.getParameter("ph"));
		String Password = request.getParameter("ps");
		Connection con = null;
		PreparedStatement pst = null;
		String qry = "insert into user_demo values(?,?,?,?,?)";
		try {
			Class.forName(UserUtility.DRIVER);
			con = DriverManager.getConnection(" com.mysql.cj.jdbc.Driver");
			pst = con.prepareStatement(qry);
			pst.setInt(1, id);
			
			pst.setString(2, name);
			pst.setInt(3, age);
			pst.setLong(4, phone);
			pst.setString(5, Password);
			pst.executeUpdate();
			PrintWriter writer = respond.getWriter();
			writer.write("<html><body><h1>User Saved successfully <h1><body><html>");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
	}finally{
			
			if (con != null) {
				try {
					con.close();
					System.out.println("Connection is closed");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
				if (pst != null) {
					try {
						pst.close();
						System.out.println("Prepared Statement is closed");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			}

		}
	}

}
   
