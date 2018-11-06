package com.hacaller.data;

import com.hacaller.business.Car;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarData {

    int id;
    String logo;
    String website;
    int foundedYear;
    String brand;
    String carPhoto;
    int rating;

    public CarData(int id, String logo, String website, int foundedYear, String brand, String carPhoto, int rating) {
        this.id = id;
        this.logo = logo;
        this.website = website;
        this.foundedYear = foundedYear;
        this.brand = brand;
        this.carPhoto = carPhoto;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Car toBusinessCar(){
        return new Car(id, logo, website, foundedYear, brand, carPhoto, rating);
    }

}
