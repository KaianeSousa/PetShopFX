package repository;

import entities.Animal;
import entities.Servico;
import entities.Agendamento;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AnimalServiceRepository {

    public void adicionarAnimalEServico(Animal animal, Servico servico, Agendamento agendamento) throws SQLException, ClassNotFoundException {
        String sqlInsertAnimal = "INSERT INTO animais (nome, raca, genero, idade) VALUES (?, ?, ?, ?)";
        String sqlInsertServico = "INSERT INTO servicos (nome, classificacao) VALUES (?, ?)";
        String sqlInsertAgendamento = "INSERT INTO agendamentos (animal_id, servico_id, data, horario) VALUES (?, ?, ?, ?)";

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
                stmtAnimal.setString(3, animal.getIdade());
                stmtAnimal.setString(4, animal.getGenero());
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

    public List<Animal> buscarTodosAnimais() throws SQLException, ClassNotFoundException {
        String query = "SELECT id, nome, raca, genero, idade FROM animais";
        List<Animal> listaAnimais = new ArrayList<>();

        try (Connection connection = ConectarBancoDeDados.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String raca = resultSet.getString("raca");
                String genero = resultSet.getString("genero");
                String idade = resultSet.getString("idade");

                Animal animal = new Animal(nome, idade, genero, raca);
                animal.setId(id);
                listaAnimais.add(animal);
            }
        }
        return listaAnimais;
    }
}
