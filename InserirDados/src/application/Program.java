package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DB.getConnection();

			ps = conn.prepareStatement
			("INSERT INTO seller"
			+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
			+ "VALUES" + "(?, ?, ?, ?, ?)", // quantidade de campos que vão ser preenchidos (5)
			Statement.RETURN_GENERATED_KEYS); // retorna o id que foi gerado quando o objeto foi inserido no banco

			ps.setString(1, "Mano Brownie"); // o número 1 representa o primeiro interrogação, o segundo parâmetro será o valor que irá substitui-lo
			ps.setString(2, "maninho@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("06/08/1987").getTime()));
			ps.setDouble(4, 2950.00);
			ps.setInt(5, 3);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) { // while deve ser feito pois pode ser retornado um ou mais valores
					int id = rs.getInt(1);
					System.out.println("Done! id: " + id);
				}
			} 
			else {
				System.out.println("No rows affected");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}

	}
}