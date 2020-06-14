package Model;

public class RoomInfo {

    private Integer id;

    private Float length;

    private Float width;

    private Float height;

    private Float volume;

    private Integer floor;

    public RoomInfo(Float length,Float width,Float height,Float volume,Integer floor){
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = volume;
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeigth(Float height) {
        this.height = height;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
