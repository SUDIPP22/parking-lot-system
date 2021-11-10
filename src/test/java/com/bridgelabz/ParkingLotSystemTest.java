package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(3);
        owner = new ParkingLotSystemOwner();
    }

    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("PORSCHE", "WB-10KL2356");
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParked_ThenCheckIfUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle("AUDI", "IN-A8002");
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() {
        vehicle = new Vehicle("TOYOTA", "WB-KL4789");
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("HYUNDAI", "WB-P98754");
        Vehicle vehicle2 = new Vehicle("FORD", "IN-658941");
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
        } catch (ParkingLotSystemException exception) {
            Assertions.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                    exception.exceptionType);
        }
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

    @Test
    void givenAVehicle_WhenCheckingIfParkingLotIsFull_ShouldReturnTrue() {
        vehicle = new Vehicle("HYUNDAI", "WB-P98754");
        try {
            parkingLotSystem.park(vehicle);
            boolean checkingIfFull = parkingLotSystem.isParkingLotFull();
            Assertions.assertTrue(checkingIfFull);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenCheckingIfParkingLotIsNotFull_ShouldReturnFalse() {
        vehicle = new Vehicle("JAGUAR", "IN-K9658");
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean checkingIfNotFull = parkingLotSystem.isParkingLotFull();
            Assertions.assertFalse(checkingIfNotFull);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldRedirectToAirportSecurityStaff() {
        vehicle = new Vehicle("ROLLS ROYCE", "WB-KL2695");
        try {
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotSystemException exception) {
            if (parkingLotSystem.isParkingLotFull())
                Assertions.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                        exception.exceptionType);
        }
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldInformTheOwner() {
        parkingLotSystem.addOwnerObserver(owner);
        Vehicle vehicle1 = new Vehicle("AUDI", "WB-L98754");
        Vehicle vehicle2 = new Vehicle("TOYOTA", "IN-65821");
        Vehicle vehicle3 = new Vehicle("ASTON MARTIN", "UK-0096");
        Vehicle vehicle4 = new Vehicle("VOLKSWAGEN", "JPN-1587");
        try {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            boolean capacityFull = owner.getParkingLotStatusIfCapacityFull();
            Assertions.assertTrue(capacityFull);
        } catch (ParkingLotSystemException exception) {
            Assertions.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                    exception.exceptionType);
        }
    }
}
