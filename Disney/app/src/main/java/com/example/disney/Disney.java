package com.example.disney;

import java.io.Serializable;
import java.util.Arrays;

public class Disney implements Serializable {

    private String name;
    private String films;
    private String url;
    private String imageUrl;
    private String createdAt;
    private String sourceUrl;
    private byte[] disneyImage;
    private boolean isSaved;

    public Disney() {
    }

    public Disney(String name, String films, String sourceUrl, String url, String thumbnailUrl, String createdAtdAt, byte[] disneyImage, boolean isSaved) {
        this.name = name;
        this.films = films;
        this.url = url;
        this.sourceUrl = sourceUrl;
        this.imageUrl = thumbnailUrl;
        this.createdAt = createdAtdAt;
        this.disneyImage = disneyImage;
        this.isSaved = isSaved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilms() {
        return films;
    }

    public void setFilms(String films) {
        this.films = films;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getDisneyImage() {
        return disneyImage;
    }

    public void setDisneyImage(byte[] newsImage) {
        this.disneyImage = disneyImage;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        this.isSaved = saved;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", films='" + films + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + imageUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", disneyImage=" + Arrays.toString(disneyImage) +
                ", isSaved=" + isSaved +
                '}';
    }

}
