package models;

import java.io.Serializable;
import java.util.Date;

public class ChannelInfo implements Serializable {
    private String channelId;
    private String title;
    private String publishedAt;
    private Date _publishedAt;
    private String subscriberCount;
    private Integer _subscriberCount;
    private String videoCount;
    private Integer _videoCount;
    private String viewCount;
    private Integer _viewCount;
    private String commentCount;
    private Integer _commentCount;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(String subscriberCount) {
        this.subscriberCount = subscriberCount;
        try {
            this._subscriberCount = Integer.parseInt(subscriberCount);
        } catch (Exception ex) {}
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
        try {
            this._videoCount = Integer.parseInt(videoCount);
        } catch (Exception ex) {}
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
        try {
            this._viewCount = Integer.parseInt(viewCount);
        } catch (Exception ex) {}
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
        try {
            this._commentCount = Integer.parseInt(commentCount);
        } catch (Exception ex) {}
    }

    public Integer get_subscriberCount() {
        return _subscriberCount;
    }

    public Integer get_videoCount() {
        return _videoCount;
    }

    public Integer get_viewCount() {
        return _viewCount;
    }

    public Integer get_commentCount() {
        return _commentCount;
    }
}
