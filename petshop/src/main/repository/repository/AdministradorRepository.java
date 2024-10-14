package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorRepository {

    private Connection connection;

    public AdministradorRepository(Connection connection) {

        this.connection = connection;
    }

    public boolean verificarCredenciais(String email, String senha) {

        String sql = "SELECT * FROM administradores WHERE email = ? AND senha = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, senha);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
