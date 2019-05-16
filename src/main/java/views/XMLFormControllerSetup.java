package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class XMLFormControllerSetup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnClearCache;

    @FXML
    private JFXCheckBox rbUserCache;

    @FXML
    private JFXCheckBox rbTimeToActing;

    @FXML
    private JFXButton btnDefoult;

    @FXML
    private JFXButton btnSave;

    @FXML
    void initialize() {

    }
}
