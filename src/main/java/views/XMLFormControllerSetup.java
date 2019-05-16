package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class XMLFormControllerSetup {

    @FXML
    private ResourceBundle resources;

    @FXML
    private JFXTextField exTextFild;

    @FXML
    private AnchorPane SetupAncore;

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



    public  void onBtnBack () throws IOException {
        AnchorPane element = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));
        SetupAncore.getChildren().setAll(element);

    }
    public void onClearCache(){

    }
    public void onUserCache (){

    }
    public void onTimeToActing (){

    }
    public void onBtnDefault (){

    }
    public void onBtnSave(){

    }
    @FXML
    void initialize() {


    }

}
