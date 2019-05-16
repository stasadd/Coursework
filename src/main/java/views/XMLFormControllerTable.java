package views;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import models.ChannelInfo;
import models.tableChannelInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class XMLFormControllerTable implements Initializable {

    private ObservableList<ChannelInfo> list;
    private boolean allColumns;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane forTable;

    public void setList(ObservableList<ChannelInfo> list) {
        this.list = list;
    }

    public void setAllColumns(boolean allColumns) {
        this.allColumns = allColumns;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            tableChannelInfo tableController = new tableChannelInfo(this.list, this.allColumns);
            TableView<ChannelInfo> table = tableController.getTable();
            forTable.setTopAnchor(table, 0.0);
            forTable.setLeftAnchor(table, 0.0);
            forTable.setRightAnchor(table, 0.0);
            forTable.setBottomAnchor(table, 0.0);
            forTable.getChildren().add(table);
        });
    }
}
