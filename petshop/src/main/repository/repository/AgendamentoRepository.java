package repository;

import entities.Agendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendamentoRepository {

    public static void salvarAgendamento(Agendamento agendamento) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO agendamentos (data, horario) VALUES (?, ?)";

        Connection connection = ConectarBancoDeDados.getConnection();
        if (connection != null) {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDate(1, agendamento.getData());
                stmt.setTime(2, agendamento.getHorario());

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Agendamento salvo com sucesso! Linhas afetadas: " + rowsAffected);
            } catch (SQLException e) {
                System.out.println("Erro ao salvar o agendamento: " + e.getMessage());
            } finally {
                ConectarBancoDeDados.closeConnection(connection);
            }
        }
    }
}
