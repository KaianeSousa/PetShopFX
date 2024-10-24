package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import repository.AdministradorRepository;
import repository.ConectarBancoDeDados;

import java.sql.Connection;

public class LoginAdmFXController {

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancelar;

    @FXML
    public void initialize() {
        buttonLogin.setOnAction(event -> validarLogin());

        buttonCancelar.setOnAction(event -> voltarParaTelaAnterior());
    }

    private void validarLogin() {
        String email = textFieldEmail.getText();
        String senha = textFieldSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {
            try (Connection connection = ConectarBancoDeDados.getConnection()) {
                AdministradorRepository administradorRepository = new AdministradorRepository(connection);

                if (administradorRepository.verificarCredenciais(email, senha)) {
                    exibirAlerta("Login bem-sucedido", "Bem-vindo ao sistema, administrador.");
                    abrirTelaAdministrador();
                } else {
                    exibirAlerta("Login falhou", "E-mail ou senha incorretos.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                exibirAlerta("Erro", "Não foi possível conectar ao banco de dados.");
            }
        }
    }

    private void abrirTelaAdministrador() {
        try {
            MainAplicattion.changeScene("verClientes.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void voltarParaTelaAnterior() {
        try {
            MainAplicattion.changeScene("main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
