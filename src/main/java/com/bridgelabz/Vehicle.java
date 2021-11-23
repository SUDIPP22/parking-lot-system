package com.bridgelabz;

import java.time.LocalTime;

/**
 * Purpose : For creating vehicle properties
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-09
 */
public class Vehicle {
    private final String name;
    private final Size vehicleSize;
    private final String vehicleNumber;
    private final String vehicleColor;
    private boolean handicapped;
    private LocalTime parkingTime;

    public Vehicle(String name, Size vehicleSize, String vehicleNumber, String vehicleColor) {
        this.name = name;
        this.vehicleSize = vehicleSize;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
    }

    public Vehicle(String name, Size vehicleSize, String vehicleNumber, String vehicleColor, boolean handicapped, LocalTime parkingTime) {
        this.name = name;
        this.vehicleSize = vehicleSize;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
        this.handicapped = handicapped;
        this.parkingTime = parkingTime;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public boolean isHandicapped() {
        return handicapped;
    }

    public LocalTime getParkingTime() {
        return parkingTime;
    }

    enum Size {LARGE, SMALL}
}
