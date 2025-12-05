package com.pluralsight;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataManager {

    private MysqlDataSource dataSource;
    private Scanner scanner = new Scanner(System.in);

    public void setMysqlDataSource(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DealerShip getDealership() {

        HashMap<Integer,DealerShip> dealerShips = new HashMap<>();

        String query = "select * from dealerships;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                DealerShip dealerShip = new DealerShip(name, address, phone);
                dealerShips.put(id,dealerShip);
                System.out.println(id + " " + name + " " + address + " " + phone);
            }
            boolean valid = true;
            DealerShip selected = null;
            while(valid){
                System.out.print("\nSelect a dealership: ");
                int input = Integer.parseInt(scanner.nextLine());
                if (dealerShips.containsKey(input)) {
                    selected = dealerShips.get(input);
                    valid = !valid;
                } else {
                    System.out.println("invalid selection\n");
                }
            }
            return selected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        String query = "select * from car_dealership.vehicles\n" +
                "where car_dealership.vehicles.price <= ? " +
                "and car_dealership.vehicles.price >= ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet result = preparedStatement.executeQuery();



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
