package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : To simulate parking lot system
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-09
 */
public class ParkingLotSystem {
    private final int actualCapacity;
    private List<Vehicle> vehicle;
    private ParkingLotSystemOwner owner;
    private List<Observer> observers;


    public ParkingLotSystem(int capacity) {
        this.vehicle = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.actualCapacity = capacity;
    }

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
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void park(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.vehicle.size() == this.actualCapacity) {
            for (Observer observer: observers) {
                observer.setParkingLotCapacity();
            }
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
        this.vehicle.add(vehicle);
    }

    /**
     * Purpose : This method is created to unpark the vehicle
     *
     * @param vehicle : takes vehicle as parameter
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null) {
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "Vehicles can not be null");
        } else if (this.vehicle.contains(vehicle))
            this.vehicle = null;
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle : takes vehicle as parameter
     * @return the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return this.vehicle.contains(vehicle);
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is unparked or not
     *
     * @param vehicle : takes vehicle as parameter
     * @return the vehicle is unparked
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return this.vehicle == null;
    }

    /**
     * Purpose : This method is created for checking whether the parking lot is full or not
     *
     * @return the checked value
     */
    public boolean isParkingLotFull() {
        return this.vehicle.size() == this.actualCapacity;
    }

    /**
     * Purpose : This method is created to add parking lot owner observer
     *
     * @param observer : takes the parking lot owner as parameter
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
}
