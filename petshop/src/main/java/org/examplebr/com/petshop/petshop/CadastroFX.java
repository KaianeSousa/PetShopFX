package org.examplebr.com.petshop.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(org.examplebr.com.petshop.petshop.HelloApplication.class.getResource("cadastro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 550);
        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
