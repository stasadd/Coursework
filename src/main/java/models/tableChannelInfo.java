package models;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class tableChannelInfo {
    private TableView<ChannelInfo> table;

    public tableChannelInfo(ObservableList<ChannelInfo> list, boolean allcolumns) {
        table = new TableView<ChannelInfo>();

        TableColumn<ChannelInfo, String> idCol = new TableColumn<>("ID каналу");
        TableColumn<ChannelInfo, String> titleCol = new TableColumn<>("Назка каналу");
        TableColumn<ChannelInfo, String> publishedAtCol = new TableColumn<>("Дата створення");
        TableColumn<ChannelInfo, String> subscriberCountCol = new TableColumn<>("Підписники");
        TableColumn<ChannelInfo, String> videoCountCol = new TableColumn<>("Відео");
        TableColumn<ChannelInfo, String> viewCountCol = new TableColumn<>("Перегляди");
        TableColumn<ChannelInfo, String> commentCountCol = new TableColumn<>("Коментарі");

        idCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("channelId"));
        idCol.setMinWidth(150);
        titleCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("title"));
        titleCol.setMinWidth(200);
        publishedAtCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("publishedAt"));
        publishedAtCol.setMinWidth(150);
        subscriberCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("subscriberCount"));
        subscriberCountCol.setMinWidth(200);
        videoCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("videoCount"));
        videoCountCol.setMinWidth(150);
        viewCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("viewCount"));
        viewCountCol.setMinWidth(200);
        commentCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("commentCount"));
        commentCountCol.setMinWidth(200);

        table.setItems(list);
        table.getColumns().addAll(idCol, titleCol, publishedAtCol, subscriberCountCol, videoCountCol, viewCountCol);
        if(allcolumns) {
            table.getColumns().add(commentCountCol);
        }
    }

    public TableView<ChannelInfo> getTable() {
        return table;
    }
}
