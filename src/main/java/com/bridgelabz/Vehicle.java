package com.bridgelabz;

/**
 * Purpose : For creating vehicle properties
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-09
 */
public class Vehicle {
    private final String name;
    private final String vehicleNumber;
    private final String parkingTime;
    private final String vehicleColor;

    public Vehicle(String name, String vehicleNumber, String parkingTime, String vehicleColor) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.parkingTime = parkingTime;
        this.vehicleColor = vehicleColor;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }
}
