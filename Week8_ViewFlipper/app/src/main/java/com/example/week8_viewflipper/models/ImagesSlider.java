package com.example.week8_viewflipper.models;

import java.io.Serializable;

public class ImagesSlider implements Serializable {
    private int id;
    private int position;
    private String avatar;

    public ImagesSlider(String avatar, int id, int position) {
        this.avatar = avatar;
        this.id = id;
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
