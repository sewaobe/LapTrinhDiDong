package com.example.week10_firebase;

public class UploadResponse {
    private String url;
    private String public_id;

    public UploadResponse(String public_id, String url) {
        this.public_id = public_id;
        this.url = url;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
