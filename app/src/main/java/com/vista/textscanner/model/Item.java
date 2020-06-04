package com.vista.textscanner.model;

import java.io.Serializable;

public class Item implements Serializable {
    private String id, content, type, datetime, imagepath, thumpath;

    public Item(String id, String content, String type, String datetime, String imagepath, String thumpath) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.datetime = datetime;
        this.imagepath = imagepath;
        this.thumpath = thumpath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getThumpath() {
        return thumpath;
    }

    public void setThumpath(String thumpath) {
        this.thumpath = thumpath;
    }
}
