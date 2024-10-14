package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonAdministrar;

    @FXML
    public void initialize() {
        // Verifica se os botões foram carregados corretamente
        if (buttonLogin != null) {
            buttonLogin.setOnAction(event -> {
                try {
                    HelloApplication.changeScene("login.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("buttonLogin não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }

        if (buttonCadastrar != null) {
            buttonCadastrar.setOnAction(event -> {
                try {
                    HelloApplication.changeScene("cadastro.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("buttonCadastrar não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }

        if (buttonAdministrar != null) {
            buttonAdministrar.setOnAction(event -> {
                try {
                    HelloApplication.changeScene("administrador.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("buttonAdministrar não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }
    }
}
