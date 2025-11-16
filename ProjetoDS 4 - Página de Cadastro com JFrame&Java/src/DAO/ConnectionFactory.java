package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	// Método para obter a conexão com o banco de dados
	public Connection getConnection() {
		
		try {
			// Configura a conexão com o banco de dados MySQL
			return DriverManager.getConnection("jdbc:mysql://localhost/bdcadastro", "root", "");
		}
		// Tratamento de exceção em caso de falha na conexão
		catch(SQLException e) {
			// Lança uma RuntimeException em caso de erro
			throw new RuntimeException();
		}
	}

}
