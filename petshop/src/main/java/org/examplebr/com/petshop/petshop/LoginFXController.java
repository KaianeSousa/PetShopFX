package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoginFXController {

    @FXML
    private Button buttonLogin;


    @FXML
    public void initialize() {
        // Configura os eventos de clique para cada botão
        buttonLogin.setOnAction(event -> {
            try {
                HelloApplication.changeScene("servicos.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
