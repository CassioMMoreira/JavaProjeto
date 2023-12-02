/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.Model.Troca_tela;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Tela_principalController implements Initializable {

    @FXML
    private Button btn_CadCli;
    @FXML
    private Button btn_CadCar;
    @FXML
    private Button btn_AluCar;
    @FXML
    private Button btn_ConsAv;
    @FXML
    private Button btn_Sair;
    
    private Stage stage = new Stage();
    
    private static Stage tela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_CadCliClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Cadastro_clientesController.class, "view/cadastro_clientes.fxml");
        stage = (Stage) btn_CadCli.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_CadCarClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Cadastro_xarrosController.class,"view/cadastro_xarros.fxml");
        stage = (Stage) btn_CadCar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_AluCarClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Alugar_carrosController.class,"view/alugar_carros.fxml");
        stage = (Stage) btn_AluCar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_ConsAvClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Consulta_avancadaController.class,"view/consulta_avancada.fxml");
        stage = (Stage) btn_AluCar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_SairClick(ActionEvent event) {
        System.exit(0);
    }
    
}
