package com.hacaller.services.models;

import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarResponse {

    int id;
    String logo;
    String website;
    String founders;
    int foundedYear;
    String foundedCity;
    List<CarPhoto> carPhotos;

    public CarResponse(int id, String logo, String website, String founders,
                       int foundedYear, String foundedCity, List<CarPhoto> carPhotos) {
        this.id = id;
        this.logo = logo;
        this.website = website;
        this.founders = founders;
        this.foundedYear = foundedYear;
        this.foundedCity = foundedCity;
        this.carPhotos = carPhotos;
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

    public String getFounders() {
        return founders;
    }

    public void setFounders(String founders) {
        this.founders = founders;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getFoundedCity() {
        return foundedCity;
    }

    public void setFoundedCity(String foundedCity) {
        this.foundedCity = foundedCity;
    }

    public List<CarPhoto> getCarPhotos() {
        return carPhotos;
    }

    public void setCarPhotos(List<CarPhoto> carPhotos) {
        this.carPhotos = carPhotos;
    }
}
