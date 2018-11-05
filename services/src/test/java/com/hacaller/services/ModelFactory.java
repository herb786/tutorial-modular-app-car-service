package com.hacaller.services;

import com.hacaller.data.CarData;
import com.hacaller.services.models.CarPhoto;
import com.hacaller.services.models.CarResponse;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;

/**
 * Created by Herbert Caller on 04/11/2018.
 */
public class ModelFactory {

    public static CarData newCar(){
        return new CarData(rndInt(),rndString(),rndString(),rndInt(),rndString(),rndInt());
    }

    public static CarResponse newCarResponse(boolean hasPhoto) {
        return new CarResponse(rndInt(),rndString(),rndString(),rndString(),
                rndInt(),rndString(),newListCarPhotoEntity(hasPhoto));
    }

    public static List<CarPhoto> newListCarPhotoEntity(boolean hasPhoto) {
        if (!hasPhoto) return null;
        CarPhoto item = new CarPhoto(rndString(),rndString());
        return Arrays.asList(item, item, item);
    }

    public static String rndString() {
        return Base64.getEncoder().encodeToString(SecureRandom.getSeed(10));
        //return Instant.now().toString()
    }

    public static int rndInt() {
        return new Random(1492).nextInt();
    }

    public static long rndLong() {
        return new Random(1492).nextLong();
    }

    public static float rndFloat() {
        return new Random(1492).nextFloat();
    }

    public static double rndDouble() {
        return new Random(1492).nextDouble();
    }

    public static boolean rndBool() {
        return new Random(1492).nextBoolean();
    }


}
