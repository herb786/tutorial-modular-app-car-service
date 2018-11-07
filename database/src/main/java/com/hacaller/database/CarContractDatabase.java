package com.hacaller.database;

import android.provider.BaseColumns;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public final class CarContractDatabase {

    private CarContractDatabase(){}

    public static class CarScheme implements BaseColumns {
         static final String TABLE_NAME = "cars";
         static final String COLUMN_LOGO = "logo";
         static final String COLUMN_BRAND = "brand";
         static final String COLUMN_WEBSITE = "website";
         static final String COLUMN_FOUNDED_YEAR = "founded_year";
         static final String COLUMN_CAR_PHOTO = "car_photo";
         static final String COLUMN_RATING = "rating";
    }

    public static final String SQL_CREATE_SCHEME =
            "CREATE TABLE " + CarScheme.TABLE_NAME + " (" +
                    CarScheme._ID + " INTEGER PRIMARY KEY," +
                    CarScheme.COLUMN_LOGO + " TEXT," +
                    CarScheme.COLUMN_BRAND + " TEXT," +
                    CarScheme.COLUMN_WEBSITE + " TEXT," +
                    CarScheme.COLUMN_FOUNDED_YEAR + " INTEGER," +
                    CarScheme.COLUMN_CAR_PHOTO + " TEXT," +
                    CarScheme.COLUMN_RATING + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CarScheme.TABLE_NAME;


}
