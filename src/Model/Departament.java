package Model;

import java.lang.reflect.Array;
import java.util.List;

public class Departament {

    private Integer id;

    private Integer facultyID;

    private String nameDepartament;

    public String getListWorkers() {
        return listWorkers;
    }

    public void setListWorkers(String listWorkers) {
        this.listWorkers = listWorkers;
    }

    private String listWorkers;

    public Departament(Integer facultyID, String nameDepartament, String listWorkers){
        this.facultyID = facultyID;
        this.nameDepartament = nameDepartament;
        this.listWorkers = listWorkers;
    }

    public Integer getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Integer facultyID) {
        this.facultyID = facultyID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDepartament() {
        return nameDepartament;
    }

    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
    }
    @Override
    public String toString() {
        return String.format("ID: %s | facultyID: %s | nameDepartament: %s | listWorkers: %s",
                this.id, this.facultyID, this.nameDepartament, this.listWorkers);
    }

}
