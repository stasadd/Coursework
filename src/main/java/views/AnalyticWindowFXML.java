package views;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import config.Config;
import controllers.NetworkChecker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.TaskModel;

public class AnalyticWindowFXML {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnalyticAnchor;

    @FXML
    private JFXListView<TaskModel> ListViewGroup;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnOk;

    private ObservableList<TaskModel> taskModels = FXCollections.observableArrayList();

    @FXML
    void onBtnBack(ActionEvent event) throws IOException {
        AnchorPane element = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));
        AnalyticAnchor.getChildren().setAll(element);
    }

    @FXML
    void onBtnOk(ActionEvent event) throws IOException {
        if(!NetworkChecker.isNetAvailable()) {
            new Alert(Alert.AlertType.WARNING, "Відсутнє інтернет з'єднання, виконання запитів неможливе").showAndWait();
            return;
        }

        if(ListViewGroup.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TaskWindowFXML.fxml"));
        root = (Parent)loader.load();
        XMLFormControllerTask controllerTask = loader.<XMLFormControllerTask>getController();
        controllerTask.setTaskModel(ListViewGroup.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(root);
        Stage secondStage = new Stage();
        Stage mainStage = (Stage) AnalyticAnchor.getScene().getWindow();
        secondStage.setScene(scene);
        secondStage.initOwner(mainStage);
        secondStage.setWidth(600);
        secondStage.setHeight(600);
        secondStage.setTitle("Task");
        secondStage.show();
    }

    public void onEditList(){
        btnOk.setDisable(false);
    }

    @FXML
    void initialize() {
        taskModels = Config.getTaskModels();
        ListViewGroup.setItems(taskModels);
        btnOk.setDisable(true);
    }
}