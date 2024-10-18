package org.examplebr.com.petshop.petshop;

import entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.ClienteRepository;

import java.io.IOException;

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

    @FXML
    private Button buttonCancelar;

    private ClienteRepository clienteRepository;

    public CadastroFXController() {
        clienteRepository = new ClienteRepository();
    }

    @FXML
    private void initialize() {
        if (buttonCadastrar != null) {
            buttonCadastrar.setOnAction(this::handleCadastro);
        }
        if (buttonCancelar != null) {
            buttonCancelar.setOnAction(this::handleCancelar);
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

        try {

            String senhaCriptografada = Password.encrypt(senha);

            Cliente cliente = new Cliente(0, nome, endereco, telefone, email, senhaCriptografada);

            clienteRepository.adicionarCliente(cliente);
            exibirMensagem("Sucesso", "Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();

            fecharTela();

        } catch (Exception e) {
            exibirMensagem("Erro", "Erro ao cadastrar cliente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void handleCancelar(ActionEvent event) {
        if (MainAplicattion.primaryStage != null) {
            try {
                MainAplicattion.changeScene("main.fxml");
            } catch (IOException e) {
                exibirMensagem("Erro", "Erro ao voltar para a tela inicial: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            exibirMensagem("Erro", "A tela principal não foi inicializada corretamente.", Alert.AlertType.ERROR);
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

    private void fecharTela() {
        Stage stage = (Stage) buttonCadastrar.getScene().getWindow();
        stage.close();
    }
}
