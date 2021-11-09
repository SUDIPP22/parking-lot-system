package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("PORSCHE", "WB-10KL2356");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ThenCheckIfUnParked_ShouldReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("AUDI", "IN-A8002");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle = new Vehicle("TOYOTA", "WB-KL4789");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        Vehicle vehicle1 = new Vehicle("HYUNDAI", "WB-P98754");
        Vehicle vehicle2 = new Vehicle("FORD", "IN-658941");
        parkingLotSystem.park(vehicle1);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertFalse(isParked);
    }
}
