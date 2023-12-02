/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CarroDAO;
import br.com.fatec.DAO.ClienteDAO;
import br.com.fatec.DAO.LocacaoDAO;
import br.com.fatec.Model.Carro;
import br.com.fatec.Model.Cliente;
import br.com.fatec.Model.Locacao;
import br.com.fatec.Model.Troca_tela;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Alugar_carrosController implements Initializable {

    @FXML
    private TextField txt_valoraluguel;
    @FXML
    private Button btn_alugar;
    @FXML
    private Button btn_voltar;
    @FXML
    private TextField txt_idLocacao;
    @FXML
    private TextField txt_idCliente;
    @FXML
    private TextField txt_idCarro;
    @FXML
    private ComboBox<Cliente> cb_cliente;
    @FXML
    private ComboBox<Carro> cb_carro;
    @FXML
    private Button btn_alterar;
    @FXML
    private Button btn_devolver;
    
    private Locacao locacao;
    private Carro carro;
    private Cliente cliente;
    private LocacaoDAO locDAO = new LocacaoDAO();
    private CarroDAO carDAO = new CarroDAO();
    private ClienteDAO cliDAO = new ClienteDAO();
    private ObservableList<Carro> carros =  
            FXCollections.observableArrayList();
    private ObservableList<Cliente> clientes =  
            FXCollections.observableArrayList();
    private Stage stage = new Stage();
    
    private static Stage tela;
    @FXML
    private TextField tempo_aluguel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheComboCliente();
        cb_cliente.setItems(clientes);
        
        preencheComboCarro();
        cb_carro.setItems(carros);
        
        habilitaInclusao();
        
        configuraLostFocusCliente();
        configuraLostFocusCarro();
        configuraChangeValueComboCliente();
        configuraChangeValueComboCarro();
    }    

    @FXML
    private void btn_alugarClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        locacao = moveDadosTelaModel();

        //vamos inserir
        try {
            if(locDAO.insere(locacao)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_idLocacao.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void btn_voltarClick(ActionEvent event) throws IOException {
        Troca_tela tela = new Troca_tela();
        tela.start(stage , Cadastro_clientesController.class, "view/tela_principal.fxml");
        stage = (Stage) btn_voltar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_alterarClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        locacao = moveDadosTelaModel();

        //vamos inserir
        try {
            if(locDAO.altera(locacao)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_idLocacao.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    private void preencheComboCliente() {
        try {
            clientes.addAll(cliDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void preencheComboCarro() {
        try {
            carros.addAll(carDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void habilitaInclusao() {
        btn_alugar.setDisable(false);
        btn_alterar.setDisable(true);
        btn_devolver.setDisable(true);
        btn_voltar.setDisable(false);
    }
    
    private void configuraLostFocusCliente() {
        //programa o LOSTFOCUS do codigo do proprietário
        //para fazer a busca dele dentro da combo
        txt_idCliente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txt_idCliente.getText().length() == 0)
                        cb_cliente.getSelectionModel().clearSelection();
                    else {
                        cliente = new Cliente();
                        cliente.setIdCliente(Integer.parseInt(txt_idCliente.getText()));
                        //busca na combo
                        cb_cliente.getSelectionModel().select(cliente);
                    }
                }
             }
        });
    }
    
    private void configuraLostFocusCarro() {
        //programa o LOSTFOCUS do codigo do proprietário
        //para fazer a busca dele dentro da combo
        txt_idCarro.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txt_idCarro.getText().length() == 0)
                        cb_carro.getSelectionModel().clearSelection();
                    else {
                        carro = new Carro();
                        carro.setIdCarro(Integer.parseInt(txt_idCarro.getText()));
                        //busca na combo
                        cb_carro.getSelectionModel().select(carro);
                    }
                }
             }
        });
    }
    
    private void configuraChangeValueComboCliente() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cb_cliente.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txt_idCliente.setText(Integer.toString(novo.getIdCliente()));
        });
    }
    
    private void configuraChangeValueComboCarro() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cb_carro.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txt_idCarro.setText(Integer.toString(novo.getIdCarro()));
        });
    }
    
    private boolean validarCampos() {
        if(txt_idCarro.getText().length() == 0 ||
           txt_idLocacao.getText().length() == 0 ||     
           txt_idCliente.getText().length() == 0 ||
           txt_idCarro.getText().length() == 0 ||   
           tempo_aluguel.getText().length() == 0 ||
           cb_cliente.getSelectionModel().getSelectedIndex() == -1 ||
           cb_carro.getSelectionModel().getSelectedIndex() == -1     ) {
            return false;
        } else {
            return true;
        }
    }
    
    private void limparCampos() {
        txt_idLocacao.setText("");
        txt_idCliente.setText("");
        txt_idCarro.setText("");
        tempo_aluguel.setText("");
        cb_cliente.getSelectionModel().clearSelection();
        cb_cliente.getSelectionModel().clearSelection();
    }
    
    private Locacao moveDadosTelaModel() {
        locacao = new Locacao();
        locacao.setIdLocacao(Integer.parseInt(txt_idLocacao.getText()));
        cliente.setIdCliente(Integer.parseInt(txt_idCliente.getText()));
        carro.setIdCarro(Integer.parseInt(txt_idCarro.getText()));
        locacao.setCarro(cb_carro.getValue());
        locacao.setCliente(cb_cliente.getValue());
        
        return locacao;
    }
    
    private void moveDadosModelTela(Locacao v) {
        txt_idLocacao.setText(Integer.toString(v.getIdLocacao()));
        txt_idCliente.setText(Integer.toString(
                    v.getCliente().getIdCliente()));
        txt_idCarro.setText(Integer.toString(
                    v.getCarro().getIdCarro()));
        txt_valoraluguel.setText(Integer.toString(v.getTempoAluguel()));
        cb_cliente.setValue(v.getCliente());
        cb_carro.setValue(v.getCarro());
    }
    
    private void habilitaAlteracaoExclusao() {
        btn_alugar.setDisable(true);
        btn_alterar.setDisable(false);
        btn_devolver.setDisable(false);        
    }

    @FXML
    private void btn_devolverClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Cliente?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        
        //recebe todos os dados da tela
        locacao = moveDadosTelaModel();
        //vamos Excluir
        try {
            if(locDAO.remove(locacao)) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_idLocacao.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Exclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }

    }
}
