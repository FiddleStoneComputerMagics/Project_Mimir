
import Model.Departament;
import Model.Worker;
import org.sqlite.JDBC;

import java.sql.*;

public class WorkerTable {

    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static WorkerTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS workers ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " firstName     varchar NOT NULL,"
                + " lastName     varchar NOT NULL,"
                + " post     varchar NOT NULL"
                + ")");
    }
    public static synchronized WorkerTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new WorkerTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private WorkerTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }



    // ���������� �������� � ��
    public void addWorker(Worker worker) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Workers(`firstName`, `lastName`,`post`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, worker.getFirstName());
            statement.setObject(2, worker.getLastName());
            statement.setObject(3, worker.getPost());
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS workers");
    }

    public void showAllWorkers() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%15s|%20s|%15s|%n", "id", "firstName", "lastName","post" );
        System.out.println("|----|---------------|--------------------|---------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM workers");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            String firstName = rs.getString("lastName");
            String lastName = rs.getString("lastName");
            String post = rs.getString("post");
            System.out.format("|%4d|%15s|%20s|%15s|%n", id, firstName, lastName,post);
        }
    }
}
