package com.simplestudio.simplevideo;

public class VideoFiles {

    private String id;
    private String path;
    private String title;
    private String filename;
    private String size;
    private String date;
    private String duration;

    public VideoFiles(String id, String path, String title, String filename, String size, String date, String duration) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.filename = filename;
        this.size = size;
        this.date = date;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
