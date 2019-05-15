package models;

import java.io.Serializable;

public class SettingsForSerialize implements Serializable {
    private boolean saveCache;
    private String CacheDirectory;
    private boolean timeShow;

    public boolean isSaveCache() {
        return saveCache;
    }

    public void setSaveCache(boolean saveCache) {
        this.saveCache = saveCache;
    }

    public String getCacheDirectory() {
        return CacheDirectory;
    }

    public void setCacheDirectory(String cacheDirectory) {
        CacheDirectory = cacheDirectory;
    }

    public boolean isTimeShow() {
        return timeShow;
    }

    public void setTimeShow(boolean timeShow) {
        this.timeShow = timeShow;
    }
}
