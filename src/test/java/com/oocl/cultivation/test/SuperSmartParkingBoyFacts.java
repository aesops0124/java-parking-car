package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyFacts {

    //given 2 parking lots in parking center that capacity are 4/5 (80%)  and 2/2 (100%) and assigned to smart parking boy
    //when parkingBoy select parking lot to park car
    //then he will choose capacity with 2/2 (100%)
    @Test
    void should_select_second_parking_lot() {

        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot1.park(new ParkingTicket(parkingLot1), new Car());

        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotCenter);

        ParkingLot selectedParkingLot = superSmartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLot2);
    }

    //given 2 parking lots in parking center that capacity are 3/3 (100%)  and 4/6 (83%) and assigned to smart parking boy
    //when parkingBoy select parking lot to park car
    //then he will choose capacity with 3/3 (100%)
    @Test
    void should_select_first_parking_lot() {

        ParkingLot parkingLot1 = new ParkingLot(3);
        ParkingLot parkingLot2 = new ParkingLot(6);
        parkingLot2.park(new ParkingTicket(parkingLot2), new Car());
        parkingLot2.park(new ParkingTicket(parkingLot2), new Car());

        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotCenter);

        ParkingLot selectedParkingLot = superSmartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLot1);
    }

    //given 2 parking lots in parking center with both full and assigned to super smart parking boy
    //when SuperSmartParkingBoy select parking lot to park car
    //then car will not parked
    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position_on_both_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotCenter);

        superSmartParkingBoy.park(new Car());

        assertNull(superSmartParkingBoy.park(new Car()));
    }

    //given full parking lot
    //when parkingBoy fetch one car
    //then he can park a new car
    @Test
    void should_fetch_car_and_remove_from_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy ParkingBoy = new ParkingBoy(parkingLotCenter);
        Car oldCar = new Car();
        ParkingTicket oldTicket = ParkingBoy.park(oldCar);
        ParkingBoy.fetch(oldTicket);
        assertNotNull(ParkingBoy.park(new Car()));

    }
}
