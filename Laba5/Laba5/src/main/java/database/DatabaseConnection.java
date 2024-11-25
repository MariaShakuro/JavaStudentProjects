package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        String url = "jdbc:h2:~/testdb"; // Использование H2 базы данных
        String user = "sa";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);

        // Инициализация базы данных (создание таблицы)
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS velo (id INT PRIMARY KEY, date DATE, type VARCHAR(255), model VARCHAR(255), price DOUBLE, max_speed DOUBLE)");
        } catch (SQLException e) {
            throw new SQLException("Ошибка инициализации базы данных", e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Метод для чтения данных из файла и вставки их в базу данных
    public void readDataFromFile(String filename) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    int id = Integer.parseInt(data[0].trim());
                    String date = data[1].trim();
                    String type = data[2].trim();
                    String model = data[3].trim();
                    double price = Double.parseDouble(data[4].trim());
                    double maxSpeed = Double.parseDouble(data[5].trim());

                    insertData(id, date, type, model, price, maxSpeed);
                }
            }
        }
    }

    // Метод для вставки данных в базу данных
    public void insertData(int id, String date, String type, String model, double price, double maxSpeed) throws SQLException {
        String query = String.format("INSERT INTO velo (id, date, type, model, price, max_speed) VALUES (%d, '%s', '%s', '%s', %.2f, %.2f)",
                id, date, type, model, price, maxSpeed);
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            throw new SQLException("Ошибка вставки данных в базу данных", e);
        }
    }
}

