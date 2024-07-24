package github.LeandroBryto.dao;

import github.LeandroBryto.model.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/lavanderia";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password"; // Substitua pela senha correta

    private static final String INSERT_CLIENTE_SQL = "INSERT INTO clientes (nome, cpf, cep, telefone) VALUES (?, ?, ?, ?);";
    private static final String SELECT_CLIENTE_BY_CPF = "SELECT id FROM clientes WHERE cpf = ?";

    public ClienteDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean existeCliente(String cpf) {
        boolean exists = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENTE_BY_CPF)) {
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void adicionarCliente(String nome, String cpf, String cep, String telefone) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTE_SQL)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, cep);
            preparedStatement.setString(4, telefone);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
