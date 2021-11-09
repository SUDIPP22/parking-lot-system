package com.bridgelabz;

import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    @Test
    void givenMessage_ShouldPrintWelcomeMessage() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.printWelcomeMessage();
    }
}
