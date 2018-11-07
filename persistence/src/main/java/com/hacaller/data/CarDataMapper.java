package com.hacaller.data;

import com.hacaller.business.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public class CarDataMapper {

    public static CarData toCarData(Car o){
        return new CarData(o.getId(), o.getLogo(), o.getWebsite(), o.getFoundedYear(),
                o.getBrand(), o.getCarPhoto(), o.getRating());
    }

    private static Car toBusinessCar(CarData o){
        return new Car(o.id, o.logo, o.website, o.foundedYear, o.brand, o.carPhoto, o.rating);
    }

    public static List<Car> toListBusinessCar(List<CarData> o){
        List<Car> cars = new ArrayList<>();
        for (CarData carData: o){
            cars.add(toBusinessCar(carData));
        }
        return cars;
    }


}
