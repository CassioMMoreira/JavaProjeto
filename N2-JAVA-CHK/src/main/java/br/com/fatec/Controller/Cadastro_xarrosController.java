/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Controller;

import br.com.fatec.DAO.CarroDAO;
import br.com.fatec.DAO.CategoriaDAO;
import br.com.fatec.DAO.MarcaDAO;
import br.com.fatec.Model.Carro;
import br.com.fatec.Model.Categoria;
import br.com.fatec.Model.Marca;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Cadastro_xarrosController implements Initializable {

    @FXML
    private TextField txt_placa;
    @FXML
    private TextField txt_modelo;
    @FXML
    private ComboBox<Marca> cb_marca;
    @FXML
    private ComboBox<Categoria> cb_categoria;
    @FXML
    private Button btn_pesquisar;
    @FXML
    private Button btn_inserir;
    @FXML
    private Button btn_alterar;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_voltar;
    @FXML
    private TextField txt_idMarca;
    @FXML
    private TextField txt_idCategoria;
    @FXML
    private Button btn_pesquisarId;
    @FXML
    private TextField txt_idCarro;
    
    private Carro carro;
    private Marca marca;
    private Categoria categoria;
    private CarroDAO carDAO = new CarroDAO();
    private MarcaDAO marDAO = new MarcaDAO();
    private CategoriaDAO catDAO = new CategoriaDAO();
    private ObservableList<Marca> marcas =  
            FXCollections.observableArrayList();
    private ObservableList<Categoria> categorias =  
            FXCollections.observableArrayList();
    private Stage stage = new Stage();
    
    private static Stage tela;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencheComboMarca();
        cb_marca.setItems(marcas);
        
        preencheComboCategoria();
        cb_categoria.setItems(categorias);
        
        habilitaInclusao();
        
        configuraLostFocusMarca();
        configuraLostFocusCategoria();
        configuraChangeValueComboMarca();
        configuraChangeValueComboCategoira();
        
    }    

    @FXML
    private void btn_pesquisarClick(ActionEvent event) {
        carro = new Carro();
        //quem será pesquisado
        carro.setPlaca(txt_placa.getText());
        try {
            //busca o veiculo
            carro = carDAO.buscaID(carro);
            //se não achou
            if(carro == null) {
                mensagem("Carro Inexistentte!!!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(carro);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btn_inserirClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        carro = moveDadosTelaModel();

        //vamos inserir
        try {
            if(carDAO.insere(carro)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_idCarro.requestFocus();
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
    private void btn_alterarClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        //recebe todos os dados da tela
        carro = moveDadosTelaModel();
        
        //vamos alterar
        try {
            if(carDAO.altera(carro)) {
                mensagem("Dados Alterados com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_placa.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Alteração: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Alteração" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btn_excluirClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Carro?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        
        //recebe todos os dados da tela
        carro = moveDadosTelaModel();
        //vamos Excluir
        try {
            if(carDAO.remove(carro)) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_idCarro.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro Genérico na Exclusão" + ex.getMessage(),
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
    private void btn_pesquisarIdClick(ActionEvent event) {
        carro = new Carro();
        //quem será pesquisado
        carro.setIdCarro(Integer.parseInt(txt_idCarro.getText()));
        try {
            //busca o veiculo
            carro = carDAO.buscaID(carro);
            //se não achou
            if(carro == null) {
                mensagem("Carro Inexistentte!!!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(carro);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    private void preencheComboMarca() {
        try {
            marcas.addAll(marDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void preencheComboCategoria() {
        try {
            categorias.addAll(catDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void habilitaInclusao() {
        btn_inserir.setDisable(false);
        btn_alterar.setDisable(true);
        btn_excluir.setDisable(true);
        btn_voltar.setDisable(false);
    }
    
    private void configuraLostFocusMarca() {
        //programa o LOSTFOCUS do codigo do proprietário
        //para fazer a busca dele dentro da combo
        txt_idMarca.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txt_idMarca.getText().length() == 0)
                        cb_marca.getSelectionModel().clearSelection();
                    else {
                        marca = new Marca();
                        marca.setIdMarca(Integer.parseInt(txt_idMarca.getText()));
                        //busca na combo
                        cb_marca.getSelectionModel().select(marca);
                    }
                }
             }
        });
    }
    
    private void configuraLostFocusCategoria() {
        //programa o LOSTFOCUS do codigo do proprietário
        //para fazer a busca dele dentro da combo
        txt_idCategoria.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txt_idCategoria.getText().length() == 0)
                        cb_categoria.getSelectionModel().clearSelection();
                    else {
                        categoria = new Categoria();
                        categoria.setIdCategoria(Integer.parseInt(txt_idCategoria.getText()));
                        //busca na combo
                        cb_categoria.getSelectionModel().select(categoria);
                    }
                }
             }
        });
    }
    
    private void configuraChangeValueComboMarca() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cb_marca.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txt_idMarca.setText(Integer.toString(novo.getIdMarca()));
        });
    }
    
    private void configuraChangeValueComboCategoira() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cb_categoria.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txt_idCategoria.setText(Integer.toString(novo.getIdCategoria()));
        });
    }
    
    private boolean validarCampos() {
        if(txt_idCarro.getText().length() == 0 ||
           txt_placa.getText().length() == 0 ||     
           txt_modelo.getText().length() == 0 ||
           txt_idMarca.getText().length() == 0 ||   
           txt_idCategoria.getText().length() == 0 ||
           cb_marca.getSelectionModel().getSelectedIndex() == -1 ||
           cb_categoria.getSelectionModel().getSelectedIndex() == -1     ) {
            return false;
        } else {
            return true;
        }
    }
    
    private void limparCampos() {
        txt_idCarro.setText("");
        txt_placa.setText("");
        txt_modelo.setText("");
        txt_idMarca.setText("");
        txt_idCategoria.setText("");
        cb_marca.getSelectionModel().clearSelection();
        cb_categoria.getSelectionModel().clearSelection();
    }
    
    private Carro moveDadosTelaModel() {
        carro = new Carro();
        carro.setIdCarro(Integer.parseInt(txt_idCarro.getText()));
        carro.setPlaca(txt_placa.getText());
        carro.setModelo(txt_modelo.getText());
        carro.setIdCarro(Integer.parseInt(txt_idCarro.getText()));
        marca.setIdMarca(Integer.parseInt(txt_idMarca.getText()));
        categoria.setIdCategoria(Integer.parseInt(txt_idCategoria.getText()));
        carro.setMarca(cb_marca.getValue());
        carro.setCategoria(cb_categoria.getValue());
        
        return carro;
    }
    
    private void moveDadosModelTela(Carro v) {
        txt_placa.setText(v.getPlaca());
        txt_modelo.setText((v.getModelo()));
        txt_idCarro.setText(Integer.toString(v.getIdCarro()));
        txt_idMarca.setText(Integer.toString(
                    v.getMarca().getIdMarca()));
        txt_idCategoria.setText(Integer.toString(
                    v.getCategoria().getIdCategoria()));
        cb_marca.setValue(v.getMarca());
        cb_categoria.setValue(v.getCategoria());
    }
    
    private void habilitaAlteracaoExclusao() {
        btn_inserir.setDisable(true);
        btn_alterar.setDisable(false);
        btn_excluir.setDisable(false);        
    }
}
