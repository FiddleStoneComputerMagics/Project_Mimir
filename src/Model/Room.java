package Model;

public class Room {

    private Integer id;

    private Integer buildingID;

    private Integer number;

    private Integer floor;

    private String type;

    public Room(Integer buildingID,Integer number,Integer floor,String type){
        this.buildingID = buildingID;
        this.number = number;
        this.floor = floor;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(Integer buildingID) {
        this.buildingID = buildingID;
    }

    public Integer getNumder() {
        return number;
    }

    public void setNumder(Integer numder) {
        this.number = numder;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
