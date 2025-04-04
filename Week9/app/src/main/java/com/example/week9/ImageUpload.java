package com.example.week9;

public class ImageUpload {
    private int id;
    private String username;
    private String avatar;

    public ImageUpload(String avatar, int id, String username) {
        this.avatar = avatar;
        this.id = id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
