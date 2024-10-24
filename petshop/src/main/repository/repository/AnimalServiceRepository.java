package repository;

import entities.Animal;
import entities.Servico;
import entities.Agendamento;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnimalServiceRepository {

    public void adicionarAnimalEServico(Animal animal, Servico servico, Agendamento agendamento) throws SQLException, ClassNotFoundException {
        String sqlInsertAnimal = "INSERT INTO animais (nome, raca, genero, idade) VALUES (?, ?, ?, ?)";
        String sqlInsertServico = "INSERT INTO servicos (nome, classificacao) VALUES (?, ?)";
        String sqlInsertAgendamento = "INSERT INTO agendamentos (animal_id, servico_id, data, horario) VALUES (?, ?, ?, ?)";

        // Verifique se a data do agendamento é nula
        if (agendamento.getData() == null) {
            throw new SQLException("Data do agendamento não pode ser nula.");
        }

        LocalDate dataAgendamento = agendamento.getData().toLocalDate();
        String dataFormatada = dataAgendamento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try (Connection connection = ConectarBancoDeDados.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement stmtAnimal = connection.prepareStatement(sqlInsertAnimal, Statement.RETURN_GENERATED_KEYS)) {
                stmtAnimal.setString(1, animal.getNome());
                stmtAnimal.setString(2, animal.getRaca());
                stmtAnimal.setString(3, animal.getGenero());
                stmtAnimal.setInt(4, Integer.parseInt(animal.getIdade()));
                stmtAnimal.executeUpdate();

                try (ResultSet generatedKeys = stmtAnimal.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long animalId = generatedKeys.getLong(1);

                        try (PreparedStatement stmtServico = connection.prepareStatement(sqlInsertServico, Statement.RETURN_GENERATED_KEYS)) {
                            stmtServico.setString(1, servico.getNome());
                            stmtServico.setString(2, servico.getClassificacao().name());
                            stmtServico.executeUpdate();

                            try (ResultSet generatedKeysServico = stmtServico.getGeneratedKeys()) {
                                if (generatedKeysServico.next()) {
                                    long servicoId = generatedKeysServico.getLong(1);

                                    try (PreparedStatement stmtAgendamento = connection.prepareStatement(sqlInsertAgendamento)) {
                                        stmtAgendamento.setLong(1, animalId);
                                        stmtAgendamento.setLong(2, servicoId);
                                        stmtAgendamento.setString(3, dataFormatada);
                                        stmtAgendamento.setString(4, agendamento.getHorario().toString());
                                        stmtAgendamento.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public void adicionarServico(Agendamento agendamento) {

    }
}
