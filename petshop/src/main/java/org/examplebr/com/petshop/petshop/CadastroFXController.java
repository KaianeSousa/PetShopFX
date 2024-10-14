package org.examplebr.com.petshop.petshop;

import entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repository.ClienteRepository;

import java.sql.SQLException;

public class CadastroFXController {

    @FXML
    private TextField textNome;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textTelefone;

    @FXML
    private TextField textEndereco;

    @FXML
    private TextField textSenha;

    @FXML
    private Button buttonCadastrar;

    private ClienteRepository clienteRepository;

    public CadastroFXController() {
        clienteRepository = new ClienteRepository();
    }

    @FXML
    private void initialize() {
        // Verifica se buttonCadastrar não é nulo antes de adicionar o evento
        if (buttonCadastrar != null) {
            buttonCadastrar.setOnAction(this::handleCadastro);
        }
    }

    @FXML
    private void handleCadastro(ActionEvent event) {
        String nome = textNome.getText();
        String email = textEmail.getText();
        String telefone = textTelefone.getText();
        String endereco = textEndereco.getText();
        String senha = textSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
            exibirMensagem("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setSenha(senha);

        try {
            clienteRepository.adicionarCliente(cliente);
            exibirMensagem("Sucesso", "Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();
        } catch (SQLException e) {
            exibirMensagem("Erro", "Erro ao cadastrar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void exibirMensagem(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        textNome.clear();
        textEmail.clear();
        textTelefone.clear();
        textEndereco.clear();
        textSenha.clear();
    }
}
