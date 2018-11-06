package com.hacaller.services.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarPhoto {

    @SerializedName("name") String name;

    @SerializedName("photo") String photo;

    public CarPhoto(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public CarPhoto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
