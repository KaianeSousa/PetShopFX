package org.examplebr.com.petshop.petshop;

import entities.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.AnimalRepository;

import java.sql.SQLException;
import java.util.Optional;

public class VerAnimaisFXController {

    @FXML
    private TableView<Animal> tableViewAnimais;

    @FXML
    private TableColumn<Animal, Integer> columnID;

    @FXML
    private TableColumn<Animal, String> columnNome;

    @FXML
    private TableColumn<Animal, String> columnRaca;

    @FXML
    private TableColumn<Animal, String> columnGenero;

    @FXML
    private TableColumn<Animal, String> columnIdade;

    @FXML
    private TableColumn<Animal, String> columnTipo;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    private final AnimalRepository animalRepository = new AnimalRepository();
    private final ObservableList<Animal> listaAnimais = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarAnimais();

        buttonDelete.setOnAction(event -> deletarAnimalSelecionado());
        buttonEdit.setOnAction(event -> editAction());
    }

    private void configurarColunas() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
        columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columnIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
    }

    private void carregarAnimais() {
        try {
            listaAnimais.clear();
            listaAnimais.addAll(animalRepository.buscarAnimais());
            tableViewAnimais.setItems(listaAnimais);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletarAnimalSelecionado() {
        Animal animalSelecionado = tableViewAnimais.getSelectionModel().getSelectedItem();
        if (animalSelecionado != null) {
            try {
                animalRepository.removerAnimal(animalSelecionado);
                listaAnimais.remove(animalSelecionado);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void editAction() {
        Animal animalSelecionado = tableViewAnimais.getSelectionModel().getSelectedItem();
        if (animalSelecionado != null) {
            String novoNome = solicitarInput("Editar Animal", "Novo Nome:", animalSelecionado.getNome());
            if (novoNome == null || novoNome.trim().isEmpty()) {
                return;
            }

            String novoTipo = solicitarInput("Editar Animal", "Novo Tipo:", animalSelecionado.getTipo());
            if (novoTipo == null || novoTipo.trim().isEmpty()) {
                return;
            }

            String novaRaca = solicitarInput("Editar Animal", "Nova Raça:", animalSelecionado.getRaca());
            if (novaRaca == null || novaRaca.trim().isEmpty()) {
                return;
            }

            String novoGenero = solicitarInput("Editar Animal", "Novo Gênero:", animalSelecionado.getGenero());
            if (novoGenero == null || novoGenero.trim().isEmpty()) {
                return;
            }

            String novaIdade = solicitarInput("Editar Animal", "Nova Idade:", animalSelecionado.getIdade());
            if (novaIdade == null || novaIdade.trim().isEmpty()) {
                return;
            }

            animalSelecionado.setNome(novoNome);
            animalSelecionado.setTipo(novoTipo);
            animalSelecionado.setRaca(novaRaca);
            animalSelecionado.setGenero(novoGenero);
            animalSelecionado.setIdade(novaIdade);

            try {
                animalRepository.atualizarAnimal(animalSelecionado);
                tableViewAnimais.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String solicitarInput(String titulo, String mensagem, String valorAtual) {
        TextInputDialog dialog = new TextInputDialog(valorAtual);
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(mensagem);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
