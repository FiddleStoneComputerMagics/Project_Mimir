
import Model.Departament;
import org.sqlite.JDBC;

import java.sql.*;

public class DepartamentTable {

    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static DepartamentTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS departaments ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " facultyID     INTEGER NOT NULL,"
                + " nameDepartament     varchar NOT NULL,"
                + " listWorkers     varchar NOT NULL"
                + ")");
    }
    public static synchronized DepartamentTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new DepartamentTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private DepartamentTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }

    /*public List<Model.Faculty> getAllFacultyes() {

        // Statement ������������ ��� ����, ����� ��������� sql-������
        try (Statement statement = this.connection.createStatement()) {
            // � ������ ������ ����� ��������� ���� ��������, ���������� �� ��
            List<Model.Faculty> faculties = new ArrayList<Model.Faculty>();
            // � resultSet ����� ��������� ��������� ������ �������,
            // ������� ����������� �������� statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, name, dean FROM faculties");
            // ���������� �� ������ resultSet � ������� ������ � products
            while (resultSet.next()) {
                faculties.add(new Model.Faculty(
                        resultSet.getString("name"),
                        resultSet.getString("dean")));
            }
            // ���������� ��� ������
            return faculties;

        } catch (SQLException e) {
            e.printStackTrace();
            // ���� ��������� ������ - ���������� ������ ���������
            return Collections.emptyList();
        }
    }*/

    // ���������� �������� � ��
    public void addDepartament(Departament departament) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Departaments(`facultyID`, `nameDepartament`,`listWorkers`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, departament.getFacultyID());
            statement.setObject(2, departament.getNameDepartament());
            statement.setObject(3, departament.getListWorkers());
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
        statement.executeUpdate("DROP TABLE IF EXISTS departaments");
    }

    public void showAllDepartaments() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%15s|%20s|%15s|%n", "id", "facultyID", "nameDepartament","listWorkers" );
        System.out.println("|----|---------------|--------------------|---------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM departaments");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            Integer facultyID = rs.getInt("facultyID"); // � ����� �� �����
            String nameDepartament = rs.getString("nameDepartament");
            String listWorkers = rs.getString("listWorkers");
            //System.out.println(listWorkers);
            System.out.format("|%4d|%15s|%20s|%15s|%n", id, facultyID, nameDepartament,listWorkers);
        }
    }
    /*// �������� �������� �� id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // ��������� ������
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}