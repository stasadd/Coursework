package views;

import com.jfoenix.controls.JFXButton;
import config.Config;
import controllers.FileSaver;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import models.ChannelInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class XMLFormControllerMain implements Initializable {

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

        int duration = 1500;
        playAnimation(imgLogo, duration, 500);
        playAnimation(versionHBox, duration, 1500);
        playAnimation(btnAnalyticsStart, duration, 2800);
        playAnimation(btnSettingsStart, duration, 3700);
    }

    public void onAnalyticsStart(ActionEvent actionEvent) {
        //todo start analytics form

        try {
            FileSaver.saveCache(new ChannelInfo());
            FileSaver.saveSettings();
        } catch (Exception ex) {}

//        try {
//            String answer = YouTubeRequest.getChannelInfo("UCcAQJDkK-Xf-YGCGAdAY3Ig");
//            Gson gson = new Gson();
//            YoutubeAnswer youtubeAnswer = gson.fromJson(answer, YoutubeAnswer.class);
//
//            System.out.println(youtubeAnswer.items[0].snippet.title);
//            System.out.println(youtubeAnswer.items[0].statistics.subscriberCount);
//        } catch (Exception ex) {}

    }

    public void onSettingsStart(ActionEvent actionEvent) {
        //todo start settings form
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
