package models;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class tableChannelInfo {
    private TableView<ChannelInfo> table;

    public tableChannelInfo(ObservableList<ChannelInfo> list, boolean allcolumns) {
        table = new TableView<ChannelInfo>();

        TableColumn<ChannelInfo, String> idCol = new TableColumn<>("ID каналу");
        TableColumn<ChannelInfo, String> titleCol = new TableColumn<>("Назка каналу");
        TableColumn<ChannelInfo, Date> publishedAtCol = new TableColumn<>("Дата створення");
        TableColumn<ChannelInfo, Integer> subscriberCountCol = new TableColumn<>("Підписники");
        TableColumn<ChannelInfo, Integer> videoCountCol = new TableColumn<>("Відео");
        TableColumn<ChannelInfo, Integer> viewCountCol = new TableColumn<>("Перегляди");
        TableColumn<ChannelInfo, Integer> commentCountCol = new TableColumn<>("Коментарі");

        idCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("channelId"));
        idCol.setMinWidth(150);
        titleCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, String>("title"));
        titleCol.setMinWidth(200);
        publishedAtCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, Date>("_publishedAt"));
        publishedAtCol.setMinWidth(150);
        subscriberCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, Integer>("_subscriberCount"));
        subscriberCountCol.setMinWidth(200);
        videoCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, Integer>("_videoCount"));
        videoCountCol.setMinWidth(150);
        viewCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, Integer>("_viewCount"));
        viewCountCol.setMinWidth(200);
        commentCountCol.setCellValueFactory(new PropertyValueFactory<ChannelInfo, Integer>("_commentCount"));
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
