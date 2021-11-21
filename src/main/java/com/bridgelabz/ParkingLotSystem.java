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
    private static List<Vehicle> vehicles;
    private final int actualCapacity;
    private final List<Observer> observers;
    private ParkingLotSystemOwner owner;
    private Vehicle vehicle;

    public ParkingLotSystem(int capacity) {
        vehicles = new ArrayList<>();
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
     * @param vehicle : takes vehicle as parameter to park each vehicle in parking lot
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void park(Vehicle vehicle) throws ParkingLotSystemException {
        ParkingLotSystem.vehicles.add(vehicle);
        if (ParkingLotSystem.vehicles.size() - 1 == this.actualCapacity) {
            for (Observer observer : observers) {
                observer.isFullCapacity();
            }
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
    }

    /**
     * Purpose : This method is created to unpark the vehicle
     *
     * @param vehicle : takes vehicle as parameter to unpark each vehicle in parking lot
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public void unPark(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null) {
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "Vehicles can not be null");
        } else if (ParkingLotSystem.vehicles.contains(vehicle)) {
            ParkingLotSystem.vehicles.remove(vehicle);
            for (Observer observer : observers) {
                observer.isParkingCapacityAvailable();
            }
        }
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle : takes vehicle as parameter for checking if that particular vehicle is parked
     * @return the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return ParkingLotSystem.vehicles.contains(vehicle);
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is unparked or not
     *
     * @param vehicle : takes vehicle as parameter for checking if that particular vehicle is unparked
     * @return the vehicle is unparked
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return !ParkingLotSystem.vehicles.contains(vehicle);
    }

    /**
     * Purpose : This method is created for checking whether the parking lot is full or not
     *
     * @return the checked value
     */
    public boolean isParkingLotFull() {
        return vehicles.size() == this.actualCapacity;
    }

    /**
     * Purpose : This method is created to add parking lot owner observer
     *
     * @param observer : takes the parking lot owner as parameter
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : This method is created to get back the position of parked car
     *
     * @param vehicle : takes vehicle as parameter to get back the particular vehicle location in parking lot
     * @return the position of the vehicle if the vehicle is parked
     * @throws ParkingLotSystemException : when no vehicle is found
     */
    public int getVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }

    /**
     * Purpose : This method is created to get back the parking time of vehicle
     *
     * @param vehicle : takes vehicle as parameter for getting back the parking time of that particular vehicle
     * @return the parking time of the vehicle if the vehicle is parked
     * @throws ParkingLotSystemException : when no vehicle is found
     */
    public String getVehicleParkingTime(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)) {
            for (Vehicle parkingTimeForVehicle : vehicles) {
                if (parkingTimeForVehicle.equals(vehicle))
                    return parkingTimeForVehicle.getParkingTime();
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Parked");
    }

    /**
     * Purpose : This method is created to know the location of all parked white cars
     *
     * @param vehicle : takes vehicle as parameter for checking the particular color of parked vehicle is white
     * @return the index position of the particular vehicle to get back the location
     * @throws ParkingLotSystemException : when no such white color vehicle is found
     */
    public int getWhiteColorVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle) && vehicle.getVehicleColor().equals("White"))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }

    /**
     * Purpose : This method is created to know the location of blue color Toyota vehicle
     *
     * @param vehicle : takes vehicle as parameter for checking the blue color Toyota vehicle's location
     * @return the index position of that particular vehicle
     * @throws ParkingLotSystemException : when no such blue color Toyota vehicle is found
     */
    public int getBlueColorToyotaVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("TOYOTA"))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }

    /**
     * Purpose : This method is created to know the vehicle plate number of blue color Toyota vehicle
     *
     * @param vehicle : takes vehicle as parameter for checking the blue color Toyota vehicle's plate number
     * @return the vehicle number of that particular vehicle
     * @throws ParkingLotSystemException : when no such blue color Toyota is found
     */
    public String getBlueColorToyotaVehicleNumber(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("TOYOTA"))
            for (Vehicle vehicleNumberPlate : vehicles) {
                if (vehicleNumberPlate.equals(vehicle))
                    return vehicleNumberPlate.getVehicleNumber();
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Parked");
    }

    /**
     * Purpose : This method is created to know the location of parked BMW vehicle
     *
     * @param vehicle : takes vehicle as parameter to get back the index of that particular vehicle
     * @return the index position of that particular vehicle
     * @throws ParkingLotSystemException : when no such parked BMW vehicle is found
     */
    public int getBMWVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getName().equals("BMW"))
            for (Vehicle position : vehicles) {
                if (position.equals(vehicle))
                    return vehicles.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }
}
