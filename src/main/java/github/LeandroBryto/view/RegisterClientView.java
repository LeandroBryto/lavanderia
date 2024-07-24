package github.LeandroBryto.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterClientView extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField cepField;
    private JTextField telefoneField;
    private JButton registerButton;
    private JButton displayClientsButton;
    private JTextArea clientDisplayArea;

    public RegisterClientView() {
        setTitle("Registrar Cliente");
        setSize(400, 300); // Reduzido o tamanho da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        nomeField = new JTextField(15);
        cpfField = new JTextField(15);
        cepField = new JTextField(15);
        telefoneField = new JTextField(15);
        registerButton = new JButton("Registrar");
        displayClientsButton = new JButton("Exibir Clientes");
        clientDisplayArea = new JTextArea(8, 30);
        clientDisplayArea.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(nomeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("CEP:"), gbc);
        gbc.gridx = 1;
        add(cepField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        add(telefoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(registerButton, gbc);

        gbc.gridx = 1;
        add(displayClientsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(new JScrollPane(clientDisplayArea), gbc);
    }

    public void addRegisterClientListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void addDisplayClientsListener(ActionListener listener) {
        displayClientsButton.addActionListener(listener);
    }

    public String getNome() {
        return nomeField.getText();
    }

    public String getCpf() {
        return cpfField.getText();
    }

    public String getCep() {
        return cepField.getText();
    }

    public String getTelefone() {
        return telefoneField.getText();
    }

    public void displayClients(String clients) {
        clientDisplayArea.setText(clients);
    }
}
