import Model.Audit;
import Model.Worker;
import org.sqlite.JDBC;

import java.sql.*;

public class AuditTable {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса FacultyDB
    private static AuditTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // Создаём таблицы
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

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private AuditTable() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // Добавление продукта в БД
    public void addAudit(Audit audit) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Audits(`DepartamentID`, `NameAudit`,`DataAudit`,`MarkAudit`,`Recommendations`) " +
                        "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, audit.getDepartamentID());
            statement.setObject(2, audit.getNameAudit());
            statement.setObject(3, audit.getDataAudit());
            statement.setObject(4, audit.getMarkAudit());
            statement.setObject(5, audit.getRecommendations());
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // Создаём таблицы
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
            int id = rs.getInt(1); // можно обращаться по номеру столбца
            Integer departamentID = rs.getInt("departamentID");
            String nameAudit = rs.getString("nameAudit");
            String dataAudit = rs.getString("dataAudit");
            String markAudit = rs.getString("markAudit");
            String recommendations = rs.getString("recommendations");
            System.out.format("|%4s|%13s|%10s|%17s|%15s|%32s|%n", id, departamentID, nameAudit,dataAudit,markAudit,recommendations);
        }
    }
}
