package config;

public class Settings{

    private static volatile Settings INSTANCE;

    private Settings() {
        this.setCacheDirectory(Config.getDefaultCacheDir());
        this.setSaveCache(true);
        this.setTimeShow(true);
    }

    public static Settings getInstance() {
        Settings localInstance = INSTANCE;
        if(localInstance == null) {
            synchronized (Settings.class) {
                localInstance = INSTANCE;
                if(localInstance == null) {
                    INSTANCE = localInstance = new Settings();
                }
            }
        }
        return localInstance;
    }

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

    public void setDefaultSettings() {
        this.setCacheDirectory(Config.getDefaultCacheDir());
        this.setSaveCache(true);
        this.setTimeShow(true);
    }
}
