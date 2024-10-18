package org.examplebr.com.petshop.petshop;

import entities.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VerClientesFXController {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnID;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    @FXML
    private TableColumn<Cliente, String> columnEndereco;
    @FXML
    private TableColumn<Cliente, String> columnTelefone;
    @FXML
    private TableColumn<Cliente, String> columnEmail;
    @FXML
    private TableColumn<Cliente, String> columnSenha;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonEdit;

    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ObservableList<Cliente> clienteList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarClientes();
    }

    private void configurarColunas() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
    }

    private void carregarClientes() {
        try {
            List<Cliente> clientes = clienteRepository.buscarClientes();
            clienteList.setAll(clientes);
            tableViewClientes.setItems(clienteList);
        } catch (SQLException e) {
            e.printStackTrace();
            exibirMensagem("Erro", "Erro ao carregar clientes: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleDeleteAction() {
        Cliente clienteSelecionado = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja excluir o cliente selecionado?");

            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == javafx.scene.control.ButtonType.OK) {
                try {
                    clienteRepository.removerCliente(clienteSelecionado);
                    clienteList.remove(clienteSelecionado);
                    exibirMensagem("Sucesso", "Cliente excluído com sucesso!", Alert.AlertType.INFORMATION);
                } catch (SQLException e) {
                    e.printStackTrace();
                    exibirMensagem("Erro", "Erro ao excluir cliente: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            exibirMensagem("Atenção", "Nenhum cliente selecionado.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleEditAction() {
        Cliente clienteSelecionado = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {

            String novoNome = solicitarInput("Editar Cliente", "Novo Nome:", clienteSelecionado.getNome());
            if (novoNome == null || novoNome.trim().isEmpty()) {
                return;
            }

            String novoEndereco = solicitarInput("Editar Cliente", "Novo Endereço:", clienteSelecionado.getEndereco());
            if (novoEndereco == null || novoEndereco.trim().isEmpty()) {
                return;
            }

            String novoTelefone = solicitarInput("Editar Cliente", "Novo Telefone:", clienteSelecionado.getTelefone());
            if (novoTelefone == null || novoTelefone.trim().isEmpty()) {
                return;
            }

            String novoEmail = solicitarInput("Editar Cliente", "Novo Email:", clienteSelecionado.getEmail());
            if (novoEmail == null || novoEmail.trim().isEmpty()) {
                return;
            }

            clienteSelecionado.setNome(novoNome);
            clienteSelecionado.setEndereco(novoEndereco);
            clienteSelecionado.setTelefone(novoTelefone);
            clienteSelecionado.setEmail(novoEmail);

            try {

                clienteRepository.atualizarCliente(clienteSelecionado);
                tableViewClientes.refresh();
                exibirMensagem("Sucesso", "Cliente atualizado com sucesso!", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                exibirMensagem("Erro", "Erro ao atualizar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            exibirMensagem("Atenção", "Nenhum cliente selecionado.", Alert.AlertType.WARNING);
        }
    }

    private String solicitarInput(String titulo, String mensagem, String valorAtual) {
        TextInputDialog dialog = new TextInputDialog(valorAtual);
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(mensagem);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void exibirMensagem(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
