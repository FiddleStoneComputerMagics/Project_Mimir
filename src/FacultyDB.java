
import Model.Faculty;
import org.sqlite.JDBC;

import java.sql.*;

public class FacultyDB {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:faculties.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса FacultyDB
    private static FacultyDB instance = null;

    public static void createDB() throws SQLException {
           Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        // Создаём таблицы
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS faculties ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name       TEXT NOT NULL,"
                + " dean TEXT NOT NULL"
                + ")");
    }
    public static synchronized FacultyDB getInstance() throws SQLException {
        createDB();
        if (instance == null)
            instance = new FacultyDB();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private FacultyDB() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    /*public List<Model.Faculty> getAllFacultyes() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Model.Faculty> faculties = new ArrayList<Model.Faculty>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT id, name, dean FROM faculties");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                faculties.add(new Model.Faculty(
                        resultSet.getString("name"),
                        resultSet.getString("dean")));
            }
            // Возвращаем наш список
            return faculties;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }*/

    // Добавление продукта в БД
    public void addFaculty(Faculty faculty) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Faculties(`name`, `dean`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, faculty.getName());
            statement.setObject(2, faculty.getDean());
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
        statement.executeUpdate("DROP TABLE IF EXISTS faculties");
    }

    public void showAllUsers() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + "faculties.db");

        System.out.format("|%4s|%15s|%10s|%n", "id", "name", "dean");
        System.out.println("|----|---------------|----------|");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT * FROM faculties");
        while (rs.next()) {
            int id = rs.getInt(1); // можно обращаться по номеру столбца
            String name = rs.getString("name"); // а можно по имени
            String dean = rs.getString("dean");
            System.out.format("|%4d|%15s|%10s|%n", id, name, dean);
        }
    }
    /*// Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}