package views;

import com.jfoenix.controls.JFXButton;
import config.Config;
import config.Settings;
import controllers.FileSaver;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.ChannelInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XMLFormControllerMain implements Initializable {

    static boolean firstLaunch = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgLogo;

    @FXML
    private HBox versionHBox;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label labelVersion;

    @FXML
    private JFXButton btnAnalyticsStart;

    @FXML
    private JFXButton btnSettingsStart;

    public void initialize(URL location, ResourceBundle resources) {
        labelVersion.setText(Config.getVersion());

        if(firstLaunch) {
            int duration = 1000;
            playAnimation(imgLogo, duration, 500);
            playAnimation(versionHBox, duration, 1000);
            playAnimation(btnAnalyticsStart, duration, 1500);
            playAnimation(btnSettingsStart, duration, 2000);
            firstLaunch = false;
        }
    }

    public void onAnalyticsStart(ActionEvent actionEvent) throws IOException {
        AnchorPane elements = FXMLLoader.load(getClass().getResource("/AnalyticsWindowFXML.fxml"));
        mainAnchorPane.getChildren().setAll(elements);
    }

    public void onSettingsStart(ActionEvent actionEvent) throws IOException {
        AnchorPane element = FXMLLoader.load(getClass().getResource("/SetupWindowFXML.fxml"));
        mainAnchorPane.getChildren().setAll(element);
    }

    private void playAnimation(Node node, int duration, int delay) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
        fadeTransition.setFromValue(0.0f);
        fadeTransition.setToValue(1.0f);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
        translateTransition.setFromX(-200);
        translateTransition.setToX(0);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
        parallelTransition.setDelay(Duration.millis(delay));
        parallelTransition.play();
    }

}
