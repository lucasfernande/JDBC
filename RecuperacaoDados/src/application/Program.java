package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("select * from department"); // executando consulta e retorna na vari�vel rs do tipo
																// ResultSet

			while (rs.next()) { // o m�todo next() percorre cada linha da tabela de banco de dados, e quando n�o encontra nada retorna null
							
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        finally {
        	DB.closeResultSet(rs);
        	DB.closeStatement(st);
        	DB.closeConnection();
        }
		
	}

}
