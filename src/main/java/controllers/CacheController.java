package controllers;

import config.Settings;

import java.io.File;

public abstract class CacheController {

    //todo ....

    public static boolean isCacheExist(String fileName) {
        return new File(Settings.getInstance().getCacheDirectory() + "\\" + fileName).exists();
    }

    public static void clearCache() {
        //todo delete all files from CacheDir Settings.getInstance().getCacheDirectory();
    }
}
