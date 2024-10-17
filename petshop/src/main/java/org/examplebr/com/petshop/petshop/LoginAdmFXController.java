package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginAdmFXController {

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private Button buttonLogin;

    @FXML
    public void initialize() {

        buttonLogin.setOnAction(event -> validarLogin());
    }

    private void validarLogin() {
        String email = textFieldEmail.getText();
        String senha = textFieldSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {

            if (email.equals("admin@petshop.com") && senha.equals("12345")) {
                exibirAlerta("Login bem-sucedido", "Bem-vindo ao sistema, administrador.");
            } else {
                exibirAlerta("Login falhou", "E-mail ou senha incorretos.");
            }
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
