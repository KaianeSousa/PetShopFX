package org.examplebr.com.petshop.petshop;

import entities.Animal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;  // Importação correta para Insets
import repository.AnimalServiceRepository;

import java.sql.SQLException;
import java.util.List;

public class AnimaisFXController {

    @FXML
    private Button buttonVoltar;

    @FXML
    private ListView<VBox> listViewAnimais;

    private final AnimalServiceRepository animalServiceRepository = new AnimalServiceRepository();

    @FXML
    void voltar(ActionEvent event) {
        System.out.println("Voltar para a tela anterior");
    }

    public void carregarDados() {
        try {
            List<Animal> listaAnimais = animalServiceRepository.buscarTodosAnimais();

            listViewAnimais.getItems().clear();

            for (Animal animal : listaAnimais) {
                VBox vbox = new VBox();
                vbox.setPadding(new Insets(10));
                vbox.getChildren().addAll(
                        new Label("ID: " + animal.getId()),
                        new Label("Nome: " + animal.getNome()),
                        new Label("Idade: " + animal.getIdade()),
                        new Label("Raça: " + animal.getRaca())
                );
                listViewAnimais.getItems().add(vbox);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar os dados dos animais: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        carregarDados();
    }
}
