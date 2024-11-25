package main;

import database.DatabaseConnection;
import velo.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static main.ZipFile.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Подключение к базе данных
            DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
            Connection connection = databaseConnection.getConnection();

            // Ввод формата данных
            System.out.println("Выберите формат ввода данных:\n1 - JSON\n2 - XML\n3 - TXT");
            Scanner in = new Scanner(System.in);
            int inputChoice = in.nextInt(); in.nextLine();
            String inputFormat = "";
            switch (inputChoice) {
                case 1:
                    inputFormat = "JSON";
                    break;
                case 2:
                    inputFormat = "XML";
                    break;
                case 3:
                    inputFormat = "TXT";
                    break;
                default:
                    System.out.println("Неверный выбор");
                    return;
            }

            // Чтение данных из файла в зависимости от формата
            List<AbstractVelo> bikes;
            if (inputFormat.equals("JSON")) {
                bikes = JSONReader.readBikesfromJSON("input.json");
            } else if (inputFormat.equals("XML")) {
                bikes = XMLReader.readBikes("input.xml");
            } else {
                bikes = ReadFromFile.readFile("input.txt");
            }

            // Запись объектов в базу данных
            
            for (AbstractVelo bike : bikes) {
                insertBikeIntoDatabase(connection, bike);
            }

            // Чтение объектов из базы данных
            List<AbstractVelo> bikesFromDB = readBikesFromDatabase(connection);

            // Вывод объектов
            bikesFromDB.forEach(System.out::println);

            // Итераторный вывод List
            ListStorage<AbstractVelo> listStorage = new ListStorage<>();
            listStorage.addAll(bikesFromDB);
            Iterator<AbstractVelo> listIterator = listStorage.getAll().iterator();
            StringBuilder listOutput = new StringBuilder();
            listOutput.append("List items\n");
            while (listIterator.hasNext()) {
                AbstractVelo listValue = listIterator.next();
                listOutput.append(listValue).append("\n");
            }
            System.out.println(listOutput.toString());

            // Выбор формата вывода данных
            System.out.println("Выберите формат вывода данных:\n1 - JSON\n2 - XML\n3 - TXT");
            int outputChoice = in.nextInt(); in.nextLine();
            String format = "";
            String outputFilePath = "";
            switch (outputChoice) {
                case 1:
                    format = "JSON";
                    outputFilePath = "output.json";
                    break;
                case 2:
                    format = "XML";
                    outputFilePath = "output.xml";
                    break;
                case 3:
                    format = "TXT";
                    outputFilePath = "output.txt";
                    break;
                default:
                    System.out.println("Неверный выбор");
                    return;
            }

            // Запись данных в файл в зависимости от выбранного формата
            if (format.equals("JSON")) {
                JSONWriter.writeBikes(outputFilePath, bikesFromDB);
            } else if (format.equals("XML")) {
                XMLWriter.writeBikes(outputFilePath, bikesFromDB);
            } else {
                WriteToFile.writeToFile(outputFilePath, bikesFromDB);
            }

            // Выбор поля для сортировки
           System.out.println("Выберите поле для сортировки:\n1 - id\n2 - date\n3 - type\n4 - model\n5 - price\n6 - max_speed");
            int choice = in.nextInt(); in.nextLine();

            switch (choice) {
                case 1:
                    SortVelo.sortId(bikesFromDB);
                    break;
                case 2:
                    SortVelo.sortDate(bikesFromDB);
                    break;
                case 3:
                    SortVelo.sortType(bikesFromDB);
                    break;
                case 4:
                    SortVelo.sortModel(bikesFromDB);
                    break;
                case 5:
                    SortVelo.sortPrice(bikesFromDB);
                    break;
                case 6:
                    SortVelo.sortMax_speed(bikesFromDB);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
                    return;
            }

            // Выводим отсортированный список
            System.out.println("Отсортированный список:");
            for (AbstractVelo velo : bikesFromDB) {
                System.out.println(velo);
            }

            // Консольный ввод новых данных
            System.out.println("Введите данные для List и Map (id, date, type, model, price, max_speed):");
            while (true) {
                String input = in.nextLine();
                String[] parts = input.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0].trim());
                    Date date = DateUtils.parseDate(parts[1].trim());
                    String type = parts[2].trim();
                    String model = parts[3].trim();
                    double price = Double.parseDouble(parts[4].trim());
                    double maxSpeed = Double.parseDouble(parts[5].trim());

                    AbstractVelo newBike;
                    if ("Road".equals(type)) {
                        newBike = new RoadVelo(id, date, type, model, price, maxSpeed);
                    } else if ("Mountain".equals(type)) {
                        newBike = new MountainVelo(id, date, type, model, price, maxSpeed);
                    } else {
                        System.out.println("Invalid type, please try again.");
                        continue;
                    }

                    bikesFromDB.add(newBike);
                    insertBikeIntoDatabase(connection, newBike);

                    if (format.equals("JSON")) {
                        JSONWriter.writeBikes(outputFilePath, bikesFromDB);
                    } else if (format.equals("XML")) {
                        XMLWriter.writeBikes(outputFilePath, bikesFromDB);
                    } else {
                        WriteToFile.writeEntry(outputFilePath, newBike);
                    }
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void insertBikeIntoDatabase(Connection connection, AbstractVelo bike) throws SQLException {
        String query = "INSERT INTO velo (id, date, type, model, price, max_speed) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bike.getID());
            statement.setDate(2, new java.sql.Date(bike.getDATE().getTime()));
            statement.setString(3, bike.getTYPE());
            statement.setString(4, bike.getMODEL());
            statement.setDouble(5, bike.getPRICE());
            statement.setDouble(6, bike.getMAX_SPEED());
            statement.executeUpdate();
        }
    }

    private static List<AbstractVelo> readBikesFromDatabase(Connection connection) throws SQLException {
        List<AbstractVelo> bikes = new ArrayList<>();
        String query = "SELECT * FROM velo";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                String type = resultSet.getString("type");
                String model = resultSet.getString("model");
                double price = resultSet.getDouble("price");
                double maxSpeed = resultSet.getDouble("max_speed");

                AbstractVelo bike;
                if ("Road".equals(type)) {
                    bike = new RoadVelo(id, date, type, model, price, maxSpeed);
                } else if ("Mountain".equals(type)) {
                    bike = new MountainVelo(id, date, type, model, price, maxSpeed);
                } else {
                    throw new IllegalArgumentException("Unknown type: " + type);
                }
                bikes.add(bike);
            }
        }
        return bikes;
    }
}
