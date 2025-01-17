package github.LeandroBryto.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/lavanderia";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Atualize com a senha do seu banco de dados

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
