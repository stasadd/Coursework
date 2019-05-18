package controllers;

import com.google.gson.Gson;
import models.ChannelInfo;
import models.YoutubeChannelInfoAnswer.YoutubeChannelInfoAnswer;

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
        } catch (Exception ex) {}
        return channelInfo;
    }

}
