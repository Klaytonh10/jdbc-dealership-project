package com.yearup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    private String file = "src/main/resources/inventory2.csv";
    private DealerShip dealerShip;

    //responsible for reading the dealership file,
    //parsing the data, and creating a Dealership object full of vehicles from the
    //file

    public DealerShip getDealership() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String input;
        String dealershipString = reader.readLine();
        String[] dealershipArray = dealershipString.split("\\|");
        String title = dealershipArray[0];
        String address = dealershipArray[1];
        String phone = dealershipArray[2];
        dealerShip = new DealerShip(title, address, phone);
        //reader.readLine();
        while ((input = reader.readLine()) != null) {
            String[] sections = input.split("\\|");

            int vin = Integer.parseInt(sections[0]);
            int year = Integer.parseInt(sections[1]);
            String make = sections[2];
            String model = sections[3];
            String vehicleType = sections[4];
            String color = sections[5];
            int odometer = Integer.parseInt(sections[6]);
            double price = Double.parseDouble(sections[7]);
            dealerShip.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));

        }
        //System.out.println("\n" + dealerShip.getName() + " created! \nWould you like to save this dealership?\n");
        return dealerShip;
    }


    // this could be completely wrong so let's not touch that right now
    public void saveDealership(DealerShip thisDealerShip) throws IOException {
        dealerShip = thisDealerShip;
        List<Vehicle> vehicles = dealerShip.getAllVehicles();

        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String titleString = dealerShip.getName() + "|" + dealerShip.getAddress() + "|" + dealerShip.getPhone();
        bufferedWriter.write(titleString+"\n");
        for (Vehicle vehicle : vehicles) {
            String dealString = vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "\n";
            bufferedWriter.write(dealString);
        }
        bufferedWriter.close();
        System.out.println(dealerShip.getName() + " saved!\n");
    }
}


