/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplointerfazhotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 
 */
public class FormularioPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML private Button btnReservas;
     @FXML private Button btnSalir;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void handlebtnReservas(ActionEvent event) throws IOException{
     Stage stage =(Stage) btnReservas.getScene().getWindow(); 
     Parent root = FXMLLoader.load(getClass().getResource("FormularioPrincipal.fxml"));
     if(event.getSource()==btnReservas){
        //Obtener referencia a la Escena del botón         
        stage=(Stage) btnReservas.getScene().getWindow();
        //cargar el otro documento, en este caso la segundo pantalla
        
        
         //original code
         //root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         // resend to my menu
         root = FXMLLoader.load(getClass().getResource("/es/itrafa/dam_di_ud2_t1/booksMenu.fxml"));
      }
     
     //Crear una nueva escena con raíz y establecer el escenario
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
}
     public void handlebtnSalir(ActionEvent event) throws IOException{
        Platform.exit();
        System.exit(0);
     }
}
