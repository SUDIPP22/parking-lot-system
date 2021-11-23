package com.bridgelabz;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Purpose : To simulate parking lot system
 *
 * @author SUDIP PANJA
 * @version '1.0-SNAPSHOT'
 * @since 2021-11-09
 */
public class ParkingLotSystem {
    private static List<Vehicle> parkingLot1;
    private static List<Vehicle> parkingLot2;
    private static List<Vehicle> parkingLotForHandicapped;
    private static int actualCapacity;
    private final List<Observer> observers;
    private ParkingLotSystemOwner owner;
    private Vehicle vehicle;

    public ParkingLotSystem() {
        parkingLot1 = new ArrayList<>(actualCapacity);
        parkingLot2 = new ArrayList<>(actualCapacity);
        parkingLotForHandicapped = new ArrayList<>(actualCapacity);
        this.observers = new ArrayList<>();
    }

    /**
     * Purpose : This method is created to print welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome To The Parking Lot System...");
    }

    /**
     * Purpose :This method is created to set the capacity of parking lot
     *
     * @param capacity : takes the maximum capacity of parking lot
     */
    public void setCapacity(int capacity) {
        actualCapacity = capacity;
    }

    /**
     * Purpose : This method is created to park the vehicle
     *
     * @param vehicle : takes vehicle as parameter to park each vehicle in parking lot
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void park(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity
                && parkingLotForHandicapped.size() == actualCapacity) {
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
        if (isVehicleParked(vehicle))
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle is already parked");
        if (vehicle.isHandicapped()) {
            if (parkingLotForHandicapped.size() > actualCapacity)
                throw new ParkingLotSystemException
                        (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is full");
            parkingLotForHandicapped.add(vehicle);
        }
        if (!vehicle.isHandicapped()) {
            if (parkingLot1.size() > parkingLot2.size()) {
                parkingLot2.add(vehicle);
            } else parkingLot1.add(vehicle);
        }
        checkingForCapacity();
    }

    /**
     * Purpose : This method is created to check the capacity and informing observers for park() method
     */
    private void checkingForCapacity() {
        if (parkingLot1.size() == actualCapacity && parkingLot2.size() == actualCapacity
                && parkingLotForHandicapped.size() == actualCapacity) {
            for (Observer observer : observers) {
                observer.isFullCapacity();
            }
        }
    }

