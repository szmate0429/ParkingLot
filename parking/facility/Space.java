package parking.facility;



public class Space{

    private final int floorNumber; //get

    private final int spaceNumber; //get

    private vehicle.Car occupyingCar;

    public Space(int floorNumber, int spaceNumber){
        this.floorNumber = floorNumber;
        this.spaceNumber = spaceNumber;
        occupyingCar = null;
    }

    public boolean isTaken(){

        return occupyingCar != null;
    }

    public void addOccupyingCar(vehicle.Car c){
        if (!isTaken()){
            occupyingCar=c;
        }
    }

    public void removeOccupyingCar(){
        occupyingCar=null;
    }

    public String getCarLicensePlate(){
        if(isTaken()){
            
            return occupyingCar.getLicensePlate();
        }else return "";
    }

    public vehicle.Size getOccupyingCarSize(){
        if(isTaken()){
            return occupyingCar.getSpotOccupation();
        }else return null;
    }

    public int getFloorNumber(){
        return floorNumber;
    }
    public int getSpaceNumber(){
        return spaceNumber;
    } 
}