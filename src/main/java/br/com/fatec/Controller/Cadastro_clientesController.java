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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Cadastro_clientesController implements Initializable {

    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_endereco;
    @FXML
    private Button btn_inserir;
    @FXML
    private Button btn_alterar;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_voltar;
    
    private Cliente cliente;
    
    private ClienteDAO cliDAO = new ClienteDAO();
    
    private Stage stage = new Stage();
    
    private static Stage tela;
    @FXML
    private Button btn_pesquisar;
    @FXML
    private TextField txt_idCliente;
    @FXML
    private Button btn_pesquisarId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_inserirClick(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        cliente = moveDadosTelaModel();

        //vamos inserir
        try {
            if(cliDAO.insere(cliente)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_nome.requestFocus();
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
            mensagem("Preencher todos os campos", 
                    Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        cliente = moveDadosTelaModel();

        //vamos inserir
        try {
            if(cliDAO.altera(cliente)) {
                mensagem("Dados Incluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                txt_nome.requestFocus();
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
    private void btn_excluirClick(ActionEvent event) {
         
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
        cliente = moveDadosTelaModel();
        //vamos Excluir
        try {
            if(cliDAO.remove(cliente)) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
                habilitaInclusao();
                txt_nome.requestFocus();
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
    
    private void moveDadosModelTela(Cliente v) {
        txt_idCliente.setText(Integer.toString(v.getIdCliente()));
        txt_nome.setText(v.getNome());
        txt_email.setText(v.getEmail());
        txt_cpf.setText(v.getCpf());
        txt_endereco.setText(v.getEndereco());
    }
    
    private boolean validarCampos() {
        if(txt_idCliente.getText().length() == 0 ||
           txt_nome.getText().length() == 0 ||
           txt_email.getText().length() == 0 ||
           txt_cpf.getText().length() == 0 ||
           txt_endereco.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private Cliente moveDadosTelaModel() {
        cliente = new Cliente();
        
        cliente.setIdCliente(Integer.parseInt(txt_idCliente.getText()));
        cliente.setNome(txt_nome.getText());
        cliente.setEmail(txt_email.getText());
        cliente.setCpf(txt_cpf.getText());
        cliente.setEndereco(txt_endereco.getText());
     
        return cliente;
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    private void limparCampos() {
        txt_idCliente.setText("");
        txt_nome.setText("");
        txt_email.setText("");
        txt_cpf.setText("");
        txt_endereco.setText("");
       
    }
    
    private void habilitaAlteracaoExclusao() {
        btn_inserir.setDisable(true);
        btn_alterar.setDisable(false);
        btn_excluir.setDisable(false);        
    }
    
    private void habilitaInclusao() {
        btn_inserir.setDisable(false);
        btn_alterar.setDisable(true);
        btn_excluir.setDisable(true);
    }

    @FXML
    private void btn_pesquisarClick(ActionEvent event) {
        cliente = new Cliente();
        cliente.setNome(txt_nome.getText());
        try {
            //busca o veiculo
            cliente = cliDAO.buscaNome(cliente);
            //se não achou
            if(cliente == null) {
                mensagem("Cliente Não Existe!!!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(cliente);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
        
    }

    @FXML
    private void btn_pesquisarId(ActionEvent event) {
        cliente = new Cliente();
        cliente.setIdCliente(Integer.parseInt(txt_idCliente.getText()));
        try {
            //busca o veiculo
            cliente = cliDAO.buscaID(cliente);
            //se não achou
            if(cliente == null) {
                mensagem("Cliente Não Existe!!!",
                            Alert.AlertType.INFORMATION);
            } 
            else { //achou
                //mostrar na tela
                moveDadosModelTela(cliente);
                habilitaAlteracaoExclusao();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
}