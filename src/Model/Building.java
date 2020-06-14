package Model;

public class Building {

    private Integer id;

    private Integer departamentID;

    private String nameBuilding;

    private String adress;

    private String watchmen;

    public Building(Integer departamentID,String nameBuilding,String adress,String watchmen){
        this.departamentID = departamentID;
        this.nameBuilding = nameBuilding;
        this.adress = adress;
        this.watchmen = watchmen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartamentID() {
        return departamentID;
    }

    public void setDepartamentID(Integer departamentID) {
        this.departamentID = departamentID;
    }

    public String getNameBuilding() {
        return nameBuilding;
    }

    public void setNameBuilding(String nameBuilding) {
        this.nameBuilding = nameBuilding;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWatchmen() {
        return watchmen;
    }

    public void setWatchmen(String watchmen) {
        this.watchmen = watchmen;
    }
}
