package repository;

import entities.Animal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    public void adicionarAnimal(Animal animal) throws SQLException {

        String sql = "INSERT INTO animais (NOME, RACA, IDADE, TIPO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConectarBancoDeDados.getConnection().prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getIdade());
            stmt.setString(4, animal.getTipo());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Animal> buscarAnimais() throws SQLException {

        String sql = "SELECT * FROM animais";
        List<Animal> animais = new ArrayList<>();

        try (PreparedStatement stmt = ConectarBancoDeDados.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("RACA"),
                        rs.getString("TIPO"),
                        rs.getString("IDADE")
                );
                animais.add(animal);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return animais;
    }


    public void atualizarAnimal(Animal animal) throws SQLException {

        String sql = "UPDATE animais SET NOME = ?, RACA = ?, IDADE = ?, TIPO = ? WHERE ID = ?";

        try (PreparedStatement stmt = ConectarBancoDeDados.getConnection().prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getIdade());
            stmt.setString(4, animal.getTipo());
            stmt.setInt(5, animal.getId());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerAnimal(Animal animal) throws SQLException {

        String sql = "DELETE FROM animais WHERE NOME = ? AND RACA = ? AND TIPO = ? AND IDADE = ?";

        try (PreparedStatement stmt = ConectarBancoDeDados.getConnection().prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getRaca());
            stmt.setString(3, animal.getTipo());
            stmt.setString(4, animal.getIdade());
            stmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
