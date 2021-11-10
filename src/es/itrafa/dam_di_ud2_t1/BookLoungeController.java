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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafa
 */
public class BookLoungeController implements Initializable {

    private static Logger LOGGER = Logger.getLogger("itrafaLog");

    @FXML
    private ProgressIndicator form_ProgressIndicator;
    @FXML
    private Label eventType_Gro;







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
    private TextField cantPeople_TextField;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // Initialize bar progress
        progress = new boolean[data.values().length];
        for (boolean v : progress) {
            v = false;
        }

        // Add values to choose in lounge
        ObservableList<String> loungeList
                = FXCollections.observableArrayList("Salón Habana", "Otro Salón");
        lounge_ChoiceBox.setItems(loungeList);

        // Add values to choose in cucineTypes
        ObservableList<String> cucineTypes
                = FXCollections.observableArrayList("Buffet", "Carta", "Cita con chef", "No precisa");
        cucineType_ChoiceBox.setItems(cucineTypes);

        // group radioButton to choose only one
        banket_RadioBtn.setToggleGroup(eventTypeTGroup);
        day_RadioBtn.setToggleGroup(eventTypeTGroup);
        congress_RadioBtn.setToggleGroup(eventTypeTGroup);

        lounge_ChoiceBox .getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
        System.out.println(box.getItems().get((Integer) number2));
      }
    });
        
    }

    @FXML
    private void updateProgress() {
        LOGGER.info("Actualizando barra progreso");
        double actualProgress = 0;

        for (boolean v : progress) {
            if (v) {
                actualProgress++;
            }
        }

        actualProgress /= progress.length;
        form_ProgressIndicator.setProgress(actualProgress);
    }

    @FXML
    private void exitApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void switchScene(ActionEvent event) {
        Parent root;

        Control control = (Control) event.getSource();
        Stage stage = (Stage) control.getScene().getWindow();
        try {
            if (control == back_Btn) {
                root = FXMLLoader.load(getClass().getResource("booksMenu.fxml"));

            } else {
                return;
            }

            //Crear una nueva escena con raíz y establecer el escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            LOGGER.severe("Posible error al acceder al recurso xml");
        }

    }

    // CHECK INPUT AND PROGRESS BAR METHODS
    // NAME
    @FXML
    private void name_TextFieldHandler(KeyEvent event) {
        LOGGER.info("nombre modificado");

        // check value and mark it if is ok
        progress[data.NAME.ordinal()] = !name_TextField.getText().isEmpty();
        // calculate and show new bar progress value
        updateProgress();
    }

    // TFNO
    @FXML
    private void tfno_TextFieldHandler(KeyEvent event) {
        LOGGER.info("telefono modificado");

        // check value and mark it if is ok
        progress[data.TFNO.ordinal()] = !tfno_TextField.getText().isEmpty();
        // calculate and show new bar progress value
        updateProgress();
    }

    // LOUNGE (FAIL)

    
    
    
    // EVENTTYPE
    @FXML
    private void eventTypeGroup_VBoxHandler(MouseEvent event) {
        LOGGER.info("tipo evento modificado");

        progress[data.EVENTTYPE.ordinal()] = eventTypeTGroup.getSelectedToggle().isSelected();
        updateProgress();
    }
    // EVENTDATE

    // CUCINETYPE
    @FXML
    private void cucineType_ChoiceBoxHandler(ScrollEvent event) {
        LOGGER.info("tipo cocina modificado");

        progress[data.CUCINETYPE.ordinal()] = cucineType_ChoiceBox.getValue() == null;
        updateProgress();
    }

    // CANTPEOPLE
    @FXML
    private void cantPeople_TextFieldHandler(KeyEvent event) {
        LOGGER.info("cantidadGente modificado");

        // check value and mark it if is ok
        progress[data.CANTPEOPLE.ordinal()] = !cantPeople_TextField.getText().isEmpty();
        // calculate and show new bar progress value
        updateProgress();
    }

    // NEEDROMS ( NO PROGRESS CHECK cause both options are valid)
    @FXML
    private void roomNeedToggleButtonHandler(ActionEvent event) {
        LOGGER.info("necesitanHabitaciones modificado");

        roomsNeed_ToggleButton.getStyleClass().clear();

        if (roomsNeed_ToggleButton.isSelected()) {
            roomsNeed_ToggleButton.getStyleClass().add("toggleButton-true");
            roomsNeed_ToggleButton.setText("Requiere habitaciones: SÍ");
        } else {
            roomsNeed_ToggleButton.getStyleClass().add("toggleButton-false");
            roomsNeed_ToggleButton.setText("Requiere habitaciones: NO");
        }
    }

}