    /**
     * Purpose : This method is created to unpark the vehicle
     *
     * @param vehicle : takes vehicle as parameter to unpark each vehicle from slot in parking lot
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public boolean unPark(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLot1 == null || parkingLot2 == null || parkingLotForHandicapped == null) {
            return false;
        }
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle)) {
                parkingLot1.remove(vehicle);
                for (Observer observer : observers) {
                    observer.isParkingCapacityAvailable();
                }
                return true;
            }
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                parkingLot2.remove(vehicle);
                for (Observer observer : observers) {
                    observer.isParkingCapacityAvailable();
                }
                return true;
            }
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.equals(vehicle)) {
                parkingLotForHandicapped.remove(vehicle);
                for (Observer observer : observers) {
                    observer.isParkingCapacityAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotSystemException
                (ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle : takes vehicle as parameter for checking if that particular vehicle is parked
     * @return the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle slot : parkingLot1) {
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle)) {
                isParked = true;
                break;
            }
        }
        return isParked;
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is unparked or not
     *
     * @param vehicle : takes vehicle as parameter for checking if that particular vehicle is unparked
     * @return the vehicle is unparked
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        boolean isUnParked = false;
        for (Vehicle slot : parkingLot1) {
            if (!slot.equals(vehicle)) {
                isUnParked = true;
                break;
            }
        }
        for (Vehicle slot : parkingLot2) {
            if (!slot.equals(vehicle)) {
                isUnParked = true;
                break;
            }
        }
        return isUnParked;
    }


    /**
     * Purpose : This method is created to add parking lot owner observer
     *
     * @param observer : takes the parking lot owner as parameter
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : This method is created to get back the position of parked car
     *
     * @param vehicle : takes vehicle as parameter to get back the particular vehicle location in parking lot
     * @return the position of the vehicle if the vehicle is parked
     * @throws ParkingLotSystemException : when no vehicle is found
     */
    public int getVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle))
            for (Vehicle slot : parkingLot1) {
                if (slot.equals(vehicle))
                    return parkingLot1.indexOf(slot);
            }
        for (Vehicle slot : parkingLot2) {
            if (slot.equals(vehicle))
                return parkingLot2.indexOf(slot);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.equals(vehicle))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }


    /**
     * Purpose : This method is created for getting back the position of vehicle by providing vehicle color
     *
     * @param vehicleColor : takes vehicle color for checking the equality of color entity present in Vehicle class
     * @return the index position in the parking lot
     * @throws ParkingLotSystemException : when no such vehicle color is found
     */
    public int getVehiclePositionByColor(String vehicleColor) throws ParkingLotSystemException {
        for (Vehicle slot : parkingLot1) {
            if (slot.getVehicleColor().equals(vehicleColor))
                return parkingLot1.indexOf(slot);
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.getVehicleColor().equals(vehicleColor))
                return parkingLot2.indexOf(slot);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.getVehicleColor().equals(vehicleColor))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

    /**
     * Purpose : This method is created to know the vehicle plate number of blue color Toyota vehicle
     *
     * @param vehicle : takes vehicle as parameter for checking the blue color Toyota vehicle's plate number
     * @return the vehicle number of that particular vehicle
     * @throws ParkingLotSystemException : when no such blue color Toyota is found
     */
    public String getBlueColorToyotaVehicleNumber(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("TOYOTA"))
            for (Vehicle vehicleNumberPlate : parkingLot1) {
                if (vehicleNumberPlate.equals(vehicle))
                    return vehicleNumberPlate.getVehicleNumber();
            }
        for (Vehicle vehicleNumberPlate : parkingLot2) {
            if (vehicleNumberPlate.equals(vehicle))
                return vehicleNumberPlate.getVehicleNumber();
        }
        for (Vehicle vehicleNumberPlate : parkingLotForHandicapped) {
            if (vehicleNumberPlate.equals(vehicle))
                return vehicleNumberPlate.getVehicleNumber();
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Parked");
    }

    /**
     * Purpose : This method is created for getting back the position of vehicle by providing vehicle name
     *
     * @param vehicleName : takes vehicle name for checking the equality of name entity present in Vehicle class
     * @return the index position in the parking lot
     * @throws ParkingLotSystemException : when no such vehicle name is found
     */
    public int getVehiclePositionByName(String vehicleName) throws ParkingLotSystemException {
        for (Vehicle slot : parkingLot1) {
            if (slot.getName().equals(vehicleName))
                return parkingLot1.indexOf(slot);
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.getName().equals(vehicleName))
                return parkingLot2.indexOf(slot);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.getName().equals(vehicleName))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

    /**
     * Purpose : This method is created to get back the position of the vehicle which is parked for last 30 minutes
     *
     * @param time : takes local time as parameter
     * @return the index position of the vehicle in parking lot
     * @throws ParkingLotSystemException : when no vehicle is found parked for last 30 minutes
     */
    public int getLast30MinuteParkedVehicles(LocalTime time) throws ParkingLotSystemException {
        for (Vehicle slot : parkingLot1) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLot1.indexOf(slot);
        }
        for (Vehicle slot : parkingLot2) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLot2.indexOf(slot);
        }
        for (Vehicle slot : parkingLotForHandicapped) {
            if (slot.getParkingTime().minusMinutes(30).equals(time))
                return parkingLotForHandicapped.indexOf(slot);
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No such vehicle found");
    }

    /**
     * Purpose : This method is created to check the validity of vehicle plate numbers
     *
     * @param vehicleNumber : takes vehicle number for checking across the regular expression defined in the method
     * @return : the matching value of that vehicle number if true or false
     */
    public boolean validateVehicleNumberPlate(String vehicleNumber) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][A-Z]{1,2}[0-9]{4,}$");
        Matcher matcher = pattern.matcher(vehicleNumber);
        boolean value = matcher.matches();
        if (vehicleNumber.isEmpty())
            return false;
        return value;
    }
}
