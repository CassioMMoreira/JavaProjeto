/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.Model;

/**
 *
 * @author SAMSUNG
 */
import br.com.fatec.App;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Aluno
 */


public class Troca_tela {
   public static Stage tela;
    
    public <T> T start(Stage tela, Class<T> tipoController, String view) throws IOException{
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(view));
        Parent root = fxmlLoader.load();
        T controler = fxmlLoader.getController();
        
        Scene scene = new Scene(root);
        tela.setScene(scene);
        tela.show();        

        return null;
    }
        public static void setStage(Stage t) {
        tela = t;
    }

}
