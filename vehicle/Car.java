package vehicle;

public class Car{

    private final String licensePlate; //get
    private final vehicle.Size spotOccupation; //get
    private int preferredFloor; //get,set
    private String ticketId; //get,set
    public Car(String licensePlate, vehicle.Size spotOccupation, int preferredFloor ){
        this.licensePlate = licensePlate;
        this.spotOccupation = spotOccupation;
        this.preferredFloor = preferredFloor;
        this.ticketId="";
    }

    //getters
    public String getLicensePlate(){
        return licensePlate;
    }

    public vehicle.Size getSpotOccupation(){
        return spotOccupation;
    }

    public int getPreferredFloor(){
        return preferredFloor;
    }

    public String getTicketId(){
        return ticketId;
    }

    //setters

    public void setPreferredFloor(int preferredFloor){
        this.preferredFloor = preferredFloor;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }
}