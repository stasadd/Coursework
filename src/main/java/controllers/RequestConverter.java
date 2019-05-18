package controllers;

import com.google.gson.Gson;
import models.ChannelInfo;
import models.YouTubeSingleVideoAnswer.YouTubeSingleVideoAnswer;
import models.YouTubeVideosByChannelAnswer.YouTubeVideosByChannelAnswer;
import models.YoutubeChannelInfoAnswer.YoutubeChannelInfoAnswer;

import java.util.ArrayList;
import java.util.List;

public class RequestConverter {

    public ChannelInfo ConvertToChannelInfo(String answer) {
        ChannelInfo channelInfo = null;
        try {
            Gson gson = new Gson();
            YoutubeChannelInfoAnswer youtubeAnswer = gson.fromJson(answer, YoutubeChannelInfoAnswer.class);
            if(youtubeAnswer != null) {
                channelInfo = new ChannelInfo();
                channelInfo.setChannelId(youtubeAnswer.items[0].id);
                channelInfo.setTitle(youtubeAnswer.items[0].snippet.title);
                channelInfo.setPublishedAt(youtubeAnswer.items[0].snippet.publishedAt);
                channelInfo.setSubscriberCount(youtubeAnswer.items[0].statistics.subscriberCount);
                channelInfo.setVideoCount(youtubeAnswer.items[0].statistics.videoCount);
                channelInfo.setViewCount(youtubeAnswer.items[0].statistics.viewCount);
            }
        } catch (Exception ex) {
            channelInfo = null;
        }
        return channelInfo;
    }

    public List<String> getVideoIds(String answer) {
        List<String> vidooIds = new ArrayList<>();
        try {
            Gson gson = new Gson();
            YouTubeVideosByChannelAnswer youtubeAnswer = gson.fromJson(answer, YouTubeVideosByChannelAnswer.class);
            for (models.YouTubeVideosByChannelAnswer.Item item : youtubeAnswer.items) {
                vidooIds.add(item.id.videoId);
            }
        } catch (Exception ex) {}
        return vidooIds;
    }

    public String getNextPageToken(String answer) {
        String nextPageToken = "";
        try {
            Gson gson = new Gson();
            YouTubeVideosByChannelAnswer youtubeAnswer = gson.fromJson(answer, YouTubeVideosByChannelAnswer.class);
            nextPageToken = youtubeAnswer.nextPageToken;
        } catch (Exception ex) {
            nextPageToken = "";
        }
        return nextPageToken;
    }

    public String getCommentCount(String answer) {
        String commentCount = "";
        try {
            Gson gson = new Gson();
            YouTubeSingleVideoAnswer youtubeAnswer = gson.fromJson(answer, YouTubeSingleVideoAnswer.class);
            commentCount = youtubeAnswer.items[0].statistics.commentCount;
        } catch (Exception ex) {
            commentCount = "";
        }
        return commentCount;
    }

}
