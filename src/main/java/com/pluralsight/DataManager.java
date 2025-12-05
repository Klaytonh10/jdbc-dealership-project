package com.pluralsight;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.ArrayList;

public class DataManager {

    MysqlDataSource mysqlDataSource;

    public void setMysqlDataSource(MysqlDataSource mysqlDataSource) {
        this.mysqlDataSource = mysqlDataSource;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        return null;
    }
}
