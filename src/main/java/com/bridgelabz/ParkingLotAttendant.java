package com.bridgelabz;

/**
 * Purpose : To simulate parking and unparking of vehicles by parking lot attendant
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-12
 */
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(3);

    /**
     * Purpose : This method is created to park the car by attendant in parking lot
     *
     * @param vehicle : takes vehicle as a parameter to park that particular car by attendant in parking lot
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void parkedByAttendant(Vehicle vehicle) throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
    }

    /**
     * Purpose : This method is created to unpark the car by attendant in parking lot
     *
     * @param vehicle : takes vehicle as a parameter to unpark that particular car by attendant in parking lot
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public void unparkedByAttendant(Vehicle vehicle) throws ParkingLotSystemException {
        parkingLotSystem.unPark(vehicle);
    }
}
