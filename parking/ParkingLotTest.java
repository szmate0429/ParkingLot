package parking;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class ParkingLotTest{


    @Test
    public void testConstructorWithInvalidValues() throws IllegalArgumentException{
        try{
            parking.ParkingLot parkingLot = new parking.ParkingLot(0,0);
            fail("Exception was not thrown!");
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void testTextualRepresentation(){
        parking.ParkingLot parkingLot = new parking.ParkingLot(3,3);
        assertEquals("X X X\nX X X\nX X X",parkingLot.toString());
        parking.facility.Gate gate = new parking.facility.Gate(parkingLot);
        vehicle.Car car1 = new vehicle.Car("ASD",vehicle.Size.SMALL,3);
        gate.registerCar(car1);
        assertEquals("X X X\nX X X\nS X X",parkingLot.toString());
        vehicle.Car car2 = new vehicle.Car("FGH",vehicle.Size.LARGE,3);
        gate.registerCar(car2);
        assertEquals("X X X\nX X X\nS L L",parkingLot.toString());
        vehicle.Car car3 = new vehicle.Car("JKL",vehicle.Size.LARGE,3);
        gate.registerCar(car3);
        assertEquals("X X X\nL L X\nS L L",parkingLot.toString());
        gate.deRegisterCar(car1.getTicketId());
        assertEquals("X X X\nL L X\nX L L",parkingLot.toString());
        vehicle.Car car4 = new vehicle.Car("QWE",vehicle.Size.LARGE,3);
        gate.registerCar(car4);
        assertEquals("L L X\nL L X\nX L L",parkingLot.toString());
        gate.deRegisterCar(car2.getTicketId());
        gate.deRegisterCar(car3.getTicketId());
        gate.deRegisterCar(car4.getTicketId());
        assertEquals("X X X\nX X X\nX X X",parkingLot.toString());
        
    }
}