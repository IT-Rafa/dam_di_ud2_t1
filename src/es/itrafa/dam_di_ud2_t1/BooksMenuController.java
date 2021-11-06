/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.itrafa.dam_di_ud2_t1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafa
 */
public class BooksMenuController implements Initializable {

    @FXML
    private Button exit_Btn;
    @FXML
    private Button back_Btn;
    @FXML
    private Button rooms_Btn;
    @FXML
    private Button launge_Btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void exitApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void switchScene(ActionEvent event) {
        Parent root;
        Scene scene;
        Control control = (Control) event.getSource();
        Stage stage = (Stage) control.getScene().getWindow();
        try {
            if (control == back_Btn) {
                root = FXMLLoader.load(getClass().getResource("/ejemplointerfazhotel/FormularioPrincipal.fxml"));

            } else if (control == rooms_Btn) {
                root = FXMLLoader.load(getClass().getResource("/ejemplointerfazhotel/FXMLDocument.fxml"));

            } else if (control == launge_Btn) {
                root = FXMLLoader.load(getClass().getResource("bookLounge.fxml"));
            } else {
                return;
            }
            
            //Crear una nueva escena con raíz y establecer el escenario
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(BooksMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}


