package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;
    AirportSecurity airportSecurity = null;
    ParkingLotAttendant attendant = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(3);
        owner = new ParkingLotSystemOwner();
        airportSecurity = new AirportSecurity();
        attendant = new ParkingLotAttendant();
    }

    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("PORSCHE", "WB-10KL2356");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("TOYOTA", "WB-KL4789");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
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
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unPark(vehicle));
    }

    @Test
    void givenAVehicle_WhenCheckingIfParkingLotIsFull_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("HYUNDAI", "WB-P98754");
        parkingLotSystem.park(vehicle);
        boolean checkingIfFull = parkingLotSystem.isParkingLotFull();
        Assertions.assertFalse(checkingIfFull);
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
        parkingLotSystem.addObserver(owner);
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

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        parkingLotSystem.addObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("BUGATTI", "WB-K96523");
        Vehicle vehicle2 = new Vehicle("LEXUS", "IN-49821");
        Vehicle vehicle3 = new Vehicle("FIAT", "UK-1159");
        Vehicle vehicle4 = new Vehicle("MITSUBISHI", "JPN-2689");
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

    @Test
    void givenVehicles_WhenParkingLotHasSpaceAgain_ShouldInformTheOwner() throws ParkingLotSystemException {
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle1 = new Vehicle("FORD", "IN-MH2546");
        Vehicle vehicle2 = new Vehicle("CHEVROLET", "IN-MP0023");
        Vehicle vehicle3 = new Vehicle("TATA MOTORS", "WB-2698");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assertions.assertTrue(parkingLotSystem.isParkingLotFull());
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unPark(vehicle3));
        Assertions.assertFalse(owner.getParkingLotStatusIfCapacityFull());
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ThenCheckIfParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("FORD", "IN-MH2546");
        attendant.parkedByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }
}
