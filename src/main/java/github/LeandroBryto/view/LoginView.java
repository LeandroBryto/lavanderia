package github.LeandroBryto.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        btnLogin = new JButton("Login");
        add(btnLogin);
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }
}
