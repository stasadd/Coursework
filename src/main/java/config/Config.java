package config;

public abstract class Config {

    private static final String ApiKey = "";
    private static final String SettingsDirectory = "SettingsDir";
    private static final String DefaultCacheDir = "CacheDir";
    private static String Version = "1.0.1";

    public static String getApiKey() {
        return ApiKey;
    }

    public static String getSettingsDirectory() {
        return SettingsDirectory;
    }

    public static String getDefaultCacheDir() {
        return DefaultCacheDir;
    }

    public static String getVersion() {
        return Version;
    }

    public static void setVersion(String version) {
        Version = version;
    }

}
