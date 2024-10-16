package repository;

import entities.Animal;
import entities.Servico;
import entities.Agendamento;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnimalServiceRepository {

    public void adicionarAnimalEServico(Animal animal, Servico servico, Agendamento agendamento) throws SQLException, ClassNotFoundException {
        String sqlInsertAnimal = "INSERT INTO animal (nome, raca, genero, idade) VALUES (?, ?, ?, ?)";
        String sqlInsertServico = "INSERT INTO servico (nome, classificacao) VALUES (?, ?)";
        String sqlInsertAgendamento = "INSERT INTO agendamento (animal_id, servico_id, data, horario) VALUES (?, ?, ?, ?)";

        LocalDate dataAgendamento = agendamento.getData().toLocalDate();
        String dataFormatada = dataAgendamento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (dataFormatada == null || dataFormatada.isEmpty()) {
            throw new SQLException("Data do agendamento não pode ser nula ou vazia.");
        }

        try (Connection connection = ConectarBancoDeDados.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stmtAnimal = connection.prepareStatement(sqlInsertAnimal, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement stmtServico = connection.prepareStatement(sqlInsertServico, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement stmtAgendamento = connection.prepareStatement(sqlInsertAgendamento)) {


                stmtAnimal.setString(1, animal.getNome());
                stmtAnimal.setString(2, animal.getRaca());
                stmtAnimal.setString(3, animal.getGenero());
                stmtAnimal.setString(4, animal.getIdade());
                stmtAnimal.executeUpdate();

                ResultSet rsAnimal = stmtAnimal.getGeneratedKeys();
                if (!rsAnimal.next()) {
                    throw new SQLException("Falha ao obter o ID do animal.");
                }
                int animalId = rsAnimal.getInt(1);

                stmtServico.setString(1, servico.getNome());
                stmtServico.setString(2, servico.getClassificacao().toString());
                stmtServico.executeUpdate();

                ResultSet rsServico = stmtServico.getGeneratedKeys();
                if (!rsServico.next()) {
                    throw new SQLException("Falha ao obter o ID do serviço.");
                }
                int servicoId = rsServico.getInt(1);

                stmtAgendamento.setInt(1, animalId);
                stmtAgendamento.setInt(2, servicoId);
                stmtAgendamento.setString(3, dataFormatada);
                stmtAgendamento.setString(4, agendamento.getHorario().toString());
                stmtAgendamento.executeUpdate();

                connection.commit();
            } catch (SQLException e) {

                connection.rollback();
                throw new SQLException("Erro ao adicionar animal e serviço, transação revertida: " + e.getMessage(), e);
            } finally {

                connection.setAutoCommit(true);
            }
        }
    }
}
