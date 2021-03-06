package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import config.Settings;
import controllers.CacheController;
import controllers.FileSaver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CacheController.clearCache();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new Alert(Alert.AlertType.INFORMATION, "Кеш успішно видалено").showAndWait();
                        }
                    });
                } catch (Exception ex) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new Alert(Alert.AlertType.ERROR, "Сталася помилка під час видалення кешу").showAndWait();
                        }
                    });
                }
            }
        }).start();
    }

    public void onUserCache (){
        Settings.getInstance().setSaveCache(rbUserCache.selectedProperty().get());
    }

    public void onTimeToActing (){
        Settings.getInstance().setTimeShow(rbTimeToActing.selectedProperty().get());
    }

    public void onBtnDefault (){
        Settings.getInstance().setDefaultSettings();
        rbUserCache.setSelected(Settings.getInstance().isSaveCache());
        rbTimeToActing.setSelected(Settings.getInstance().isTimeShow());
        exTextFild.setText(Settings.getInstance().getCacheDirectory());
    }

    public void onBtnSave(){
        if (FileSaver.isDirCanMake(Settings.getInstance().getCacheDirectory())) {
            if(FileSaver.isDirCanMake(exTextFild.getText())) {
                Settings.getInstance().setCacheDirectory(exTextFild.getText());
            }
            FileSaver.saveSettings();
            new Alert(Alert.AlertType.INFORMATION, "Успішно збережено").showAndWait();
        }
        else
            new Alert(Alert.AlertType.ERROR, "Дерикторію створити неможливо").showAndWait();
    }

    @FXML
    void initialize() {
        exTextFild.setText(Settings.getInstance().getCacheDirectory());
        rbUserCache.setSelected(Settings.getInstance().isSaveCache());
        rbTimeToActing.setSelected(Settings.getInstance().isTimeShow());
    }

}
