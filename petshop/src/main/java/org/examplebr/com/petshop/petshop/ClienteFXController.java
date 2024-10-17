package org.examplebr.com.petshop.petshop;

import entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import repository.ClienteRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClienteFXController {

    @FXML
    private Button buttonVoltar;

    @FXML
    private ListView<String> listViewClientes;

    private final ClienteRepository clienteRepository = new ClienteRepository();
    private Stage stageAnterior;

    @FXML
    void voltar(ActionEvent event) {
        try {
            // Abrir a tela do administrador
            abrirTelaAdministrador();
            // Fechar a tela atual
            ((Stage) buttonVoltar.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do administrador: " + e.getMessage());
        }
    }

    private void abrirTelaAdministrador() throws IOException {

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/examplebr/com/petshop/petshop/administrador.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Área do Administrador");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setStageAnterior(Stage stageAnterior) {
        this.stageAnterior = stageAnterior;
    }

    public void carregarDados() {
        try {
            List<Cliente> listaClientes = clienteRepository.buscarClientes();

            listViewClientes.getItems().clear();

            for (Cliente cliente : listaClientes) {
                String clienteInfo = "ID: " + cliente.getId() +
                        ", Nome: " + cliente.getNome() +
                        ", Telefone: " + cliente.getTelefone() +
                        ", Email: " + cliente.getEmail() +
                        ", Endereço: " + cliente.getEndereco();
                listViewClientes.getItems().add(clienteInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados dos clientes: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        carregarDados();
    }
}
