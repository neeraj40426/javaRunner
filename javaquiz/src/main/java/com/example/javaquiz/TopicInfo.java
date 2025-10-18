package com.example.javaquiz;

public class TopicInfo {
    private String displayName;  // For showing on UI
    private String dbName;       // For backend/database fetch
    private String color;
    private String icon;

    public TopicInfo(String displayName, String dbName, String color, String icon) {
        this.displayName = displayName;
        this.dbName = dbName;
        this.color = color;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
