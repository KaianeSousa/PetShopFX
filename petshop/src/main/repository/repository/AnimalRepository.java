package repository;

import entities.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    private Connection connection;

    public AnimalRepository() {
        try {
            this.connection = ConectarBancoDeDados.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO animais (NOME, TIPO, RACA, GENERO, IDADE) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getTipo());
            stmt.setString(3, animal.getRaca());
            stmt.setString(4, animal.getGenero());
            stmt.setString(5, animal.getIdade());
            stmt.executeUpdate();
        }
    }

    public List<Animal> buscarAnimais() throws SQLException {
        String sql = "SELECT * FROM animais";
        List<Animal> animais = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                Animal animal = new Animal(
                        id,
                        rs.getString("NOME"),
                        rs.getString("TIPO"),
                        rs.getString("RACA"),
                        rs.getString("GENERO"),
                        rs.getString("IDADE")
                );
                animais.add(animal);
            }
        }
        return animais;
    }

    public void atualizarAnimal(Animal animal) throws SQLException {
        String sql = "UPDATE animais SET NOME = ?, TIPO = ?, RACA = ?, GENERO = ?, IDADE = ? WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getTipo());
            stmt.setString(3, animal.getRaca());
            stmt.setString(4, animal.getGenero());
            stmt.setString(5, animal.getIdade());
            stmt.setInt(6, animal.getId());
            stmt.executeUpdate();
        }
    }

    public void removerAnimal(Animal animal) throws SQLException {
        String sql = "DELETE FROM animais WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animal.getId());
            stmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
