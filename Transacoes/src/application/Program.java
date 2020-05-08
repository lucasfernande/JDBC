package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement ps = null;

		try {
			
             conn = DB.getConnection();
             
             conn.setAutoCommit(false); // com esse m�todo, todas as opera��es n�o ser�o confirmadas automaticamente, 
                                        // dependendo de uma confirma��o expl�cita do programador
             
             ps = conn.createStatement();
             
             int rows1 = ps.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
	         
             /*
             int x = 1;
             if (x < 2) {
            	 throw new SQLException("Fake error"); // simulando um erro
             }
             */
             
             int rows2 = ps.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
             
             conn.commit(); // somente aqui a transa��o ser� confirmada
             
             System.out.println("Rows 1: " + rows1);
             System.out.println("Rows 2: " + rows2);
             

             
		} catch (SQLException e) {
			try {
				conn.rollback(); // o rollback() volta para o banco de dados antes da transa��o
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}
}