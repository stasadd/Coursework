import controllers.FileLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainWindowFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1100);
        primaryStage.setHeight(800);
        primaryStage.setTitle("YouTube Analytics");
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        FileLoader.loadSettings();
        launch(args);
    }
}
