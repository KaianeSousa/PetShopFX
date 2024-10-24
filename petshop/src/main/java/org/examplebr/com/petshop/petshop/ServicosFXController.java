package org.examplebr.com.petshop.petshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import repository.ConectarBancoDeDados;
import repository.ServicosRepository;
import entities.Servico;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ServicosFXController {

    @FXML
    private Button buttonRegistrar;

    @FXML
    private ComboBox<String> servicosBox;

    @FXML
    private ComboBox<String> horaBox;

    @FXML
    private DatePicker datebox;

    private ServicosRepository servicoRepository;

    public ServicosFXController() throws SQLException, ClassNotFoundException {

        Connection connection = ConectarBancoDeDados.getConnection();
        this.servicoRepository = new ServicosRepository(connection);
    }

    @FXML
    public void initialize() {
        // Preencher ComboBox com serviços disponíveis
        servicosBox.getItems().addAll(
                "Banho",
                "Tosa",
                "Consulta Veterinária",
                "Passeio"
        );

        // Preencher ComboBox com horários disponíveis
        horaBox.getItems().addAll(
                "07:00",
                "08:00",
                "10:00",
                "11:00",
                "12:00",
                "14:00",
                "16:00"
        );

        buttonRegistrar.setOnAction(event -> {
            String servicoSelecionado = servicosBox.getValue();
            String horaSelecionada = horaBox.getValue();
            LocalDate dataSelecionada = datebox.getValue();

            if (servicoSelecionado != null && horaSelecionada != null && dataSelecionada != null) {
                try {
                    Servico.ClassificacaoServico classificacao = Servico.ClassificacaoServico.valueOf(servicoSelecionado.toUpperCase().replace(" ", "_"));
                    Servico servico = new Servico(servicoSelecionado, classificacao);
                    servicoRepository.registrarServico(servico, dataSelecionada.toString(), horaSelecionada);
                    System.out.println("Serviço registrado com sucesso!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Por favor, preencha todos os campos.");
            }
        });
    }
}
