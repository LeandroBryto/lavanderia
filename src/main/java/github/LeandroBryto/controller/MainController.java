package github.LeandroBryto.controller;


import github.LeandroBryto.model.Cliente;
import github.LeandroBryto.model.ItemOrdem;
import github.LeandroBryto.view.MainView;



import java.util.List;

public class MainController {
    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    public void gerarComprovante(List<ItemOrdem> itens, Cliente cliente) {
        StringBuilder comprovante = new StringBuilder();
        comprovante.append("RS LAVANDERIA\n");
        comprovante.append("Cliente: ").append(cliente.getNome()).append("\n");
        comprovante.append("CPF: ").append(cliente.getCpf()).append("\n");
        comprovante.append("Serviços:\n");

        double total = 0;
        for (ItemOrdem item : itens) {
            comprovante.append(item.getQuantidade()).append(" x ")
                    .append(item.getServico().getNome()).append(" - ")
                    .append(String.format("%.2f", item.getSubtotal())).append("\n");
            total += item.getSubtotal();
        }

        comprovante.append("Total: ").append(String.format("%.2f", total)).append("\n");

        mainView.appendToAreaTexto(comprovante.toString());
    }
}
