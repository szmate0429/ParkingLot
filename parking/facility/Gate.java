package parking.facility;

import java.util.ArrayList;

public class Gate{

    private final ArrayList<vehicle.Car> cars;
    private final parking.ParkingLot parkingLot;

    public Gate(parking.ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        cars = new ArrayList<vehicle.Car>();
    }

    private parking.facility.Space findTakenSpaceByCar(vehicle.Car c){
        for(parking.facility.Space[] rows : parkingLot.getFloorPlan()){
            for(parking.facility.Space space : rows){
            if (space.getCarLicensePlate() == c.getLicensePlate()){
                return space;    
                }
            }
        }
        return null;
    }

    private parking.facility.Space findAvailableSpaceOnFloor(int floor, vehicle.Car c){
        if(parkingLot.getFloorPlan().length <= floor){return null;}
        if(c.getSpotOccupation() == vehicle.Size.SMALL){
                for(parking.facility.Space space : parkingLot.getFloorPlan()[floor]){
                if(!space.isTaken()){ return space;}}
        }else{
            parking.facility.Space[][] temp = parkingLot.getFloorPlan();
            int cols = temp[0].length;
            for(int j=1; j< cols;j++){
                if(!temp[floor][j-1].isTaken() && !temp[floor][j].isTaken()){
                    return temp[floor][j];
                }
                }
        }
        return null;
    }


    public parking.facility.Space  findAnyAvailableSpaceForCar(vehicle.Car c){
        for(int i=0; i< parkingLot.getFloorPlan().length; i++){
            if(findAvailableSpaceOnFloor(i,c) != null){ return findAvailableSpaceOnFloor(i,c);}
        }
        return null;
    }

    public parking.facility.Space findPreferredAvailableSpaceForCar(vehicle.Car c){
        int pref = c.getPreferredFloor();
        if(findAvailableSpaceOnFloor(pref,c) != null){ return findAvailableSpaceOnFloor(pref,c);}
        int i = 1;
        int floors = parkingLot.getFloorPlan().length;
        while( pref+i < floors || pref-i >= 0){
            if(pref+i < floors && findAvailableSpaceOnFloor(pref+i,c) != null){ return findAvailableSpaceOnFloor(pref+i,c);}
            if(pref-i >= 0 && findAvailableSpaceOnFloor(pref-i,c) != null){ return findAvailableSpaceOnFloor(pref-i,c);}
            i++;
        }
        return null;
    }

    public boolean registerCar(vehicle.Car c){
        parking.facility.Space space = findPreferredAvailableSpaceForCar(c);
        if(space != null && c.getSpotOccupation()==vehicle.Size.SMALL){
            space.addOccupyingCar(c);
            c.setTicketId("F"+space.getFloorNumber()+"S"+space.getSpaceNumber());
            cars.add(c);
            return true;}
        else if(space != null && c.getSpotOccupation()==vehicle.Size.LARGE){
            space.addOccupyingCar(c);
            parkingLot.getFloorPlan()[space.getFloorNumber()][space.getSpaceNumber()-1].addOccupyingCar(c);
            c.setTicketId("F"+space.getFloorNumber()+"S"+space.getSpaceNumber());
            cars.add(c);
            return true;}    
        return false;
    }



    public boolean registerCarAnySpace(vehicle.Car c){
        parking.facility.Space space = findAnyAvailableSpaceForCar(c);
        if(space != null && c.getSpotOccupation()==vehicle.Size.SMALL){
            space.addOccupyingCar(c);
            c.setTicketId("F"+space.getFloorNumber()+"S"+space.getSpaceNumber());
            cars.add(c);
            return true;}
        else if(space != null && c.getSpotOccupation()==vehicle.Size.LARGE){
            space.addOccupyingCar(c);
            parkingLot.getFloorPlan()[space.getFloorNumber()][space.getSpaceNumber()-1].addOccupyingCar(c);
            c.setTicketId("F"+space.getFloorNumber()+"S"+space.getSpaceNumber());
            cars.add(c);
            return true;}    
        return false;
    }

    public void registerCars(vehicle.Car... cars){
        for(vehicle.Car car : cars){
            boolean found = registerCarAnySpace(car);
            if(!found){System.out.println("Error: no spaces for this car!");}
        }
    }

    public void deRegisterCar(String ticketId){
        vehicle.Car car = null;
        for(vehicle.Car tempcar : cars){
            if(tempcar.getTicketId() == ticketId){
                car = tempcar;
            }
        }
        if(car != null){
        parking.facility.Space spaceCar = findTakenSpaceByCar(car);
        if(car.getSpotOccupation()==vehicle.Size.LARGE){
            parkingLot.getFloorPlan()[spaceCar.getFloorNumber()][spaceCar.getSpaceNumber()+1].removeOccupyingCar();
            }
        spaceCar.removeOccupyingCar();
        car.setTicketId("");
        cars.remove(car);}
    }
}   
