package com.company.banu.DetailLevel;

public class Topic {
    public String name;
    public String image;
    public float process;

    public Topic() {}

    public Topic(String name, String image) {
        this.name = name;
        this.image = image;
        this.process = 0;
    }

    public Topic(String name, String image, float process) {
        this.name = name;
        this.image = image;
        this.process = process;
    }
}