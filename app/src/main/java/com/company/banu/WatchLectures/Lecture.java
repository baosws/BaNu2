package com.company.banu.WatchLectures;

public class Lecture {
    String name;
    Integer id;
    Float percent;
    Boolean isLock;

    public Lecture(String n, Integer id, Float percent)
    {
        this.name = n;
        this.id = id;
        this.percent = percent;
        this.isLock = true;
    }
}
