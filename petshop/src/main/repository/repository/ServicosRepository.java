package repository;

import entities.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicosRepository {

    private Connection connection;

    public ServicosRepository(Connection connection) {
        this.connection = connection;
    }

    public void registrarServico(Servico servico, String data, String hora) throws SQLException {
        String sql = "INSERT INTO servicos (nome, classificacao, data, hora) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, servico.getNome());
            statement.setString(2, servico.getClassificacao().name());
            statement.setString(3, data);
            statement.setString(4, hora);

            statement.executeUpdate();
        }
    }
}
