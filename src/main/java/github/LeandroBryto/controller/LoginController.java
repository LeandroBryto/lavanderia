package github.LeandroBryto.controller;


import github.LeandroBryto.dao.UsuarioDAO;
import github.LeandroBryto.model.Usuario;
import github.LeandroBryto.view.LoginView;
import github.LeandroBryto.view.MainView;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {
    private LoginView loginView;
    private UsuarioDAO usuarioDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.usuarioDAO = new UsuarioDAO();
        this.loginView.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsuario();
            String senha = loginView.getSenha();

            try {
                Usuario usuario = usuarioDAO.autenticar(username, senha);
                if (usuario != null) {
                    MainView mainView = new MainView();
                    mainView.setVisible(true);
                    loginView.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginView, "Usuário ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(loginView, "Erro de conexão com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
