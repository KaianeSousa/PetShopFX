package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDeDados {

    private static final String URL = "jdbc:mysql://localhost:3307/petshop";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexão com o Banco de Dados estabelecida com sucesso!");
        return connection;
    }

    public static void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public static void closeConnection(Connection connection, java.sql.Statement stmt, java.sql.ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar Statement: " + e.getMessage());
            }
        }
        closeConnection(connection); 
    }
}
