package org.examplebr.com.petshop.petshop;

import entities.Animal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import repository.AnimalRepository;

import java.io.IOException;

public class CadastrarAnimalFXController {

    @FXML
    private TextField textNome;

    @FXML
    private TextField textTipo;

    @FXML
    private TextField textRaca;

    @FXML
    private ComboBox<String> generoBox;

    @FXML
    private TextField textIdade;

    @FXML
    private Button buttonRegistrar;

    @FXML
    private Button buttonServicos;

    private AnimalRepository animalRepository;

    public CadastrarAnimalFXController() {
        animalRepository = new AnimalRepository();
    }

    @FXML
    private void initialize() {
        generoBox.getItems().addAll("Macho", "Fêmea"); // Inicializando ComboBox
    }

    @FXML
    private void handleRegistrar(ActionEvent event) {
        String nome = textNome.getText();
        String tipo = textTipo.getText();
        String raca = textRaca.getText();
        String genero = generoBox.getValue();
        String idade = textIdade.getText();

        if (nome.isEmpty() || tipo.isEmpty() || raca.isEmpty() || genero == null || idade.isEmpty()) {
            exibirMensagem("Erro", "Todos os campos devem ser preenchidos.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Animal animal = new Animal(0, nome, raca, genero, idade, tipo);
            animalRepository.adicionarAnimal(animal);
            exibirMensagem("Sucesso", "Animal cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();

            Platform.runLater(() -> {
                try {
                    MainAplicattion.changeScene("main.fxml"); // Muda para a tela principal após o cadastro
                } catch (IOException e) {
                    exibirMensagem("Erro", "Erro ao voltar para a tela inicial: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            });

        } catch (Exception e) {
            exibirMensagem("Erro", "Erro ao cadastrar animal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                MainAplicattion.changeScene("main.fxml");
            } catch (IOException e) {
                exibirMensagem("Erro", "Erro ao voltar para a tela inicial: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        });
    }

    @FXML
    private void handleServicos(ActionEvent event) {
        abrirJanelaServicos();
    }

    private void abrirJanelaServicos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/examplebr/com/petshop/petshop/servicos.fxml")); // Caminho correto para o arquivo FXML
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Serviços");
            stage.setScene(new Scene(root, 920, 650));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            exibirMensagem("Erro", "Erro ao abrir a tela de serviços: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void exibirMensagem(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        textNome.clear();
        textTipo.clear();
        textRaca.clear();
        generoBox.setValue(null);
        textIdade.clear();
    }
}
