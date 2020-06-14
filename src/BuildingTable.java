import Model.Audit;
import Model.Building;
import org.sqlite.JDBC;

import java.sql.*;

public class BuildingTable {
    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static BuildingTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS buildings ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " departamentID     INTEGER NOT NULL,"
                + " nameBuilding    varchar NOT NULL,"
                + " adress     varchar NOT NULL,"
                + " watchmen     varchar NOT NULL,"
                + ")");
    }
    public static synchronized BuildingTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new BuildingTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private BuildingTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // ���������� �������� � ��
    public void addBuilding(Building building) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Buildings(`DepartamentID`, `nameBuilding`,`adress`,`watchmen`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, building.getDepartamentID());
            statement.setObject(2, building.getNameBuilding());
            statement.setObject(3, building.getAdress());
            statement.setObject(4, building.getWatchmen());
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS buildings");
    }

    public void showAllBuildings() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%4s|%10s|%17s|%15s|%n", "id", "departamentID", "nameBuilding","adress","watchmen" );
        System.out.println("|----|-------------|----------|-----------------|---------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM buildings");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            Integer departamentID = rs.getInt("departamentID");
            String nameBuilding = rs.getString("nameBuilding");
            String adress = rs.getString("adress");
            String watchmen = rs.getString("watchmen");
            System.out.format("|%4s|%13s|%10s|%17s|%15s|%n", id, departamentID, nameBuilding,adress,watchmen);
        }
    }
}
