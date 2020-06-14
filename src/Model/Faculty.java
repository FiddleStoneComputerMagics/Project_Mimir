package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Faculty {

    private Integer id;

    private String name;

    private String dean;

    public Faculty(String name, String dean){
        this.name = name;
        this.dean = dean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }
    @Override
    public String toString() {
        return String.format("ID: %s | Имя: %s | Декан: %s",
                this.id, this.name, this.dean);
    }
}
