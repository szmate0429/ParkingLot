package parking;

public class ParkingLot{

    private final parking.facility.Space[][] floorPlan; //get

    public ParkingLot(int floorNumber,int spaceNumber) throws IllegalArgumentException{
        if(floorNumber < 1 || spaceNumber < 1){throw new IllegalArgumentException("Illegal parameters given!");}
        floorPlan = new parking.facility.Space[floorNumber][spaceNumber];
        for(int i=0; i< floorNumber; i++){
            for(int j=0; j < spaceNumber; j++){
                floorPlan[i][j]=new parking.facility.Space(i,j);
            }
        }
    }

    public parking.facility.Space[][] getFloorPlan(){
        return floorPlan;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < floorPlan.length;i++){
            for(int j = 0; j < floorPlan[0].length;j++){
                switch(floorPlan[i][j].getOccupyingCarSize()){
                    case vehicle.Size.SMALL :
                        result.append("S");
                        break;
                    case vehicle.Size.LARGE :
                        result.append("L");
                        break;
                    case null:
                        result.append("X");
                        break;
                }
                if(j < floorPlan[0].length-1){result.append(" ");}
            }
            if(i < floorPlan.length-1){result.append("\n");}
        }
        return result.toString();
    }
}