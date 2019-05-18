package views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import config.Config;
import config.Settings;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.ChannelInfo;
import models.TaskModel;
import models.Tasks.GetChannelInfoTask;

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

    private TaskModel taskModel = null;

    public void setTaskModel(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelTaskDescription.setText(taskModel.getTaskDescription());
            }
        });

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
        textChannelId.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER) && !textChannelId.getText().isEmpty()) {
                    if(taskModel.getMaxChannelsIds() == -1) {
                        channels.add(textChannelId.getText());
                        textChannelId.clear();
                    }
                    else {
                        if(channels.size() < taskModel.getMaxChannelsIds()) {
                            channels.add(textChannelId.getText());
                            textChannelId.clear();
                        }
                    }
                }
            }
        });
    }

    public void onBtnAddChannel(ActionEvent actionEvent) {
        if(!textChannelId.getText().isEmpty()) {
            if(taskModel.getMaxChannelsIds() == -1) {
                channels.add(textChannelId.getText());
                textChannelId.clear();
            }
            else {
                if(channels.size() < taskModel.getMaxChannelsIds()) {
                    channels.add(textChannelId.getText());
                    textChannelId.clear();
                }
            }
        }
    }

    public void onBtnSendRequest(ActionEvent actionEvent) throws IOException {
        if(channels.isEmpty()) return;

        boxTimeShow.setVisible(Settings.getInstance().isTimeShow());

        GetChannelInfoTask getChannelInfoTask = new GetChannelInfoTask(channels, taskModel);
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(getChannelInfoTask.progressProperty());

        final Thread getChannelThread = new Thread(getChannelInfoTask);

        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double seconds = 0.0;
                    while(!Thread.currentThread().isInterrupted()) {
                        seconds += 0.1;
                        seconds = Math.round(seconds*100)/100.0;
                        setSeconds(seconds);
                        Thread.sleep(100);

                        if(seconds >= Config.getMaxTimeWaiting()) {
                            getChannelThread.interrupt();
                            Thread.currentThread().interrupt();
                        }
                    }
                } catch (Exception ex) {  }
            }
        });

        getChannelInfoTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                ObservableList<ChannelInfo> channelInfos = getChannelInfoTask.getValue();
                if(channelInfos == null || channelInfos.isEmpty()) {
                    Stage currentStage = (Stage) textChannelId.getScene().getWindow();
                    currentStage.close();
                    new Alert(Alert.AlertType.ERROR, "Запит не повернув жодних результатів").showAndWait();
                    return;
                }
                progressBar.progressProperty().unbind();
                progressBar.setProgress(1);
                if(Settings.getInstance().isTimeShow()) {
                    timer.interrupt();
                }
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/forTableFXML.fxml"));
                try {
                    root = (Parent)loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                XMLFormControllerTable controllerTable = loader.<XMLFormControllerTable>getController();
                controllerTable.setList(channelInfos);
                controllerTable.setAllColumns(taskModel.isAllColumns());
                Scene scene = new Scene(root);
                Stage secondStage = new Stage();
                Stage currentStage = (Stage) textChannelId.getScene().getWindow();
                Stage ownerStage = (Stage) currentStage.getOwner();
                secondStage.setScene(scene);
                secondStage.initOwner(ownerStage);
                secondStage.setWidth(1400);
                secondStage.setHeight(600);
                secondStage.setTitle("table");
                secondStage.show();
                currentStage.close();
            }
        });

        getChannelInfoTask.addEventHandler(WorkerStateEvent.WORKER_STATE_FAILED, new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Stage currentStage = (Stage) textChannelId.getScene().getWindow();
                currentStage.close();
                new Alert(Alert.AlertType.ERROR, "Помилка обробки запиту").showAndWait();
            }
        });

        getChannelThread.start();
        if(Settings.getInstance().isTimeShow()) {
            timer.setDaemon(true);
            timer.start();
        }
    }

    private void setSeconds(int seconds) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelSeconds.setText(String.valueOf(seconds));
            }
        });
    }

    private void setSeconds(double seconds) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelSeconds.setText(String.valueOf(seconds));
            }
        });
    }
}
