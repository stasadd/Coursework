package controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import config.Config;

import java.util.ArrayList;
import java.util.List;

public class YouTubeRequest {

    public String getChannelInfo(String channelId) throws UnirestException {
        String url = "https://www.googleapis.com/youtube/v3/channels";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Config.getApiKey())
                .queryString("id", channelId)
                .queryString("part", "snippet,statistics")
                .asString();
        return response.getBody();
    }

    public String getVideosFromChannel(String channelId, String pageToken) throws UnirestException {
        String url = "https://www.googleapis.com/youtube/v3/search";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Config.getApiKey())
                .queryString("channelId", channelId)
                .queryString("part", "snippet")
                .queryString("maxResults", 50)
                .queryString("pageToken", pageToken)
                .asString();
        return response.getBody();
    }

    public String getVideoInfo(String videoId) throws UnirestException {
        String url = "https://www.googleapis.com/youtube/v3/videos";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Config.getApiKey())
                .queryString("id", videoId)
                .queryString("part", "statistics")
                .asString();
        return response.getBody();
    }

}
