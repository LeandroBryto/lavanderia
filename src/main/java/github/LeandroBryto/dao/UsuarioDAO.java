package github.LeandroBryto.dao;

import github.LeandroBryto.model.Usuario;
import github.LeandroBryto.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String username, String senha) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
        String query = "SELECT * FROM usuarios WHERE username = ? AND senha = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, senha);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setUsername(resultSet.getString("username"));
            usuario.setSenha(resultSet.getString("senha"));
            return usuario;
        }

        return null;
    }
}
