package com.pluralsight;

import java.util.ArrayList;

public class DealerShip {

    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public DealerShip() {
        this.inventory = new ArrayList<>();
    }

    public DealerShip(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getVehicleByPrice(double min, double max) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(min <= vehicle.getPrice() && vehicle.getPrice() <= max) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public ArrayList<Vehicle> getVehicleByYear(int min, int max) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(min <= vehicle.getYear() && vehicle.getYear() <= max) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public ArrayList<Vehicle> getVehicleByMileage(int min, int max) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(min <= vehicle.getOdometer() && vehicle.getOdometer() <= max) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(vehicle.getMake().trim().equalsIgnoreCase(make) && vehicle.getModel().trim().equalsIgnoreCase(model)) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public Vehicle getVehicleByVin(int vin) {
        Vehicle thisVehicle = null;

        for(Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                thisVehicle = vehicle;
                break;
            } else {
                continue;
            }
        }

        return thisVehicle;
    }

    public ArrayList<Vehicle> getVehicleByColor(String color) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(vehicle.getColor().trim().equalsIgnoreCase(color)) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public ArrayList<Vehicle> getVehicleByType(String type) {
        ArrayList<Vehicle> carsInRange = new ArrayList<>();

        for(Vehicle vehicle : inventory) {
            if(vehicle.getVehicleType().trim().equalsIgnoreCase(type)) {
                carsInRange.add(vehicle);
            }
        }

        return carsInRange;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

}
