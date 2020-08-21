package com.arcadegamepark.chatapp;

import java.net.URL;

public class Message {

    private String author;
    private String textOfMessage;
    private long date;
    private String urlToImage;

    public Message(String author, String textOfMessage, long date, String urlToImage) {
        this.author = author;
        this.textOfMessage = textOfMessage;
        this.date = date;
        this.urlToImage = urlToImage;
    }

    public Message() {}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTextOfMessage() {
        return textOfMessage;
    }

    public void setTextOfMessage(String textOfMessage) {
        this.textOfMessage = textOfMessage;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getURLToImage() {
        return urlToImage;
    }

    public void setURLToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
