package database;

import velo.AbstractVelo;
import velo.ConcreteVelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    // Метод для проверки существования записи
    public boolean bikeExists(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM velo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Количество записей с id = " + id + ": " + count);
                    return count > 0;
                }
            }
        }
        return false;
    }


    // Метод для чтения данных из файла и вставки их в базу данных
    public void insertBike(int id, java.sql.Date date, String type, String model, double price, double maxSpeed) throws SQLException {
        String query = "INSERT INTO velo (id, date, type, model, price, max_speed) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setDate(2, date);
            stmt.setString(3, type);
            stmt.setString(4, model);
            stmt.setDouble(5, price);
            stmt.setDouble(6, maxSpeed);
            stmt.executeUpdate();
            System.out.println("Запись с id = " + id + " добавлена.");
        } catch (SQLException e) {
            throw new SQLException("Ошибка вставки данных в базу данных", e);
        }
    }
    // Метод для чтения объектов из базы данных
    public List<AbstractVelo> readBikesFromDatabase() throws SQLException {
        List<AbstractVelo> bikes = new ArrayList<>();
        String query = "SELECT * FROM velo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String type = rs.getString("type");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                double max_speed = rs.getDouble("max_speed");
                bikes.add(new ConcreteVelo(id, date, type, model, price, max_speed));
            }
        }
            return bikes;
        }
}

