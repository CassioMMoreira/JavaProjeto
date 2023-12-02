/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.Model.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Consulta_avancadaController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    @FXML
    private TableColumn<Cliente, String> colIdLocacao;
    @FXML
    private TableColumn<Cliente, String> colIdCarro;
    @FXML
    private TableColumn<Cliente, String> colDataLoc;
    @FXML
    private TableColumn<Cliente, String> colDataDev;
    @FXML
    private TableColumn<Cliente, Float> colValTot;
    @FXML
    private Button btn_voltar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPesquisarClick(ActionEvent event) {
    }

    @FXML
    private void btn_voltarClick(ActionEvent event) {
    }
    
}
