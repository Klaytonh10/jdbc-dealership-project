package com.yearup.Contracting;

import com.yearup.Vehicle;

public class LeaseContract extends Contract {

    private double expectedEndingValue; // 50% of the original price
    private double leaseFee;
    private double monthlyPaymentFinancing; // All leases 4.0% for 36/months

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = getExpectedEndingValue();
        this.leaseFee = getLeaseFee();
        this.monthlyPaymentFinancing = getMonthlyPaymentFinancing();
    }

    public double getExpectedEndingValue() {
        expectedEndingValue = getVehicleSold().getPrice()/2;
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        leaseFee = getVehicleSold().getPrice() * 0.7;
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getMonthlyPaymentFinancing() {

        return monthlyPaymentFinancing;
    }

    public void setMonthlyPaymentFinancing(double monthlyPaymentFinancing) {
        this.monthlyPaymentFinancing = monthlyPaymentFinancing;
    }

    @Override
    public double getTotalPrice() {

        return 0;
    }

    @Override
    public double getMonthlyPayment() {

        return 0;
    }
}
