import Model.Building;
import Model.Room;
import org.sqlite.JDBC;

import java.sql.*;

public class RoomTable {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса FacultyDB
    private static RoomTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // Создаём таблицы
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

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private RoomTable() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    // Добавление продукта в БД
    public void addRoom(Room room) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO rooms(`buildingID`,`number`,`floor`,`type`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, room.getBuildingID());
            statement.setObject(2, room.getNumder());
            statement.setObject(3, room.getFloor());
            statement.setObject(4, room.getType());
            // Выполняем запрос
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
            int id = rs.getInt(1); // можно обращаться по номеру столбца
            Integer departamentID = rs.getInt("buildingID");
            String number = rs.getString("number");
            String floor = rs.getString("floor");
            String type = rs.getString("type");
            System.out.format("|%4s|%13s|%10s|%17s|%15s|%n", id, departamentID, number,floor,type);
        }
    }
}
