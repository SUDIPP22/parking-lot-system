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
}
