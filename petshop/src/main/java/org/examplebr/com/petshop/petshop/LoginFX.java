package org.examplebr.com.petshop.petshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFX extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/org/examplebr/com/petshop/petshop/login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }

