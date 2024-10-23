package com.example.musicapp;

public class User {
    private String name;
    private String bio;


    // 构造方法、getter 和 setter 方法
    public User(String name, String bio) {
        this.name = name;
        this.bio = bio;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
