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
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void park(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null)
            this.vehicle = vehicle;
        else if (this.vehicle != null)
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
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
        } else if (this.vehicle.equals(vehicle))
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
        return this.vehicle.equals(vehicle);
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
        return this.vehicle != null;
    }
}
