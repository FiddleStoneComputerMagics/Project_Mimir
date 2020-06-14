
import Model.Departament;
import Model.Worker;
import org.sqlite.JDBC;

import java.sql.*;

public class WorkerTable {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса FacultyDB
    private static WorkerTable instance = null;

    public static void createDB() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // Создаём таблицы
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

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private WorkerTable() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }



    // Добавление продукта в БД
    public void addWorker(Worker worker) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Workers(`firstName`, `lastName`,`post`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, worker.getFirstName());
            statement.setObject(2, worker.getLastName());
            statement.setObject(3, worker.getPost());
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
            int id = rs.getInt(1); // можно обращаться по номеру столбца
            String firstName = rs.getString("lastName");
            String lastName = rs.getString("lastName");
            String post = rs.getString("post");
            System.out.format("|%4d|%15s|%20s|%15s|%n", id, firstName, lastName,post);
        }
    }
}
