package com.bridgelabz;

/**
 * Purpose : To simulate parking lot system
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-09
 */
public class ParkingLotSystem {
    private Vehicle vehicle;

    /**
     * Purpose : This method is created to print welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome To The Parking Lot System...");
    }

    /**
     * Purpose : This method is created to park the vehicle
     *
     * @param vehicle : takes vehicle as parameter
     */
    public void park(Vehicle vehicle) {
        if (this.vehicle == null)
            this.vehicle = vehicle;
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle : takes vehicle as parameter
     * @return the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return this.vehicle.equals(vehicle);
    }
}
