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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafa
 */
public class BookLoungeController implements Initializable {

    @FXML
    private ProgressIndicator form_ProgressIndicator;
    @FXML
    private Label eventType_Gro;

    @FXML
    private void name_TextFieldHandler(KeyEvent event) {
    }

    private enum data {
        NAME, TFNO, LOUNGE, EVENTTYPE, EVENTDATE, CUCINETYPE, CANTPEOPLE
    };
    private boolean[] progress;

    @FXML
    private VBox eventTypeGroup_VBox;
    @FXML
    private Button exit_Btn;
    @FXML
    private Button back_Btn;

    @FXML
    private ChoiceBox lounge_ChoiceBox;
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
    @FXML
    private ToggleButton roomsNeed_ToggleButton;
    @FXML
    private TextField tfno_TextField;
    @FXML
    private TextField name_TextField;
    @FXML
    private TextField cantPeople_TxtField;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // , 
        progress = new boolean[data.values().length];
        for (boolean v : progress) {
            v = false;
        }

        name_TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            progress[data.NAME.ordinal()] = !name_TextField.getText().isEmpty();
            updateProgress();
        });

        ObservableList<String> cucineTypes
                = FXCollections.observableArrayList("Buffet", "Carta", "Cita con chef", "No precisa");
        cucineType_ChoiceBox.setItems(cucineTypes);

        ObservableList<String> loungeList
                = FXCollections.observableArrayList("Salón Habana", "Otro Salón");
        lounge_ChoiceBox.setItems(loungeList);

        banket_RadioBtn.setToggleGroup(eventTypeTGroup);
        day_RadioBtn.setToggleGroup(eventTypeTGroup);
        congress_RadioBtn.setToggleGroup(eventTypeTGroup);

    }

    @FXML
    private void updateProgress() {
        int actualProgress = 0;

        for (boolean v : progress) {
            if (v) {
                System.out.println(v + "= true");
                actualProgress++;
            } else {
                System.out.println(v + "= false");
            }
        }
        double initProgress = actualProgress / progress.length;
        System.out.println("initProgress:" + initProgress);

        double porcentProgress = initProgress * 100;
        System.out.println("porcentProgress:" + porcentProgress);
        
        form_ProgressIndicator.setProgress(porcentProgress);
        System.out.println("insertado:" + form_ProgressIndicator.getProgress());
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

    @FXML
    private void roomNeedToggleButtonHandler(ActionEvent event) {
        roomsNeed_ToggleButton.getStyleClass().clear();

        if (roomsNeed_ToggleButton.isSelected()) {
            roomsNeed_ToggleButton.getStyleClass().add("toggleButton-true");
            roomsNeed_ToggleButton.setText("Requiere habitaciones: SÍ");
        } else {
            roomsNeed_ToggleButton.getStyleClass().add("toggleButton-false");
            roomsNeed_ToggleButton.setText("Requiere habitaciones: NO");
        }
    }

    private void name_TextFieldHandler(ActionEvent event) {
        System.out.println("tocaste nombre");
        progress[data.NAME.ordinal()] = !name_TextField.getText().isEmpty();
        updateProgress();
    }

    @FXML
    private void tfno_TextFieldHandler(ActionEvent event) {

        progress[data.TFNO.ordinal()] = !tfno_TextField.getText().isEmpty();
        updateProgress();
    }

    @FXML
    private void cantPeople_TxtFieldHandler(ActionEvent event) {
        progress[data.CANTPEOPLE.ordinal()] = !cantPeople_TxtField.getText().isEmpty();

        updateProgress();
    }

    @FXML
    private void eventTypeGroup_VBoxHandler(MouseEvent event) {
        progress[data.EVENTTYPE.ordinal()] = eventTypeTGroup.getSelectedToggle().isSelected();
        updateProgress();
    }

    @FXML
    private void cucineType_ChoiceBoxHandler(DragEvent event) {
        progress[data.CUCINETYPE.ordinal()] = cucineType_ChoiceBox.getValue() == null;
        updateProgress();
    }

    @FXML
    private void lounge_ChoiceBoxHandler(DragEvent event) {
        progress[data.LOUNGE.ordinal()] = lounge_ChoiceBox.getValue() == null;
        updateProgress();
    }

}
