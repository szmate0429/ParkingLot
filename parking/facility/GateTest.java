package parking.facility;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class GateTest{

    @Test
    public void testFindAnyAvailableSpaceForCar(){
        parking.ParkingLot parkingLot = new parking.ParkingLot(5,5);
        vehicle.Car carSmall = new vehicle.Car("ABC",vehicle.Size.SMALL,3);
        vehicle.Car carLarge = new vehicle.Car("ABC",vehicle.Size.LARGE,7);
        parking.facility.Gate gate = new parking.facility.Gate(parkingLot);
        assertEquals(parkingLot.getFloorPlan()[0][0],gate.findAnyAvailableSpaceForCar(carSmall));
        assertEquals(parkingLot.getFloorPlan()[0][1],gate.findAnyAvailableSpaceForCar(carLarge));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        "ABCD", SMALL, 3
        "JKLM", LARGE, 4
        "LMNO", SMALL, 2
    """)
    @DisableIfHasBadStructure
    public void testFindPreferredAvailableSpaceForCar(String plate,vehicle.Size size, int preferredFloor) {
        parking.ParkingLot parkingLot = new parking.ParkingLot(5,8);
        parking.facility.Gate gate = new parking.facility.Gate(parkingLot);
        vehicle.Car car = new vehicle.Car(plate,size,preferredFloor);
        if(size == vehicle.Size.SMALL){
            assertEquals(parkingLot.getFloorPlan()[preferredFloor][0],gate.findPreferredAvailableSpaceForCar(car));}
        else{
            assertEquals(parkingLot.getFloorPlan()[preferredFloor][1],gate.findPreferredAvailableSpaceForCar(car));
        }
    }


    @ParameterizedTest
    @CsvSource(textBlock = """
        "ABCD", SMALL, 3
        "JKLM", LARGE, 4
        "LMNO", SMALL, 1
    """)
    @DisableIfHasBadStructure
    public void testRegisterCar(String plate,vehicle.Size size, int preferredFloor) {
        parking.ParkingLot parkingLot = new parking.ParkingLot(5,8);
        parking.facility.Gate gate = new parking.facility.Gate(parkingLot);
        vehicle.Car car = new vehicle.Car(plate,size,preferredFloor);

        gate.registerCar(car);
        if(size == vehicle.Size.SMALL){
            assertEquals("F"+preferredFloor+"S0",car.getTicketId());
        }else{
            assertEquals("F"+preferredFloor+"S1",car.getTicketId());
        }
        

    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        "ABCD", SMALL, 3
        "JKLM", LARGE, 4
        "LMNO", SMALL, 1
    """)
    @DisableIfHasBadStructure
    public void testDeRegisterCar(String plate,vehicle.Size size, int preferredFloor) {
        parking.ParkingLot parkingLot = new parking.ParkingLot(5,8);
        parking.facility.Gate gate = new parking.facility.Gate(parkingLot);
        vehicle.Car car = new vehicle.Car(plate,size,preferredFloor);

        gate.registerCar(car);
        // parking.facility.Space parkingSpace = gate.findTakenSpaceByCar(car);
        gate.deRegisterCar(car.getTicketId());

        assertEquals("",car.getTicketId());
        // assertTrue(!parkingSpace.isTaken());
    }





}


