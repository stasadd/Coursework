package models.Tasks;

import config.Settings;
import controllers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import models.ChannelInfo;
import models.TaskModel;

import java.io.IOException;
import java.util.List;

public class GetChannelInfoTask extends Task<ObservableList<ChannelInfo>> {

    private List<String> channelsId;
    private TaskModel currentTask;

    public GetChannelInfoTask(ObservableList<String> channelsId, TaskModel taskModel) {
        this.channelsId = channelsId;
        this.currentTask = taskModel;
    }

    private ObservableList<ChannelInfo> getChannelInfoList() {
        ObservableList<ChannelInfo> list = FXCollections.observableArrayList();
        YouTubeRequest youTubeRequest = new YouTubeRequest();
        RequestConverter requestConverter = new RequestConverter();
        for (String id : this.channelsId) {
            if(Settings.getInstance().isSaveCache() && CacheController.isCacheExist(id)) {
                ChannelInfo channelInfoFromCache = FileLoader.loadCache(id);
                if(channelInfoFromCache != null)
                    list.add(channelInfoFromCache);
            }
            else {
                try {
                    String answer = youTubeRequest.getChannelInfo(id);
                    ChannelInfo channelInfo = requestConverter.ConvertToChannelInfo(answer);
                    if(channelInfo != null) {
                        list.add(channelInfo);
                        if(Settings.getInstance().isSaveCache()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        FileSaver.saveCache(channelInfo);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }
                }
                catch (Exception ex) { }
            }

            if(Thread.currentThread().isInterrupted()) return null;
        }
        return list;
    }

    private ObservableList<ChannelInfo> getRezonansChannelInfoList() {
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
        if(currentTask.isAllColumns()) {
            return getRezonansChannelInfoList();
        }
        return getChannelInfoList();
    }
}
