package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button buttonEntrar;

    @FXML
    private Button buttonCadastro;

    @FXML
    private Button buttonAdministrar;

    @FXML
    public void initialize() {

        if (buttonEntrar != null) {
            buttonEntrar.setOnAction(event -> {
                try {
                    MainAplicattion.changeScene("login.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("Botão Entrar não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }

        if (buttonCadastro != null) {
            buttonCadastro.setOnAction(event -> {
                try {
                    MainAplicattion.changeScene("cadastro.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("Botão Cadastrar não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }

        if (buttonAdministrar != null) {
            buttonAdministrar.setOnAction(event -> {
                try {
                    MainAplicattion.changeScene("loginAdm.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.err.println("Botão Administrar não foi inicializado. Verifique o fx:id no arquivo FXML.");
        }
    }
}
