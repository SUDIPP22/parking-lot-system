package com.bridgelabz;

/**
 * Purpose : Simulation of Exception for parking lot system
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-10
 */
public class ParkingLotSystemException extends Exception {
    public ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {NO_SUCH_VEHICLE}
}
