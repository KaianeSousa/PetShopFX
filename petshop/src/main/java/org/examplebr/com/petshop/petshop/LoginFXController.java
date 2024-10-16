package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginFXController {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    public void initialize() {

        buttonLogin.setOnAction(event -> {
            try {
                MainAplicattion.changeScene("servicos.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonCancelar.setOnAction(event -> {
            System.out.println("Botão Cancelar clicado!");
            try {
                MainAplicattion.changeScene("main.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
