package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // Запускаем приложение
        try {
            String jdbcURL = "jdbc:h2:./hexlet";
            String username = "sa";
            String password = "";

            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to H2 in-memory database.");
            Statement statement = connection.createStatement();
            statement.execute("DROP ALL OBJECTS");
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(App.class, args);

    }
}
