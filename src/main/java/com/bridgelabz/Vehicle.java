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

    public Vehicle(String name, String vehicleNumber) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
