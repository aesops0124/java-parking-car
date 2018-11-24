package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GeneralFacts {
    @Test // s1 ac1
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test  //s1 ac2
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test //s1 ac3
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket(new ParkingLot());

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test //s1 ac3
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(new ParkingLot()));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        ParkingTicket wrongTicket = new ParkingTicket(parkingLot);

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

}
