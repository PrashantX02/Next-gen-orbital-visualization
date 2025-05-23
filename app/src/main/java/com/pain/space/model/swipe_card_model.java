package com.pain.space.model;

public class swipe_card_model {

    String headers,description,lines;
    int image;

    public swipe_card_model(String headers, String description, String lines, int image) {
        this.headers = headers;
        this.description = description;
        this.lines = lines;
        this.image = image;
    }
    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
