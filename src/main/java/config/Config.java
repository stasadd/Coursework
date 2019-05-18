package config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.TaskModel;

public abstract class Config {
    private static final String ApiKey = "";
    private static final String SettingsDirectory = "SettingsDir";
    private static final String DefaultCacheDir = "CacheDir";
    private static String Version = "1.0.1";
    private static double maxTimeWaiting = 60;
    private static ObservableList<TaskModel> taskModels = FXCollections.observableArrayList();

    static {
        taskModels.add(new TaskModel("Відобразити глобальну інформацію про YouTube канал", false, 1));
        taskModels.add(new TaskModel("Відобразити та порівняти глобальну інформацію про два YouTube канали", false, 2));
        taskModels.add(new TaskModel("Відобразити, порівняти та сортувати глобальну інформацію про YouTube канали", false));
        taskModels.add(new TaskModel("Медіа резонанас: відобразити глобальну інформацію про YouTube канал, враховуючи кількість коментарів", true, 1));
        taskModels.add(new TaskModel("Медіа резонанас: відобразити та порівняти глобальну інформацію про два YouTube канали, враховуючи кількість коментарів", true, 2));
        taskModels.add(new TaskModel("Медіа резонанас: відобразити, порівняти та сортувати глобальну інформацію про YouTube канали, враховуючи кількість коментарів", true));
    }

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

    public static double getMaxTimeWaiting() {
        return maxTimeWaiting;
    }

    public static void setMaxTimeWaiting(double maxTimeWaiting) {
        Config.maxTimeWaiting = maxTimeWaiting;
    }

    public static ObservableList<TaskModel> getTaskModels() {
        return taskModels;
    }

    public static void setTaskModels(ObservableList<TaskModel> taskModels) {
        Config.taskModels = taskModels;
    }
}
