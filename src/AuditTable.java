import Model.Audit;
import Model.Worker;
import org.sqlite.JDBC;

import java.sql.*;

public class AuditTable {

    // ���������, � ������� �������� ����� �����������
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // ���������� ������ ��������, ����� �� ������� ���������
    // ����������� ������ FacultyDB
    private static AuditTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // ������ �������
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS audits ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " departamentID     INTEGER NOT NULL,"
                + " nameAudit    varchar NOT NULL,"
                + " dataAudit     varchar NOT NULL,"
                + " markAudit     varchar NOT NULL,"
                + " recommendations     varchar NOT NULL"
                + ")");
    }
    public static synchronized AuditTable getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new AuditTable();
        return instance;
    }

    // ������, � ������� ����� ��������� ���������� � ��
    private Connection connection;

    private AuditTable() throws SQLException {
        // ������������ �������, � ������� ����� ��������
        // � ����� ������ Sqlite
        DriverManager.registerDriver(new JDBC());
        // ��������� ����������� � ���� ������
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // ���������� �������� � ��
    public void addAudit(Audit audit) {
        // �������� �������������� ���������, ����� �������� SQL-��������
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Audits(`DepartamentID`, `NameAudit`,`DataAudit`,`MarkAudit`,`Recommendations`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, audit.getDepartamentID());
            statement.setObject(2, audit.getNameAudit());
            statement.setObject(3, audit.getDataAudit());
            statement.setObject(4, audit.getMarkAudit());
            statement.setObject(5, audit.getRecommendations());
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
        statement.executeUpdate("DROP TABLE IF EXISTS audits");
    }

    public void showAllAudits() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%4s|%10s|%17s|%15s|%32s|%n", "id", "departamentID", "nameAudit","dataAudit","markAudit","recommendations" );
        System.out.println("|----|-------------|----------|-----------------|---------------|--------------------------------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM audits");
        while (rs.next()) {
            int id = rs.getInt(1); // ����� ���������� �� ������ �������
            Integer departamentID = rs.getInt("departamentID");
            String nameAudit = rs.getString("nameAudit");
            String dataAudit = rs.getString("dataAudit");
            String markAudit = rs.getString("markAudit");
            String recommendations = rs.getString("recommendations");
            System.out.format("|%4s|%13s|%10s|%17s|%15s|%32s|%n", id, departamentID, nameAudit,dataAudit,markAudit,recommendations);
        }
    }
}
