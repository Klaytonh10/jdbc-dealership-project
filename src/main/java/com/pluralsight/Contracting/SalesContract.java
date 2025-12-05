package com.pluralsight.Contracting;

import com.pluralsight.Vehicle;

public class SalesContract extends Contract {

    private final static double salesTaxAmount = 0.05;
    private final static double recordingFee = 100;
    private final static double processingFeeUnder = 295;
    private final static double processingFeeOver = 495;
    private boolean isFinancing = false;

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinancing) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinancing = isFinancing;
    }

    public double getProcessingFee() {
        return 0;
    }

    public static double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public static double getRecordingFee() {
        return recordingFee;
    }

    @Override
    public double getTotalPrice(){
        boolean isOver;
        double withTax = (getVehicleSold().getPrice() * salesTaxAmount) + (getVehicleSold().getPrice() + recordingFee);
        double total = 0;

        if(getVehicleSold().getPrice() < 10_000) {
            total = withTax + processingFeeUnder;
        } else {
            isOver = true;
            total = withTax + processingFeeOver;
        }

        return total;
    }

    @Override
    public double getMonthlyPayment() {

        return 0;
    }

}
