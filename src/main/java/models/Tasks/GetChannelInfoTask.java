package models.Tasks;

import config.Settings;
import controllers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import models.ChannelInfo;
import models.TaskModel;
import models.VideoInfo;

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
                try {
                    ChannelInfo channelInfoFromCache = FileLoader.loadCache(id);
                    if (channelInfoFromCache != null)
                        list.add(channelInfoFromCache);
                } catch (Exception ex) {}
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
        YouTubeRequest youTubeRequest = new YouTubeRequest();
        RequestConverter requestConverter = new RequestConverter();
        for (String id : this.channelsId) {
            if(Settings.getInstance().isSaveCache() && CacheController.isCacheExist(id)) {
                ChannelInfo channelInfoFromCache = FileLoader.loadCache(id);

                if(channelInfoFromCache != null && channelInfoFromCache.getCommentCount().isEmpty()) {
                    getComments(channelInfoFromCache);

                    if(channelInfoFromCache != null) {
                        if(Settings.getInstance().isSaveCache()) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        FileSaver.saveCache(channelInfoFromCache);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }
                }

                if(channelInfoFromCache != null)
                    list.add(channelInfoFromCache);
            }
            else {
                try {
                    String answer = youTubeRequest.getChannelInfo(id);
                    ChannelInfo channelInfo = requestConverter.ConvertToChannelInfo(answer);

                    getComments(channelInfo);

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

    private void getComments(ChannelInfo channelInfo) {
        YouTubeRequest youTubeRequest = new YouTubeRequest();
        RequestConverter requestConverter = new RequestConverter();

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setVideoCount(Integer.parseInt(channelInfo.getVideoCount()));
        while(videoInfo.getVideoCount() > videoInfo.getVideoIds().size()) {
            try {
                String pageToken = "";
                String answerVideoList = youTubeRequest.getVideosFromChannel(channelInfo.getChannelId(), pageToken);
                videoInfo.addVideoIds(requestConverter.getVideoIds(answerVideoList));
                pageToken = requestConverter.getNextPageToken(answerVideoList);
                if (pageToken.isEmpty()) break;
            } catch (Exception ex) {

            }
        }
        Integer totalCommentsCount = 0;
        for (String videoId : videoInfo.getVideoIds()) {
            try {
                String singleVideoAnswer = youTubeRequest.getVideoInfo(videoId);
                String commentCount = requestConverter.getCommentCount(singleVideoAnswer);
                if (!commentCount.isEmpty()) {
                    Integer temp = Integer.parseInt(commentCount);
                    totalCommentsCount += temp;
                }
            } catch (Exception ex) {

            }
        }
        channelInfo.setCommentCount(String.valueOf(totalCommentsCount));
    }

    @Override
    protected ObservableList<ChannelInfo> call() throws Exception {
        if(currentTask.isAllColumns()) {
            return getRezonansChannelInfoList();
        }
        return getChannelInfoList();
    }
}