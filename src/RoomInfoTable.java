import Model.Building;
import Model.RoomInfo;
import org.sqlite.JDBC;

import java.sql.*;

public class RoomInfoTable {
    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static RoomInfoTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS roomInfo ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " length     FLOAT NOT NULL,"
                + " width    FLOAT NOT NULL,"
                + " height     FLOAT NOT NULL,"
                + " volume     FLOAT NOT NULL,"
                + " floor     INTEGER NOT NULL"
                + ")");
    }
    public static synchronized RoomInfoTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new RoomInfoTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private RoomInfoTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // ���������� �������� � ��
    public void addRoomInfo(RoomInfo roomInfo) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO RoomInfo(`length`, `width`,`height`,`volume`,`floor`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, roomInfo.getLength());
            statement.setObject(2, roomInfo.getWidth());
            statement.setObject(3, roomInfo.getHeight());
            statement.setObject(4, roomInfo.getVolume());
            statement.setObject(5, roomInfo.getFloor());
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS roomInfo");
    }

    public void showAllRoomInfo() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%10s|%10s|%17s|%15s|%15s|%n", "id", "length", "width","height","volume","floor" );
        System.out.println("|----|----------|----------|-----------------|---------------|---------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM roomInfo");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            Float length = rs.getFloat("length");
            Float width = rs.getFloat("width");
            Float height = rs.getFloat("height");
            Float volume = rs.getFloat("volume");
            Integer floor = rs.getInt("floor");

            System.out.format("|%4d|%10f|%10f|%17f|%15f|%15d|%n", id, length, width,height,volume,floor);
        }
    }
}
