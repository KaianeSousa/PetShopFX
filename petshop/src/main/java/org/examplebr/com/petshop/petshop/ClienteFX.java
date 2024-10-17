package org.examplebr.com.petshop.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClienteFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/examplebr/com/petshop/petshop/verClientes.fxml"));
        Parent root = loader.load();

        ClienteFXController controller = loader.getController();

        primaryStage.setTitle("Cadastro de Clientes");
        primaryStage.setScene(new Scene(root, 750, 550));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
