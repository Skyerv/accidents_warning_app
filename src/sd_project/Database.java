package sd_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/sd-project";
        String usuario = "postgres";
        String senha = "12345";

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
