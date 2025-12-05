package com.yearup;

import com.yearup.Contracting.Contract;
import com.yearup.Contracting.ContractFileManager;
import com.yearup.Contracting.LeaseContract;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private DealerShip dealerShip;
    private Scanner scanner = new Scanner(System.in);

    private boolean isRunning = true;

    public UserInterface() {

    }

    private void init() throws IOException {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealerShip = dfm.getDealership();
    }

    public void display() {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(isRunning) {
            System.out.print("""
                =====================================
                          Lars Cars and Bar          
                =====================================
                
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                0 - Sell/Lease vehicle
                99 - Quit
                
                Choose an option: """);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "0":
                    processSellLeaseRequest();
                case "99":
                    isRunning = !isRunning;
                    break;
                default:
                    System.out.println("Please select an option\n");
                    break;
            }
        }
    }

    // collect user information
    // add vehicle information to contract
    // ask if sale or lease (Cannot lease a vehicle over 3 years old)
    // calculate pricing
    private void processSellLeaseRequest() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        ArrayList<Vehicle> vehicles = dealerShip.getAllVehicles();
        displayVehicles(vehicles);
        System.out.println("Select a vehicle by entering a matching vin #: ");
        int chosenVin = Integer.parseInt(scanner.nextLine());
        Vehicle requestedVehicle = dealerShip.getVehicleByVin(chosenVin);

        int dateDifference = Integer.parseInt(today) - requestedVehicle.getYear();

        // check if valid for lease
        if(dateDifference <= 3) {
            System.out.println("Is this a lease?(Y/N): ");
            String leaseResponse = scanner.nextLine();
            if(leaseResponse.trim().equalsIgnoreCase("Y")){
                leaseRequest(new LeaseContract(today, userName, email, requestedVehicle));
            }else {
                sellRequest(requestedVehicle);
            }
        } else {
            sellRequest(requestedVehicle);
        }
    }

    private void leaseRequest(Contract contract) {
        DealershipFileManager dfm = new DealershipFileManager();
        ContractFileManager cfm = new ContractFileManager();

        try {
            dealerShip.removeVehicle(contract.getVehicleSold());
            dfm.saveDealership(dealerShip);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void sellRequest(Vehicle v) {

    }

    private void displayVehicles(List<Vehicle> inventory) {
        DecimalFormat formater = new DecimalFormat(".00");
        String output;
        for (Vehicle vehicle : inventory) {
            output = "Price: $"+ formater.format(vehicle.getPrice()) + ", Year: " + vehicle.getYear() + ", Make: " + vehicle.getMake() + ", Model: " + vehicle.getModel() + ", Color: " + vehicle.getColor() + ", Miles: " + vehicle.getOdometer() + ", Vin " + vehicle.getVin();
            System.out.println(output);
        }
        System.out.println();
    }

    public void processGetByPriceRequest() {
        System.out.print("\nMinimum price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        System.out.println();
        ArrayList<Vehicle> vehiclesInRange = dealerShip.getVehicleByPrice(min, max);
        displayVehicles(vehiclesInRange);
    }

    public void processGetByYearRequest() {
        System.out.print("\nMinimum year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        System.out.println();
        ArrayList<Vehicle> vehiclesInRange = dealerShip.getVehicleByYear(min, max);
        displayVehicles(vehiclesInRange);
    }

    public void processGetByColorRequest() {
        System.out.print("\nRequested color: ");
        String color = scanner.nextLine();
        System.out.println();
        ArrayList<Vehicle> vehiclesInRange = dealerShip.getVehicleByColor(color);
        displayVehicles(vehiclesInRange);
    }

    public void processGetByMileageRequest() {
        System.out.println("\nMinimum mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("Maximum mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        System.out.println();
        ArrayList<Vehicle> vehiclesInRange = dealerShip.getVehicleByMileage(min, max);
        displayVehicles(vehiclesInRange);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("\nRequested vehicle type: ");
        String type = scanner.nextLine();
        System.out.println();
        ArrayList<Vehicle> vehiclesInRange = dealerShip.getVehicleByType(type);
        displayVehicles(vehiclesInRange);
    }

    public void processGetByMakeModelRequest() {
        System.out.println("\nYour car's make: ");
        String make = scanner.nextLine();
        System.out.println("Your car's model: ");
        String model = scanner.nextLine();
        System.out.println();
        ArrayList<Vehicle> returnedVehicles = dealerShip.getVehicleByMakeModel(make, model);
        displayVehicles(returnedVehicles);
    }

    public void processGetAllVehiclesRequest() {
        System.out.println();
        ArrayList<Vehicle> inventory = this.dealerShip.getAllVehicles();
        displayVehicles(inventory);
    }

    public void processAddVehicleRequest() {
        System.out.println("--Add vehicle info--");
        System.out.print("Vin number: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Miles: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealerShip.addVehicle(v);

        DealershipFileManager dfm = new DealershipFileManager();
        try {
            dfm.saveDealership(dealerShip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processRemoveVehicleRequest() {
        ArrayList<Vehicle> vehicles = dealerShip.getAllVehicles();
        DealershipFileManager dfm = new DealershipFileManager();
        displayVehicles(vehicles);
        int vin;
        do
        {
            System.out.println("Select a vehicle by it's vin #: ");
            try {
                vin = Integer.parseInt(scanner.nextLine());
                Vehicle removedVehicle = dealerShip.getVehicleByVin(vin);
                dealerShip.removeVehicle(removedVehicle);
                dfm.saveDealership(dealerShip);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Couldn't parse input, please try again\n");
            }
        }
        while (true);
    }
}
