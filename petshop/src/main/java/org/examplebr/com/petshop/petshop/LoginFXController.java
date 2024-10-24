package org.examplebr.com.petshop.petshop;

import repository.ConectarBancoDeDados;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String email = emailField.getText().trim();
            String senha = senhaField.getText().trim();

            if (autenticarUsuario(email, senha)) {
                try {
                    MainAplicattion.changeScene("cadastroAnimal.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                    mostrarMensagem(AlertType.ERROR, "Erro ao Trocar de Tela", "Erro: ", e.getMessage());
                }
            } else {
                mostrarMensagem(AlertType.ERROR, "Falha no Login", "Credenciais inválidas", "Por favor, verifique o e-mail e a senha.");
            }
        });

        buttonCancelar.setOnAction(event -> {
            try {
                MainAplicattion.changeScene("main.fxml");
            } catch (IOException e) {
                mostrarMensagem(AlertType.ERROR, "Erro ao voltar para a tela inicial", "Erro: ", e.getMessage());
            }
        });
    }

    private boolean autenticarUsuario(String email, String senha) {
        String sql = "SELECT senha FROM clientes WHERE email = ?";

        try (Connection conn = ConectarBancoDeDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String senhaCriptografada = rs.getString("senha");
                String senhaDescriptografada = Password.decrypt(senhaCriptografada);
                return senhaDescriptografada.equals(senha);
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarMensagem(AlertType.ERROR, "Erro no Banco de Dados", "Falha na autenticação", "Erro: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            mostrarMensagem(AlertType.ERROR, "Erro de Descriptografia", "Não foi possível descriptografar a senha.", "Erro: " + e.getMessage());
            return false;
        }
    }

    private void mostrarMensagem(AlertType tipo, String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}
