package models;

import java.util.ArrayList;
import java.util.List;

public class VideoInfo {
    private int videoCount;
    private List<String> videoIds;

    public VideoInfo() {
        this.videoCount = 0;
        this.videoIds = new ArrayList<>();
    }

    public int getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public void addVideoIds(List<String> videoIds) {
        this.videoIds.addAll(videoIds);
    }
}
