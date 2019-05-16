package controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import config.Config;

import java.util.ArrayList;
import java.util.List;

public class YouTubeRequest {

//    public static String getStatisticsByChannelId(String channelId) throws UnirestException {
//        String url = "https://www.googleapis.com/youtube/v3/channels";
//        HttpResponse<String> response = Unirest.get(url)
//                .queryString("key", Config.getApiKey())
//                .queryString("id", channelId)
//                .queryString("part", "statistics")
//                .asString();
//        return response.getBody();
//    }
//
//    public static List<String> getStatisticsByChannelId(String[] channelIds) throws UnirestException {
//        List<String> answers = new ArrayList<String>();
//        for (String channelId : channelIds) {
//            answers.add(getStatisticsByChannelId(channelId));
//        }
//        return answers;
//    }
//
//    public static String getSnippetByChannelId(String channelId) throws UnirestException {
//        String url = "https://www.googleapis.com/youtube/v3/channels";
//        HttpResponse<String> response = Unirest.get(url)
//                .queryString("key", Config.getApiKey())
//                .queryString("id", channelId)
//                .queryString("part", "snippet")
//                .asString();
//        return response.getBody();
//    }
//
//    public static List<String> getSnippetByChannelId(String[] channelIds) throws UnirestException {
//        List<String> answers = new ArrayList<String>();
//        for (String channelId : channelIds) {
//            answers.add(getSnippetByChannelId(channelId));
//        }
//        return answers;
//    }

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

    public String getVideoInfo(String videoId) {
        String url = "";
        return url;
    }


//    public List<String> getChannelInfo(String[] channelIds) throws UnirestException {
//        List<String> answers = new ArrayList<String>();
//        for (String channelId : channelIds) {
//            answers.add(getChannelInfo(channelId));
//        }
//        return answers;
//    }



}
