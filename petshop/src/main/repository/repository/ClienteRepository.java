package repository;

import entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private Connection connection;

    public ClienteRepository() {
        try {
            this.connection = ConectarBancoDeDados.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (NOME, TELEFONE, EMAIL, ENDERECO, SENHA) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getSenha());
            stmt.executeUpdate();
        }
    }

    public List<Cliente> buscarClientes() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("TELEFONE"),
                        rs.getString("EMAIL"),
                        rs.getString("ENDERECO"),
                        rs.getString("SENHA")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET NOME = ?, TELEFONE = ?, EMAIL = ?, ENDERECO = ?, SENHA = ? WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getSenha());
            stmt.setInt(6, cliente.getId());
            stmt.executeUpdate();
        }
    }

    public void removerCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE ID = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getId());
            stmt.executeUpdate();
        }
    }

    public boolean verificarCredenciais(String email, String senha) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE EMAIL = ? AND SENHA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }
}
