package org.examplebr.com.petshop.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministradorFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carregando o arquivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/examplebr/com/petshop/petshop/administrador.fxml"));

        // Configurando a cena e o palco
        Scene scene = new Scene(fxmlLoader.load(), 750, 550);
        stage.setTitle("Área do Administrador - PetShop");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
