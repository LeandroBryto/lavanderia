package github.LeandroBryto.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComprovanteView extends JFrame {
    private JTextArea comprovanteArea;

    public ComprovanteView(String nomeCliente, String quantidade, String discriminacao, String preco, double total, String serviços) {
        setTitle("Comprovante de Serviço");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents(nomeCliente, quantidade, discriminacao, preco, total, serviços);
    }

    private void initComponents(String nomeCliente, String quantidade, String discriminacao, String preco, double total, String serviços) {
        comprovanteArea = new JTextArea();
        comprovanteArea.setEditable(false);
        comprovanteArea.setText(generateComprovanteText(nomeCliente, quantidade, discriminacao, preco, total, serviços));
        JScrollPane scrollPane = new JScrollPane(comprovanteArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private String generateComprovanteText(String nomeCliente, String quantidade, String discriminacao, String preco, double total, String serviços) {
        // Obter data e hora atual
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format("Comprovante de Serviço\n\n" +
                        "Cliente: %s\n" +
                        "Quantidade: %s\n" +
                        "Discriminação: %s\n" +
                        "Preço: %s\n" +
                        "Total: %.2f\n" +
                        "Serviços: %s\n" +
                        "Data e Hora: %s",
                nomeCliente, quantidade, discriminacao, preco, total, serviços, now.format(formatter));
    }
}
