package controllers;

import config.Config;
import config.Settings;
import models.ChannelInfo;
import models.SettingsForSerialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public abstract class FileLoader {

    public static ChannelInfo loadCache(String channelId) {
        //todo loadCache
        return null;
    }

    public static boolean loadSettings() {
        if(new File(Config.getSettingsDirectory() + "\\settings.ser").exists()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Config.getSettingsDirectory() + "\\settings.ser"))) {
                SettingsForSerialize settingsForSerialize = (SettingsForSerialize) ois.readObject();
                Settings settings = Settings.getInstance();
                settings.setCacheDirectory(settingsForSerialize.getCacheDirectory());
                settings.setSaveCache(settingsForSerialize.isSaveCache());
                settings.setTimeShow(settingsForSerialize.isTimeShow());
                return true;
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

}
