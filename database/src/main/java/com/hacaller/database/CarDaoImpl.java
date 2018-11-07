package com.hacaller.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.hacaller.data.CarDao;
import com.hacaller.data.CarData;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_BRAND;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_CAR_PHOTO;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_FOUNDED_YEAR;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_LOGO;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_RATING;
import static com.hacaller.database.CarContractDatabase.CarScheme.COLUMN_WEBSITE;
import static com.hacaller.database.CarContractDatabase.CarScheme.TABLE_NAME;

/**
 * Created by Herbert Caller on 06/11/2018.
 */
public class CarDaoImpl implements CarDao {


    CarDatabaseHelper mDbHelper;

    String[] projection = {
            BaseColumns._ID,
            COLUMN_BRAND,
            COLUMN_LOGO,
            COLUMN_WEBSITE,
            COLUMN_FOUNDED_YEAR,
            COLUMN_CAR_PHOTO,
            COLUMN_RATING
    };

    public CarDaoImpl(Context context){
        mDbHelper = new CarDatabaseHelper(context);
    }

    @Override
    public synchronized void saveCar(CarData carData) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_ID, carData.getId());
        values.put(COLUMN_BRAND, carData.getBrand());
        values.put(COLUMN_LOGO, carData.getLogo());
        values.put(COLUMN_WEBSITE, carData.getWebsite());
        values.put(COLUMN_FOUNDED_YEAR, carData.getFoundedYear());
        values.put(COLUMN_CAR_PHOTO, carData.getCarPhoto());
        values.put(COLUMN_RATING, carData.getRating());
        long newRowId = db.insert(TABLE_NAME, null, values);
        mDbHelper.close();
    }

    @Override
    public List<CarData> getCars() {
        //String selection = COLUMN_BRAND + " LIKE ?";
        //String[] selectionArgs = { "%" };
        //String sortOrder = _ID + " ASC";
        //Cursor cursor =  db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String rawQuery = "SELECT * FROM "+TABLE_NAME+";";
        Cursor cursor = db.rawQuery(rawQuery,null);
        List<CarData> carDataList = new ArrayList<>();
        while(cursor.moveToNext()) {
            int carId = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String brand = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BRAND));
            String logo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGO));
            String website = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WEBSITE));
            int founded = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FOUNDED_YEAR));
            String photo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAR_PHOTO));
            int rating = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RATING));
            carDataList.add(new CarData(carId, logo, website, founded, brand, photo, rating));
        }
        cursor.close();
        mDbHelper.close();
        return carDataList;
    }

    @Override
    public void updateCar(CarData carData) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RATING, carData.getRating());

        // Which row to update, based on the title
        String selection = _ID + " = ?";
        String[] selectionArgs = { String.valueOf(carData.getId()) };

        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        mDbHelper.close();
    }

    @Override
    public void deleteCars() {

    }


}
