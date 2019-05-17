package views;

        import com.jfoenix.controls.JFXButton;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

        import com.jfoenix.controls.JFXListView;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;

public class AnalyticWindowFXML {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnalyticAnchor;

    @FXML
    private JFXListView<String> ListViewGroup;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnOk;

    @FXML
    void onBtnBack(ActionEvent event) throws IOException {
        AnchorPane element = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));
        AnalyticAnchor.getChildren().setAll(element);

    }

    @FXML
    void onBtnOk(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/TaskWindowFXML.fxml"));
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

        ListViewGroup.getItems().add("Показати інформацію поро канал");
        ListViewGroup.getItems().add("Порівняти інформацію каналів");
        ListViewGroup.getItems().add("Порівняти канали по даних");
        ListViewGroup.getItems().add("Медіа резонанас");
        ListViewGroup.getItems().add("Порівняти Медіа резонанс");
        ListViewGroup.getItems().add("Порівняти по Медіа резонансу");

        btnOk.setDisable(true);
    }
}