package Console;

import java.sql.Connection;
import java.sql.SQLException;

import acessoAosDados.ConnectionFactory;

public class SeedDb {

	public static void main(String[] args) {
		Connection con = ConnectionFactory.getConnectionSQLite();

		try {
			con.createStatement().execute(
				"CREATE TABLE `Aluno` ("+
				  "`id` integer PRIMARY KEY AUTOINCREMENT,"+
				  "`nome` varchar(45) NOT NULL,"+
				  "`nota1` float DEFAULT NULL,"+
				  "`nota2` float DEFAULT NULL"+
					")");
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
