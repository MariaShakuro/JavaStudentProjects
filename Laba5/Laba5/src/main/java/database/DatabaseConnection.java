package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        String url = "jdbc:h2:~/testdb";
        // Использование H2 базы данных
        String user = "sa";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }
    public static DatabaseConnection getInstance()throws SQLException{
        if(instance==null)instance=new DatabaseConnection();
        else if(instance.getConnection().isClosed()){
            instance=new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}
