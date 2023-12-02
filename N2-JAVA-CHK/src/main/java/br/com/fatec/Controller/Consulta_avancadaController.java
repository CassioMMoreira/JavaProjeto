/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.ClienteDAO;
import br.com.fatec.Model.Cliente;
import br.com.fatec.Model.Troca_tela;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Consulta_avancadaController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    @FXML
    private Button btn_voltar;
    @FXML
    private TableColumn<Cliente, String> colINome;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, String> colCpf;
    @FXML
    private TableColumn<Cliente, String> colEndereco;
    

    private Stage stage = new Stage();
    
    private static Stage tela;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        colINome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colINome.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colINome.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        
        //preenche a tabela
        tableViewClientes.setItems(preencheTabela());
        // TODO
    }    


    @FXML
    private void btn_voltarClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Cadastro_clientesController.class, "view/tela_principal.fxml");
        stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
    }
 
    private ObservableList<Cliente> preencheTabela() {
        ClienteDAO dao = new ClienteDAO();
        ObservableList<Cliente> clientes
            = FXCollections.observableArrayList();
        
        try {
            //busca somente que termina com 'a'
            //proprietarios.addAll(dao.lista("nome like '%a'"));
            //busca todo mundo
            clientes.addAll(dao.lista(""));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        
        return clientes;
    }
    
}
