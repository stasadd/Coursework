package controllers;

import config.Config;
import config.Settings;
import models.ChannelInfo;
import models.SettingsForSerialize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileSaver {

    public static void saveCache(ChannelInfo channelInfo) throws IOException {
        checkDirExists(Settings.getInstance().getCacheDirectory());
        String filePath = Settings.getInstance().getCacheDirectory() + "\\" + channelInfo.getChannelId();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            oos.writeObject(channelInfo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void saveSettings() {
        checkDirExists(Config.getSettingsDirectory());
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Config.getSettingsDirectory() + "\\settings.ser")))
        {
            SettingsForSerialize settingsForSerialize = new SettingsForSerialize();
            settingsForSerialize.setCacheDirectory(Settings.getInstance().getCacheDirectory());
            settingsForSerialize.setSaveCache(Settings.getInstance().isSaveCache());
            settingsForSerialize.setTimeShow(Settings.getInstance().isTimeShow());
            oos.writeObject(settingsForSerialize);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Boolean isDirCanMake(String dirPath){
        File f = new File(dirPath);
        return f.canWrite();
    }

    private static void checkDirExists(String dirName){
        File file = new File(dirName);
        if(!file.exists())
            file.mkdir();
    }
}
