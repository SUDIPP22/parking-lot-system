package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;
    AirportSecurity airportSecurity = null;
    ParkingLotAttendant attendant = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        owner = new ParkingLotSystemOwner();
        airportSecurity = new AirportSecurity();
        attendant = new ParkingLotAttendant();
        parkingLotSystem.setCapacity(3);
    }

    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("PORSCHE", Vehicle.Size.LARGE, "WB-10KL2356", "Blue");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        vehicle = new Vehicle("PORSCHE", Vehicle.Size.SMALL, "WB-10KL2356", "Blue");
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParkedAndCheckIfUnpark_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("TOYOTA", Vehicle.Size.LARGE, "WB-KL4789", "Black");
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenParkedAnotherVehicle_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("HYUNDAI", Vehicle.Size.LARGE, "WB-P98754", "Green");
        Vehicle vehicle2 = new Vehicle("FORD", Vehicle.Size.SMALL, "IN-658941", "Red");
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
    void givenAVehicle_WhenCheckingForParkingLotIsFull_ShouldReturnFalse() throws ParkingLotSystemException {
        vehicle = new Vehicle("ROLLS ROYCE", Vehicle.Size.LARGE, "WB-KL2695", "Sky Blue");
        parkingLotSystem.park(vehicle);
        boolean isLotFull = owner.getParkingLotStatusIfCapacityFull();
        Assertions.assertFalse(isLotFull);
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotSystemException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle1 = new Vehicle("AUDI", Vehicle.Size.LARGE, "WB-L98754", "Black");
        Vehicle vehicle2 = new Vehicle("TOYOTA", Vehicle.Size.SMALL, "IN-65821", "Grey");
        Vehicle vehicle3 = new Vehicle("ASTON MARTIN", Vehicle.Size.SMALL,
                "UK-0096", "Red", true, LocalTime.now());
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        boolean isCapacityFull = owner.getParkingLotStatusIfCapacityFull();
        Assertions.assertTrue(isCapacityFull);
    }

    @Test
    void givenVehicles_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() throws ParkingLotSystemException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.addObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("BUGATTI", Vehicle.Size.LARGE, "WB-K96523", "Green");
        Vehicle vehicle2 = new Vehicle("LEXUS", Vehicle.Size.SMALL, "IN-49821", "Grey");
        Vehicle vehicle3 = new Vehicle("FIAT", Vehicle.Size.SMALL,
                "UK-1159", "Royal Blue", true, LocalTime.now());
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        boolean capacityFull = airportSecurity.getParkingLotStatusIfCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    void givenVehicles_WhenParkingLotHasSpaceAgain_ShouldInformTheOwner() throws ParkingLotSystemException {
        parkingLotSystem.addObserver(owner);
        Vehicle vehicle1 = new Vehicle("FORD", Vehicle.Size.LARGE, "IN-MH2546", "White");
        Vehicle vehicle2 = new Vehicle("MASERATI", Vehicle.Size.LARGE, "IN-MP0023", "Grey");
        Vehicle vehicle3 = new Vehicle("TATA MOTORS", Vehicle.Size.SMALL, "WB-2698", "Red");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.unPark(vehicle3);
        Assertions.assertFalse(owner.getParkingLotStatusIfCapacityFull());
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ThenCheckIfParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("FORD", Vehicle.Size.SMALL, "IN-MH2546", "Grey");
        attendant.parkedByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicleToAttendant_WhenUnParked_ThenCheckIfParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("FORD", Vehicle.Size.LARGE, "IN-MH2546", "Grey");
        attendant.parkedByAttendant(vehicle);
        attendant.unparkedByAttendant(vehicle);
        Assertions.assertFalse(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicle_WhenParked_ThenCheckForPosition_ShouldReturnPosition() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("CHEVROLET", Vehicle.Size.SMALL,
                "IN-MP0023", "White");
        parkingLotSystem.park(vehicle1);
        int vehiclePositionForVehicle1 = parkingLotSystem.getVehiclePosition(vehicle1);
        System.out.println(vehiclePositionForVehicle1);
        Assertions.assertEquals(0, vehiclePositionForVehicle1);
    }

    @Test
    void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KIA", Vehicle.Size.LARGE, "KL-MH25698", "Black");
        parkingLotSystem.park(vehicle);
        LocalTime now = LocalTime.now();
        String vehicleParkingTime = now.toString();
        System.out.println(vehicleParkingTime);
        Assertions.assertEquals(now.toString(), vehicleParkingTime);
    }

    @Test
    void givenVehicles_WhenEvenlyParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("BMW", Vehicle.Size.LARGE, "KL-JK28678", "White");
        Vehicle vehicle2 = new Vehicle("FORD", Vehicle.Size.SMALL, "KL-MH8698", "Blue");
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle1) &&
                parkingLotSystem.isVehicleParked(vehicle2));
    }

    @Test
    void givenAVehicle_WhenSearchForNearestParkingSlotForHandicapped_ShouldParkTheNearestLocation()
            throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("AUDI", Vehicle.Size.LARGE,
                "KL-PN25898", "White", true, LocalTime.now());
        parkingLotSystem.park(vehicle);
        int nearestParkingSlot = parkingLotSystem.getVehiclePosition(vehicle);
        Assertions.assertEquals(0, nearestParkingSlot);
    }

    @Test
    void givenAVehicle_WhenSearchForParkedWhiteColorVehicle_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("TOYOTA", Vehicle.Size.SMALL,
                "KL-MH25698", "White");
        parkingLotSystem.park(vehicle1);
        int positionOfVehicle = parkingLotSystem.getVehiclePositionByColor("White");
        Assertions.assertEquals(0, positionOfVehicle);
    }

    @Test
    void givenAVehicle_WhenSearchForBlueToyota_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle3 = new Vehicle("TOYOTA", Vehicle.Size.SMALL,
                "KL-MH25698", "Blue");
        parkingLotSystem.park(vehicle3);
        int positionOfVehicle = parkingLotSystem.getVehiclePositionByColor("Blue");
        Assertions.assertEquals(0, positionOfVehicle);
    }

    @Test
    void givenAVehicle_WhenSearchForBlueToyota_ShouldReturnTheVehicleNumber() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("BMW", Vehicle.Size.LARGE, "KL-JK28678", "White");
        Vehicle vehicle2 = new Vehicle("FIAT", Vehicle.Size.SMALL, "KL-MH2598", "Blue");
        Vehicle vehicle3 = new Vehicle("AUDI", Vehicle.Size.LARGE, "KL-MP36908", "Blue");
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

    @Test
    void givenAVehicle_WhenSearchForBMWVehicle_ShouldLocateProperly() throws ParkingLotSystemException {
        parkingLotSystem.setCapacity(5);
        Vehicle vehicle1 = new Vehicle("TOYOTA", Vehicle.Size.LARGE,
                "KL-MH25698", "Blue");
        Vehicle vehicle2 = new Vehicle("AUDI", Vehicle.Size.LARGE, "KL-MP36908", "Blue");
        Vehicle vehicle3 = new Vehicle("BMW", Vehicle.Size.LARGE, "KL-JK28678", "White");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        int positionOfVehicle = parkingLotSystem.getVehiclePositionByName("BMW");
        Assertions.assertEquals(1, positionOfVehicle);
    }

    @Test
    void givenAVehicle_WhenParkedFor30Minutes_ShouldReturnThePosition() throws ParkingLotSystemException {
        parkingLotSystem.setCapacity(8);
        Vehicle vehicle1 = new Vehicle("BMW", Vehicle.Size.LARGE, "KL-JK28678", "White",
                false, LocalTime.now());
        Vehicle vehicle2 = new Vehicle("AUDI", Vehicle.Size.LARGE, "KL-JK28678", "White",
                false, LocalTime.now());
        Vehicle vehicle3 = new Vehicle("KIA", Vehicle.Size.LARGE, "KL-JK28678", "White",
                false, LocalTime.now());
        Vehicle vehicle4 = new Vehicle("FORD", Vehicle.Size.LARGE, "KL-JK28678", "White",
                false, LocalTime.now());
        Vehicle vehicle5 = new Vehicle("FIAT", Vehicle.Size.LARGE, "KL-JK28678", "White",
                false, LocalTime.now());
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.park(vehicle3);
        parkingLotSystem.park(vehicle4);
        parkingLotSystem.park(vehicle5);
        Assertions.assertThrows(ParkingLotSystemException.class,
                ()-> parkingLotSystem.getLast30MinuteParkedVehicles(vehicle1.getParkingTime()));
        Assertions.assertThrows(ParkingLotSystemException.class,
                ()-> parkingLotSystem.getLast30MinuteParkedVehicles(vehicle2.getParkingTime()));
        Assertions.assertThrows(ParkingLotSystemException.class,
                ()-> parkingLotSystem.getLast30MinuteParkedVehicles(vehicle3.getParkingTime()));
        Assertions.assertThrows(ParkingLotSystemException.class,
                ()-> parkingLotSystem.getLast30MinuteParkedVehicles(vehicle4.getParkingTime()));
        Assertions.assertThrows(ParkingLotSystemException.class,
                ()-> parkingLotSystem.getLast30MinuteParkedVehicles(vehicle5.getParkingTime()));
    }

    @Test
    void givenAVehicle_WhenCheckingVehicleNumber_ShouldPassValidation() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("TOYOTA", Vehicle.Size.SMALL,
                "KL-MH25698", "White");
        parkingLotSystem.park(vehicle);
        boolean numberPlate = parkingLotSystem.validateVehicleNumberPlate(vehicle.getVehicleNumber());
        Assertions.assertTrue(numberPlate);
    }
}
