package user_demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/fetch")

public class FetchServlet  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	           
		int id=Integer.parseInt(req.getParameter("id"));
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String qry="select * from user_demo where id=?";
		try {
			Class.forName(UserUtility.DRIVER);
			con = DriverManager.getConnection(UserUtility.URL, UserUtility.USER, UserUtility.PASSWORD);
			pst = con.prepareStatement(qry);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			PrintWriter writer=resp.getWriter();
			if(rs.next()) {
				writer.write("<html><body><h1>ID:"+rs.getInt(1)+"<h1>");
				writer.write("<h1>Name:"+rs.getString("name")+"<h1>");
				writer.write("<h1>Age:"+rs.getInt("age")+"<h1>");
				writer.write("<h1>:Phone"+rs.getLong("phone")+"</h1></body></html>");
				
				
			}
			else {
				writer.write("<html><body><h1>Entered id is invalid</h1></body></html>");
			}
		}
	catch (SQLException | ClassNotFoundException e) {
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
			if (rs != null) {
				try {
					con.close();
					System.out.println("Resultset is closed");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}
	}
}



		
	

}
