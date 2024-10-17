package org.examplebr.com.petshop.petshop;
/*
import entities.Administrador;
import repository.AdministradorRepository;
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdministradorFXController {

    @FXML
    private Button buttonVerClientes;

    @FXML
    private Button buttonVerAnimais;

    @FXML
    void verClientesCadastrados(ActionEvent event) {

        System.out.println("Ver Clientes Cadastrados");
    }

    @FXML
    void verAnimaisCadastrados(ActionEvent event) {

        System.out.println("Ver Animais Cadastrados");

    }

}
