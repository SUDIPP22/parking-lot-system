package com.bridgelabz;

/**
 * Purpose : To segregate the setParkingLotCapacity() and
 * setParkingCapacityAvailable() method for parking lot system
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-11
 */
public interface Observer {
    void isFullCapacity();

    void isParkingCapacityAvailable();
}
