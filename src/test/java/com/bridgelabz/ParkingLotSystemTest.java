package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("PORSCHE", "WB-10KL2356");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ThenCheckIfUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("AUDI", "IN-A8002");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() {
        vehicle = new Vehicle("TOYOTA", "WB-KL4789");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("HYUNDAI", "WB-P98754");
        Vehicle vehicle2 = new Vehicle("FORD", "IN-658941");
        parkingLotSystem.park(vehicle1);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertFalse(isParked);
    }

    @Test
    void givenANullVehicle_WhenUnParked_ShouldThrowException() {
        try {
            vehicle = null;
            parkingLotSystem.unPark(vehicle);
        } catch (ParkingLotSystemException exception) {
            Assertions.assertEquals(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, exception.exceptionType);
        }
    }
}
