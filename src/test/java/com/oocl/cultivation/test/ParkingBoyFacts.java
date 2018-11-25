package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingBoyFacts {

    //given 2 parking lots in parking center that both has space and assigned to parking boy
    //when parkingBoy select a parking lot
    //then he will choose first parking lot
    @Test
    void should_park_car_to_the_first_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertEquals(parkingLot1.getAvailableParkingPosition(),4);
        assertEquals(parkingLot2.getAvailableParkingPosition(),5);
        // assertSame(fetched, car);
    }

    //given 2 parking lots in parking center that the first one is full and the second one has space and assigned to parking boy
    //when parkingBoy select a parking lot
    //then he will choose second parking lot
    @Test
    void should_park_car_to_the_second_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertEquals(parkingLot1.getAvailableParkingPosition(),0);
        assertEquals(parkingLot2.getAvailableParkingPosition(),4);
    }

}
