package org.examplebr.com.petshop.petshop;

import entities.Animal;
import entities.Servico;
import entities.Agendamento;
import repository.AnimalServiceRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServicosFXController {

    @FXML
    private TextField textNome;

    @FXML
    private TextField textRaca;

    @FXML
    private TextField textIdade;

    @FXML
    private ComboBox<String> generoBox;

    @FXML
    private ComboBox<String> servicosBox;

    @FXML
    private ComboBox<String> horaBox;

    @FXML
    private DatePicker datebox;

    @FXML
    private Button buttonCadastrar;

    @FXML
    public void initialize() {
        inicializarComboBox();
        configurarCalendario();
        if (buttonCadastrar != null) {
            buttonCadastrar.setOnAction(event -> registrarServico());
        }
    }

    private void inicializarComboBox() {
        if (generoBox != null) {
            generoBox.getItems().clear();
            generoBox.getItems().addAll("Macho", "Fêmea");
            generoBox.setPromptText("Selecione o gênero");
            generoBox.getSelectionModel().clearSelection();
        }
        if (servicosBox != null) {
            servicosBox.getItems().clear();
            servicosBox.getItems().addAll("Banho", "Tosa", "Consulta Veterinária", "Passeio");
            servicosBox.setPromptText("Selecione o serviço");
            servicosBox.getSelectionModel().clearSelection();
        }
        if (horaBox != null) {
            horaBox.getItems().clear();
            horaBox.getItems().addAll("09:00", "10:00", "11:00", "14:00", "15:00", "16:00");
            horaBox.setPromptText("Selecione o horário");
            horaBox.getSelectionModel().clearSelection();
        }
    }

    private void configurarCalendario() {
        if (datebox != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            datebox.setConverter(new StringConverter<>() {
                @Override
                public String toString(LocalDate date) {
                    return (date != null) ? formatter.format(date) : "";
                }

                @Override
                public LocalDate fromString(String string) {
                    return (string != null && !string.isEmpty()) ? LocalDate.parse(string, formatter) : null;
                }
            });
            datebox.setPromptText("Selecione a data");
            datebox.setShowWeekNumbers(false);
            datebox.setValue(LocalDate.now());
        }
    }

    private void registrarServico() {
        String nome = (textNome != null) ? textNome.getText().trim() : "";
        String raca = (textRaca != null) ? textRaca.getText().trim() : "";
        String idade = (textIdade != null) ? textIdade.getText().trim() : "";
        String genero = (generoBox != null && generoBox.getValue() != null) ? generoBox.getValue().trim() : "";
        String servicoNome = (servicosBox != null && servicosBox.getValue() != null) ? servicosBox.getValue().trim() : "";
        String horario = (horaBox != null && horaBox.getValue() != null) ? horaBox.getValue().trim() : "";
        LocalDate data = (datebox != null && datebox.getValue() != null) ? datebox.getValue() : null;


        if (data == null) {
            mostrarMensagem(AlertType.ERROR, "Erro de Validação", "Data não selecionada",
                    "Por favor, selecione uma data válida.");
            return;
        }

        String dataFormatada = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (!validarCampos(nome, raca, idade, genero, servicoNome, horario, data)) {
            return;
        }


        Animal animal = new Animal(nome, raca, genero, idade);

        Servico.ClassificacaoServico classificacaoServico;
        switch (servicoNome) {
            case "Banho":
                classificacaoServico = Servico.ClassificacaoServico.BANHO;
                break;
            case "Tosa":
                classificacaoServico = Servico.ClassificacaoServico.TOSA;
                break;
            case "Consulta Veterinária":
                classificacaoServico = Servico.ClassificacaoServico.CONSULTA_VETERINÁRIA;
                break;
            case "Passeio":
                classificacaoServico = Servico.ClassificacaoServico.PASSEIO;
                break;
            default:
                mostrarMensagem(AlertType.ERROR, "Erro", "Serviço inválido", "Por favor, selecione um serviço válido.");
                return;
        }
        Servico servicoObj = new Servico(servicoNome, classificacaoServico);

        Agendamento agendamento = new Agendamento(animal, servicoObj, data, LocalTime.parse(horario));

        AnimalServiceRepository repository = new AnimalServiceRepository();
        try {
            repository.adicionarAnimalEServico(animal, servicoObj, agendamento);
            mostrarMensagem(AlertType.INFORMATION, "Sucesso", "Pet registrado com sucesso!",
                    String.format("Nome: %s%nRaça: %s%nIdade: %s%nGênero: %s%nServiço: %s%nHorário: %s%nData: %s",
                            nome, raca, idade, genero, servicoNome, horario, dataFormatada));
        } catch (SQLException | ClassNotFoundException e) {
            mostrarMensagem(AlertType.ERROR, "Erro ao Registrar", "Não foi possível registrar o serviço.",
                    "Erro: " + e.getMessage());
        }
    }



    private boolean validarCampos(String nome, String raca, String idade, String genero,
                                  String servico, String horario, LocalDate data) {
        if (nome.isEmpty() || raca.isEmpty() || idade.isEmpty() || genero.isEmpty() ||
                servico.isEmpty() || horario.isEmpty() || data == null) {
            mostrarMensagem(AlertType.ERROR, "Erro de Validação", "Campos obrigatórios não preenchidos",
                    "Por favor, preencha todos os campos.");
            return false;
        }
        try {
            int idadeInt = Integer.parseInt(idade);
            if (idadeInt < 0) {
                mostrarMensagem(AlertType.ERROR, "Erro de Validação", "Idade inválida",
                        "A idade deve ser um número positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensagem(AlertType.ERROR, "Erro de Validação", "Idade inválida",
                    "A idade deve ser um número válido.");
            return false;
        }
        return true;
    }

    private void mostrarMensagem(AlertType tipo, String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}
