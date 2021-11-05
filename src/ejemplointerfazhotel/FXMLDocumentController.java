/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplointerfazhotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author Agustin
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML private ComboBox comboBox;
    @FXML private CheckBox checkBox;
    @FXML private Label labelAviso;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       comboBox.getItems().addAll("Doble de uso individual","Doble","Junior Suite","Suite");
       labelAviso.setVisible(false);

    }    
        public void handleCheckBox(){
            if (checkBox.isSelected()){
                //etiqueta será visible
               labelAviso.setVisible(true);
            }else{
                 labelAviso.setVisible(false);
            }
        }
}
