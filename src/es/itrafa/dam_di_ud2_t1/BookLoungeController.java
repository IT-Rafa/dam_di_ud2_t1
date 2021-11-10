/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.itrafa.dam_di_ud2_t1;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private Button save_Button;

    private enum data {
        NAME, TFNO, LOUNGE, EVENTTYPE, EVENTDATE, CUCINETYPE, CANTPEOPLE
    };
    private boolean[] progress;

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
        lounge_ChoiceBox.getItems().add("Salón Habana");
        lounge_ChoiceBox.getItems().add("Otro Salón");

        // Add listener to lounge. OPTIONS IN SCENE BUILDER DON´T WORK OK
        lounge_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
                lounge_ChoiceBoxHandler(newValue);
            }
        });

        // Add values to choose in cucineTypes
        cucineType_ChoiceBox.getItems().add("Buffet");
        cucineType_ChoiceBox.getItems().add("Carta");
        cucineType_ChoiceBox.getItems().add("Cita con chef");
        cucineType_ChoiceBox.getItems().add("No precisa");

        // Add listener to lounge. OPTIONS IN SCENE BUILDER DON´T WORK OK
        cucineType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldValue, String newValue) {
                cucineType_ChoiceBoxHandler(newValue);
            }
        });

        dateEvent_DatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            dateEvent_DatePickerHandlerLocalDate(newValue);

        });

        // group radioButton to choose only one
        banket_RadioBtn.setToggleGroup(eventTypeTGroup);
        day_RadioBtn.setToggleGroup(eventTypeTGroup);
        congress_RadioBtn.setToggleGroup(eventTypeTGroup);

    }

    @FXML
    private void updateProgress() {
        LOGGER.info("updating progressBar");
        double actualProgress = 0;

        for (boolean valid : progress) {
            if (valid) {
                actualProgress++;
            }
        }

        actualProgress /= progress.length;
        form_ProgressIndicator.setProgress(actualProgress);
        if (actualProgress >= 1) {
            save_Button.setDisable(false);
        } else {
            save_Button.setDisable(true);
        }
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
            LOGGER.severe("Posible error al acceder al recurso xml: booksMenu.fxml");
        }

    }

    @FXML
    private void showConfirmation(ActionEvent event) {
        String msgLog = String.format("show confirmation");
        LOGGER.info(msgLog);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);

        alert.setTitle("Confirmación");
        alert.setContentText("Confirma que los datos son correctos");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            LOGGER.info("Datos enviados (en realidad, no)");
            
        } else if (option.get() == ButtonType.CANCEL) {
            LOGGER.info("Operación cancelada");
        } else {
            LOGGER.info("no se");
        }
    }

    // CHECK INPUT AND PROGRESS BAR METHODS
    // NAME
    @FXML
    private void name_TextFieldHandler(KeyEvent event) {
        boolean valid;
        String name = name_TextField.getText();

        String msgLog = String.format("name changed to %s", name);
        LOGGER.info(msgLog);

        String pattern = "[\\D \\-,\\.'´]+"; // patrón nombre mejorable (depende pais)
        ObservableList<String> cssClassList = name_TextField.getStyleClass();
        cssClassList.remove("noValid");
        if (name.isEmpty() || !name.matches(pattern)) {
            valid = false;
            cssClassList.add("noValid");

        } else {
            valid = true;

        }

        progress[data.NAME.ordinal()] = valid;
        updateProgress();
    }

    // TFNO
    @FXML
    private void tfno_TextFieldHandler(KeyEvent event) {
        boolean valid;
        String tfno = tfno_TextField.getText();

        String msgLog = String.format("tfno changed to %s", tfno);
        LOGGER.info(msgLog);

        String pattern = "[\\d \\-()+]+"; // patrón tfno mejorable (depende pais)
        ObservableList<String> cssClassList = tfno_TextField.getStyleClass();
        cssClassList.remove("noValid");
        if (tfno.isEmpty() || !tfno.matches(pattern)) {
            valid = false;
            cssClassList.add("noValid");

        } else {
            valid = true;
        }

        progress[data.TFNO.ordinal()] = valid;
        updateProgress();
    }

    // LOUNGE 
    private void lounge_ChoiceBoxHandler(String newValue) {
        String msgLog = String.format("lounge changed to %s", newValue);
        LOGGER.info(msgLog);

        progress[data.LOUNGE.ordinal()] = true;
        updateProgress();
    }

    // EVENTTYPE
    @FXML
    private void eventTypeGroup_RButtonHandler(MouseEvent event) {
        RadioButton button = (RadioButton) event.getSource();
        String msgLog = String.format("lounge changed to %s", button.getText());
        LOGGER.info(msgLog);

        progress[data.EVENTTYPE.ordinal()] = true;
        updateProgress();
    }

    // EVENTDATE
    private void dateEvent_DatePickerHandlerLocalDate(LocalDate newDate) {
        String msgLog = String.format("dateEvent changed to %s", newDate.toString());
        LOGGER.info(msgLog);

        boolean valid;
        ObservableList<String> cssClassList = dateEvent_DatePicker.getStyleClass();
        cssClassList.remove("noValid");

        if (newDate.isBefore(LocalDate.now())) {
            cssClassList.add("noValid");
            valid = false;
        } else {
            valid = true;
        }

        progress[data.EVENTDATE.ordinal()] = valid;
        updateProgress();
    }

    // CUCINETYPE
    private void cucineType_ChoiceBoxHandler(String newValue) {
        LOGGER.info("cucineType modificado a " + newValue);
        progress[data.CUCINETYPE.ordinal()] = true;
        updateProgress();
    }

    // CANTPEOPLE
    @FXML
    private void cantPeople_TextFieldHandler(KeyEvent event) {
        boolean valid;
        String cantPeople = cantPeople_TextField.getText();

        String msgLog = String.format("cantPeople changed to %s", cantPeople);
        LOGGER.info(msgLog);

        String pattern = "\\d{1,4}";

        ObservableList<String> cssClassList = cantPeople_TextField.getStyleClass();
        cssClassList.remove("noValid");
        if (cantPeople.isEmpty() || !cantPeople.matches(pattern)) {
            valid = false;
            cssClassList.add("noValid");

        } else {
            valid = true;

        }

        progress[data.CANTPEOPLE.ordinal()] = valid;
        updateProgress();
    }

    // NEEDROMS ( NO PROGRESS CHECK cause both options are valid)
    @FXML
    private void roomNeedToggleButtonHandler(ActionEvent event) {
        String msgLog = String.format("roomsNeed changed to %s", roomsNeed_ToggleButton.isSelected());
        LOGGER.info(msgLog);

        ObservableList<String> cssClassList = roomsNeed_ToggleButton.getStyleClass();

        if (roomsNeed_ToggleButton.isSelected()) {
            cssClassList.remove("toggleButton-false");
            cssClassList.add("toggleButton-true");
            roomsNeed_ToggleButton.setText("Requieren habitaciones: SÍ");
        } else {
            cssClassList.remove("toggleButton-true");
            cssClassList.add("toggleButton-false");
            roomsNeed_ToggleButton.setText("Requieren habitaciones: NO");
        }
    }

}
