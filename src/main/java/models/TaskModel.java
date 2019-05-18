package models;

public class TaskModel {
    private String taskDescription;
    private boolean allColumns;
    private int maxChannelsIds;

    public TaskModel() {}

    public TaskModel(String taskDescription, boolean allColumns) {
        this.taskDescription = taskDescription;
        this.allColumns = allColumns;
        this.maxChannelsIds = -1;
    }

    public TaskModel(String taskDescription, boolean allColumns, int maxChannelsIds) {
        this.taskDescription = taskDescription;
        this.allColumns = allColumns;
        this.maxChannelsIds = maxChannelsIds;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isAllColumns() {
        return allColumns;
    }

    public void setAllColumns(boolean allColumns) {
        this.allColumns = allColumns;
    }

    public int getMaxChannelsIds() {
        return maxChannelsIds;
    }

    public void setMaxChannelsIds(int maxChannelsIds) {
        this.maxChannelsIds = maxChannelsIds;
    }

    @Override
    public String toString() {
        return this.taskDescription;
    }
}
