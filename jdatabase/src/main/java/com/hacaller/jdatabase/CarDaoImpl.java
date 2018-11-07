package com.hacaller.jdatabase;

import com.hacaller.data.CarDao;
import com.hacaller.data.CarData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert Caller on 07/11/2018.
 */
public class CarDaoImpl implements CarDao {

    //When laziness is not a stopper...
    String databaseLocation;

    public CarDaoImpl(String databaseLocation) {
        this.databaseLocation = "jdbc:sqlite:"+databaseLocation;
    }

    public void setDatabaseLocation(String databaseLocation) {
        this.databaseLocation = "jdbc:sqlite:"+databaseLocation;
        System.out.println("Current location-->"+databaseLocation);
    }

    @Override
    public void saveCar(CarData carData) {
        String sql = "INSERT INTO cars(id, brand, logo, website, founded_year, car_photo, rating) " +
                "VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = CarDatabaseUtils.connect(databaseLocation);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carData.getId());
            pstmt.setString(2, carData.getBrand());
            pstmt.setString(3, carData.getLogo());
            pstmt.setString(4, carData.getWebsite());
            pstmt.setInt(5, carData.getFoundedYear());
            pstmt.setString(6, carData.getCarPhoto());
            pstmt.setInt(7, carData.getRating());
            pstmt.executeUpdate();
            System.out.println("Car stored.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public CarData fetchCar(int id) {
        CarData carData = new CarData();
        String sql = "SELECT * FROM cars WHERE id = "+String.valueOf(id);
        try (Connection conn = CarDatabaseUtils.connect(databaseLocation);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                carData = new CarData();
                carData.setId(rs.getInt("id"));
                carData.setBrand(rs.getString("brand"));
                carData.setLogo(rs.getString("logo"));
                carData.setWebsite(rs.getString("website"));
                carData.setFoundedYear(rs.getInt("founded_year"));
                carData.setCarPhoto(rs.getString("car_photo"));
                carData.setRating(rs.getInt("rating"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return carData;
    }

    @Override
    public List<CarData> getCars() {
        List<CarData> carDataList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        try (Connection conn = CarDatabaseUtils.connect(databaseLocation);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                CarData carData = new CarData();
                carData.setId(rs.getInt("id"));
                carData.setBrand(rs.getString("brand"));
                carData.setLogo(rs.getString("logo"));
                carData.setWebsite(rs.getString("website"));
                carData.setFoundedYear(rs.getInt("founded_year"));
                carData.setCarPhoto(rs.getString("car_photo"));
                carData.setRating(rs.getInt("rating"));
                carDataList.add(carData);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Car list processed.");
        return carDataList;
    }

    @Override
    public void updateCar(CarData carData) {
        String sql = "UPDATE cars SET rating = ? WHERE id = ?";
        try (Connection conn = CarDatabaseUtils.connect(databaseLocation);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, carData.getRating());
            pstmt.setInt(2, carData.getId());
            // update
            pstmt.executeUpdate();
            System.out.println("Car updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCars() {

    }

    public void deleteCar(CarData carData) {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (Connection conn = CarDatabaseUtils.connect(databaseLocation);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, carData.getId());
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("Car removed.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
