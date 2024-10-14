package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdministradorFXController {

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private Button buttonLogin;

    // Método de inicialização
    @FXML
    public void initialize() {
        // Configurações adicionais ao inicializar
        buttonLogin.setOnAction(event -> validarLogin());
    }

    // Método para validar o login
    private void validarLogin() {
        String email = textFieldEmail.getText();
        String senha = textFieldSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            exibirAlerta("Campos obrigatórios", "Por favor, preencha todos os campos.");
        } else {
            // Lógica de autenticação pode ser inserida aqui
            if (email.equals("admin@petshop.com") && senha.equals("12345")) {
                exibirAlerta("Login bem-sucedido", "Bem-vindo ao sistema, administrador.");
            } else {
                exibirAlerta("Login falhou", "E-mail ou senha incorretos.");
            }
        }
    }

    // Método para exibir uma mensagem de alerta
    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
