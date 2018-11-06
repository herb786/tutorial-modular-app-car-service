package com.hacaller.services;

import com.hacaller.data.CarData;
import com.hacaller.services.models.CarPhoto;
import com.hacaller.services.models.CarResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class CarObjectMapping {

    public static CarResponse toCarResponseModel(CarData item) {
        CarPhoto carPhoto = new CarPhoto();
        carPhoto.setName("unknown");
        carPhoto.setPhoto(item.getCarPhoto());
        return new CarResponse(
                item.getId(),
                item.getLogo(),
                item.getBrand(),
                item.getWebsite(),
                "",
                item.getFoundedYear(),
                "",
                Arrays.asList(carPhoto));
    }

    public static CarData toCarModel(CarResponse item) {
        return new CarData(
                item.getId(),
                item.getLogo(),
                item.getWebsite(),
                item.getFoundedYear(),
                item.getBrand(),
                extractCarPhoto(item.getCarPhotos()),
                0
        );
    }

    private static String extractCarPhoto(List<CarPhoto> photos) {
        if (photos == null){
            return "";
        }
        return photos.get(0).getPhoto();
    }


}
