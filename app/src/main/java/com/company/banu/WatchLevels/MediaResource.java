package com.company.banu.WatchLevels;

public class MediaResource {
    private String name;
    private String url;
    public MediaResource(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
}
