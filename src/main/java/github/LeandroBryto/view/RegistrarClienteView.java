package github.LeandroBryto.view;

import github.LeandroBryto.dao.ClienteDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarClienteView extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField cepField;
    private JTextField telefoneField;
    private JButton salvarButton;
    private JButton exibirClientesButton;
    private ClienteDAO clienteDAO;

    public RegistrarClienteView() {
        setTitle("Registrar Cliente");
        setSize(300, 250); // Ajustado o tamanho da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        clienteDAO = new ClienteDAO();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("CEP:"));
        cepField = new JTextField();
        panel.add(cepField);

        panel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        panel.add(telefoneField);

        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new SalvarClienteListener());
        panel.add(salvarButton);

        exibirClientesButton = new JButton("Exibir Clientes");
        exibirClientesButton.addActionListener(new ExibirClientesListener());
        panel.add(exibirClientesButton);

        getContentPane().add(panel);
    }

    private class SalvarClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String cep = cepField.getText();
            String telefone = telefoneField.getText();

            try {
                // Verifique se o cliente já existe no banco de dados
                if (clienteDAO.existeCliente(cpf)) {
                    JOptionPane.showMessageDialog(RegistrarClienteView.this, "Cliente já registrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clienteDAO.adicionarCliente(nome, cpf, cep, telefone);
                JOptionPane.showMessageDialog(RegistrarClienteView.this, "Cliente registrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(RegistrarClienteView.this, "Erro ao registrar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ExibirClientesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lógica para exibir os clientes
        }
    }
}
