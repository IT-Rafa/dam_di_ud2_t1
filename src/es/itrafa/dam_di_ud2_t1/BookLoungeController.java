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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafa
 */
public class BookLoungeController implements Initializable {
    @FXML
    private VBox eventTypeGroup_VBox;
    @FXML
    private Button exit_Btn;
    @FXML
    private Button back_Btn;
    @FXML
    private ChoiceBox cucineType_ChoiceBox;
    @FXML
    private DatePicker dateEvent_DatePicker;
    final ToggleGroup eventTypeTGroup = new ToggleGroup();
    @FXML
    private RadioButton banket_RadioBtn;
    @FXML
    private RadioButton day_RadioBtn;
    @FXML
    private RadioButton congress_RadioBtn;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
      eventTypeGroup_VBox.getStyleClass().add("vbox");

        ObservableList<String> transportTypes
                = FXCollections.observableArrayList("Buffet", "Carta", "Cita con chef", "No precisa");
        cucineType_ChoiceBox.setItems(transportTypes);

        banket_RadioBtn.setToggleGroup(eventTypeTGroup);
        day_RadioBtn.setToggleGroup(eventTypeTGroup);
        congress_RadioBtn.setToggleGroup(eventTypeTGroup);

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
                root = FXMLLoader.load(getClass().getResource("booksMenu.fxml"));

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
