package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement ps = null;

		try {
			
             conn = DB.getConnection();
             ps = conn.createStatement();
             
             int rows1 = ps.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
	         
             int x = 1;
             if (x < 2) {
            	 throw new SQLException("Fake error"); // simulando um erro
             }
             
             int rows2 = ps.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
             
             
             System.out.println("Rows 1: " + rows1);
             System.out.println("Rows 2: " + rows2);
             

             
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}
}