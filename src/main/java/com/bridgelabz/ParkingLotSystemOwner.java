package com.bridgelabz;

/**
 * Purpose : To operate as an observer by parking lot owner
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-10
 */
public class ParkingLotSystemOwner implements Observer {
    private boolean isParkingLotFull;

    /**
     * Purpose : This method is created to set the status of full capacity of parking lot
     */
    @Override
    public void isFullCapacity() {
        isParkingLotFull = true;
    }

    /**
     * Purpose : This method is created to set the status of parking capacity available in the parking lot
     */
    @Override
    public void isParkingCapacityAvailable() {
        isParkingLotFull = false;
    }

    /**
     * Purpose : This method is created to get back the status of full capacity of parking lot
     *
     * @return the status of the parking lot
     */
    public boolean getParkingLotStatusIfCapacityFull() {
        return this.isParkingLotFull;
    }
}
