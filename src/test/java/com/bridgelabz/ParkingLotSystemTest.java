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
        vehicle = new Vehicle("PORSCHE", "WB-10KL2356", "6:30", "Blue");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("TOYOTA", "WB-KL4789", "10:30", "Black");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("HYUNDAI", "WB-P98754", "9:30", "Green");
        Vehicle vehicle2 = new Vehicle("FORD", "IN-658941", "7:00", "Red");
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
        vehicle = new Vehicle("HYUNDAI", "WB-P98754", "11:00", "White");
        parkingLotSystem.park(vehicle);
        boolean checkingIfFull = parkingLotSystem.isParkingLotFull();
        Assertions.assertFalse(checkingIfFull);
    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldRedirectToAirportSecurityStaff() {
        vehicle = new Vehicle("ROLLS ROYCE", "WB-KL2695", "14:45", "Sky Blue");
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
        Vehicle vehicle1 = new Vehicle("AUDI", "WB-L98754", "7:50", "Black");
        Vehicle vehicle2 = new Vehicle("TOYOTA", "IN-65821", "12:30", "Grey");
        Vehicle vehicle3 = new Vehicle("ASTON MARTIN", "UK-0096", "15:30", "Red");
        Vehicle vehicle4 = new Vehicle("VOLKSWAGEN", "JPN-1587", "11:00", "Blue");
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
        Vehicle vehicle1 = new Vehicle("BUGATTI", "WB-K96523", "17:40", "Green");
        Vehicle vehicle2 = new Vehicle("LEXUS", "IN-49821", "9:45", "Grey");
        Vehicle vehicle3 = new Vehicle("FIAT", "UK-1159", "15:46", "Royal Blue");
        Vehicle vehicle4 = new Vehicle("MITSUBISHI", "JPN-2689", "19:30", "Red");
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
        Vehicle vehicle1 = new Vehicle("FORD", "IN-MH2546", "6:30", "White");
        Vehicle vehicle2 = new Vehicle("CHEVROLET", "IN-MP0023", "9:30", "Grey");
        Vehicle vehicle3 = new Vehicle("TATA MOTORS", "WB-2698", "11:30", "Red");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assertions.assertTrue(parkingLotSystem.isParkingLotFull());
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unPark(vehicle3));
        Assertions.assertFalse(owner.getParkingLotStatusIfCapacityFull());
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ThenCheckIfParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("FORD", "IN-MH2546", "15:20", "Grey");
        attendant.parkedByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicle_WhenParked_ThenCheckForPosition_ShouldReturnPosition() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("CHEVROLET", "IN-MP0023", "11:00", "White");
        Vehicle vehicle2 = new Vehicle("AUDI", "IN-KL003", "9:00", "Grey");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        int vehiclePositionForVehicle1 = parkingLotSystem.getVehiclePosition(vehicle1);
        int vehiclePositionForVehicle2 = parkingLotSystem.getVehiclePosition(vehicle2);
        Assertions.assertEquals(0, vehiclePositionForVehicle1);
        Assertions.assertEquals(1, vehiclePositionForVehicle2);
    }

    @Test
    void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "Black");
        parkingLotSystem.park(vehicle);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("11:00", vehicleParkingTime);
    }

    @Test
    void givenAVehicle_WhenSearchForParkedWhiteColorVehicle_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "White");
        Vehicle vehicle2 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "Red");
        Vehicle vehicle3 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "White");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int positionOfVehicle1 = parkingLotSystem.getWhiteColorVehiclePosition(vehicle1);
        Assertions.assertThrows(ParkingLotSystemException.class,
                () -> parkingLotSystem.getWhiteColorVehiclePosition(vehicle2));
        int positionOfVehicle3 = parkingLotSystem.getWhiteColorVehiclePosition(vehicle3);
        System.out.println(positionOfVehicle1);
        System.out.println(positionOfVehicle3);
        Assertions.assertEquals(0, positionOfVehicle1);
        Assertions.assertEquals(2, positionOfVehicle3);
    }

    @Test
    void givenAVehicle_WhenSearchForBlueToyota_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("BMW", "KL-MH25698", "11:00", "White");
        Vehicle vehicle2 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "Red");
        Vehicle vehicle3 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "Blue");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assertions.assertThrows(ParkingLotSystemException.class,
                () -> parkingLotSystem.getBlueColorToyotaVehiclePosition(vehicle1));
        Assertions.assertThrows(ParkingLotSystemException.class,
                () -> parkingLotSystem.getBlueColorToyotaVehiclePosition(vehicle2));
        int positionOfVehicle3 = parkingLotSystem.getBlueColorToyotaVehiclePosition(vehicle3);
        Assertions.assertEquals(2, positionOfVehicle3);
    }

    @Test
    void givenAVehicle_WhenSearchForBlueToyota_ShouldReturnTheVehicleNumber() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("BMW", "KL-JK28678", "11:00", "White");
        Vehicle vehicle2 = new Vehicle("TOYOTA", "KL-MH25698", "11:00", "Blue");
        Vehicle vehicle3 = new Vehicle("AUDI", "KL-MP36908", "11:00", "Blue");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        Assertions.assertThrows(ParkingLotSystemException.class,
                () -> parkingLotSystem.getBlueColorToyotaVehicleNumber(vehicle1));
        String actualVehicleNumber = parkingLotSystem.getBlueColorToyotaVehicleNumber(vehicle2);
        Assertions.assertEquals(vehicle2.getVehicleNumber(), actualVehicleNumber);
        Assertions.assertThrows(ParkingLotSystemException.class,
                () -> parkingLotSystem.getBlueColorToyotaVehicleNumber(vehicle3));
    }
}
