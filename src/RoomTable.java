import Model.Building;
import Model.Room;
import org.sqlite.JDBC;

import java.sql.*;

public class RoomTable {
    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static RoomTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS rooms ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " buildingID     INTEGER NOT NULL,"
                + " number     INTEGER NOT NULL,"
                + " floor     INTEGER NOT NULL,"
                + " type     varchar NOT NULL"
                + ")");
    }
    public static synchronized RoomTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new RoomTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private RoomTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // ���������� �������� � ��
    public void addRoom(Room room) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO rooms(`buildingID`,`number`,`floor`,`type`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, room.getBuildingID());
            statement.setObject(2, room.getNumder());
            statement.setObject(3, room.getFloor());
            statement.setObject(4, room.getType());
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS rooms");
    }

    public void showAllRooms() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%13s|%10s|%17s|%15s|%n", "id", "buildingID","number","floor","type" );
        System.out.println("|----|-------------|----------|-----------------|---------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM rooms");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            Integer departamentID = rs.getInt("buildingID");
            String number = rs.getString("number");
            String floor = rs.getString("floor");
            String type = rs.getString("type");
            System.out.format("|%4s|%13s|%10s|%17s|%15s|%n", id, departamentID, number,floor,type);
        }
    }
}
