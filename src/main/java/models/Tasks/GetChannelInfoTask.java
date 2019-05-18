package models.Tasks;

import config.Settings;
import controllers.CacheController;
import controllers.FileLoader;
import controllers.FileSaver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import models.ChannelInfo;

import java.io.IOException;
import java.util.List;

public class GetChannelInfoTask extends Task<ObservableList<ChannelInfo>> {

    private List<String> channelsId;

    public GetChannelInfoTask(ObservableList<String> channelsId) {
        this.channelsId = channelsId;
    }

    private ObservableList<ChannelInfo> getChannelInfoList() {
        ObservableList<ChannelInfo> list = FXCollections.observableArrayList();
        for (String id : this.channelsId) {
            if(Settings.getInstance().isSaveCache() && CacheController.isCacheExist(id)) {
                ChannelInfo channelInfoFromCache = FileLoader.loadCache(id);
                if(channelInfoFromCache != null)
                    list.add(channelInfoFromCache);
            }
            else {

                //todo from youtube

//                if(Settings.getInstance().isSaveCache()) {
//                    try {
//                        //todo save cache
//                        FileSaver.saveCache(null);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }

            if(Thread.currentThread().isInterrupted()) return null;
        }
        return list;
    }

    @Override
    protected ObservableList<ChannelInfo> call() throws Exception {
        return getChannelInfoList();
    }
}
