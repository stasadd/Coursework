package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import config.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class XMLFormControllerTask implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField textChannelId;

    @FXML
    private JFXButton btnAddChannel;

    @FXML
    private JFXListView<String> listChannelIds;

    @FXML
    private Label labelTaskDescription;

    @FXML
    private Label labelHint;

    @FXML
    private JFXButton btnSendRequest;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private HBox boxTimeShow;

    @FXML
    private Label labelSeconds;

    ObservableList<String> channels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(0);
        boxTimeShow.setVisible(Settings.getInstance().isTimeShow());
        listChannelIds.setItems(channels);
        listChannelIds.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.DELETE) && listChannelIds.getSelectionModel().getSelectedItem() != null) {
                    channels.remove(listChannelIds.getSelectionModel().getSelectedIndex());
                }
            }
        });
    }

    public void onBtnAddChannel(ActionEvent actionEvent) {
        if(!textChannelId.getText().isEmpty()) {
            channels.add(textChannelId.getText());
            textChannelId.clear();
        }
    }

    public void onBtnSendRequest(ActionEvent actionEvent) {
        if(channels.isEmpty()) return;

        for (String str : channels) {
            System.out.println(str);
        }
    }
}
