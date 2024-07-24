package github.LeandroBryto.view;


import github.LeandroBryto.dao.ClienteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextArea areaTexto;
    private JTextField quantidadeField;
    private JTextField precoField;
    private JTextField totalField;
    private JTextField nomeClienteField;
    private JComboBox<String> discriminacaoComboBox;
    private JCheckBox lavarCheckBox;
    private JCheckBox secarCheckBox;
    private JCheckBox passarCheckBox;
    private JCheckBox soPassarCheckBox;
    private JButton gerarComprovanteButton;
    private JButton registrarClienteButton;
    private ClienteDAO clienteDAO;

    public MainView() {
        setTitle("RS LAVANDERIA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        clienteDAO = new ClienteDAO();
        initComponents();
    }

    private void initComponents() {
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText("Bem-vindo ao sistema de gerenciamento da lavanderia RS LAVANDERIA!");

        JScrollPane scrollPane = new JScrollPane(areaTexto);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nome do Cliente:"), gbc);
        nomeClienteField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(nomeClienteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Quantidade:"), gbc);
        quantidadeField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(quantidadeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Discriminação:"), gbc);
        String[] itens = {"Blusa", "Calças", "Casacos", "Camisa", "Camisetas", "Camisolas", "Diversos", "Bobes/Roupão", "Colchas", "Short", "Cueca/Calcinhas", "Soutian", "Fronhas", "Guardanapos", "Lençóis", "Lenços/Meias", "Macacões", "Pijamas", "Saias", "Bermudas", "Toalhas de Rosto", "Vestidos", "Toalhas de Banho", "Toalhas de Mesa", "Trainings/Colonte"};
        discriminacaoComboBox = new JComboBox<>(itens);
        gbc.gridx = 1;
        inputPanel.add(discriminacaoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Preço:"), gbc);
        precoField = new JTextField(10);
        gbc.gridx = 1;
        inputPanel.add(precoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Total:"), gbc);
        totalField = new JTextField(10);
        totalField.setEditable(false);
        gbc.gridx = 1;
        inputPanel.add(totalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(new JLabel("Serviços:"), gbc);
        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new GridLayout(4, 1, 5, 5));
        lavarCheckBox = new JCheckBox("Lavar");
        secarCheckBox = new JCheckBox("Secar");
        passarCheckBox = new JCheckBox("Passar");
        soPassarCheckBox = new JCheckBox("Só Passar");

        servicePanel.add(lavarCheckBox);
        servicePanel.add(secarCheckBox);
        servicePanel.add(passarCheckBox);
        servicePanel.add(soPassarCheckBox);
        gbc.gridy = 6;
        inputPanel.add(servicePanel, gbc);

        gerarComprovanteButton = new JButton("Gerar Comprovante");
        gerarComprovanteButton.setPreferredSize(new Dimension(150, 30));
        gerarComprovanteButton.addActionListener(new GerarComprovanteListener());
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        inputPanel.add(gerarComprovanteButton, gbc);

        registrarClienteButton = new JButton("Registrar Cliente");
        registrarClienteButton.setPreferredSize(new Dimension(150, 30));
        registrarClienteButton.addActionListener(new RegistrarClienteListener());
        gbc.gridy = 8;
        inputPanel.add(registrarClienteButton, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(inputPanel, BorderLayout.EAST);
        getContentPane().add(rightPanel, BorderLayout.EAST);
    }

    public void appendToAreaTexto(String texto) {
        areaTexto.append(texto + "\n");
    }

    private class GerarComprovanteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomeCliente = nomeClienteField.getText();
            String quantidade = quantidadeField.getText();
            String discriminacao = (String) discriminacaoComboBox.getSelectedItem();
            String preco = precoField.getText();

            // Lógica de validação
            if (nomeCliente.isEmpty() || quantidade.isEmpty() || preco.isEmpty() || !isServiceSelected()) {
                JOptionPane.showMessageDialog(MainView.this, "Todos os campos devem ser preenchidos e pelo menos um serviço deve ser selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cálculo do total
            double total = Double.parseDouble(quantidade) * Double.parseDouble(preco);
            totalField.setText(String.valueOf(total));

            // Lógica de geração de serviços
            StringBuilder serviços = new StringBuilder();
            if (lavarCheckBox.isSelected()) serviços.append("Lavar ");
            if (secarCheckBox.isSelected()) serviços.append("Secar ");
            if (passarCheckBox.isSelected()) serviços.append("Passar ");
            if (soPassarCheckBox.isSelected()) serviços.append("Só Passar ");

            // Criar e exibir o comprovante
            ComprovanteView comprovanteView = new ComprovanteView(nomeCliente, quantidade, discriminacao, preco, total, serviços.toString().trim());
            comprovanteView.setVisible(true);
        }

        private boolean isServiceSelected() {
            return lavarCheckBox.isSelected() || secarCheckBox.isSelected() || passarCheckBox.isSelected() || soPassarCheckBox.isSelected();
        }
    }

    private class RegistrarClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegistrarClienteView().setVisible(true);
        }
    }
}

