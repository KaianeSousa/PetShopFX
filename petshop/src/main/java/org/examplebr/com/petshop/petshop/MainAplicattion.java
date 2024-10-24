package org.examplebr.com.petshop.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAplicattion extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            changeScene("main.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Pet Shop");
        primaryStage.show();
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplicattion.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
