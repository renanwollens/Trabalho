package acessoAosDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnectionMysql() {
	                try {
	         return DriverManager.getConnection(
	        	"jdbc:mysql://localhost/database", "root", "");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

    public static Connection getConnectionSQLite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}