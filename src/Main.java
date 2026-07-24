import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        gerenciador.adicionarTarefa("TarefaTeste");

        // Configuração básica da janela
        JFrame janela = new JFrame("Gerenciador de tarefas do João!");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        // Tamanho da janela proporcional à tela do usuário
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();

        int larguraJanela = (int) (tamanhoTela.width * 0.26);
        int alturaJanela = (int) (tamanhoTela.height * 0.65);

        janela.setSize(larguraJanela, alturaJanela);

        // Tamanho da fonte proporcional à largura da tela, isso não esta padrao, a cada mensagem ou tela nova a fonte reseta
        int tamanhoFonte = tamanhoTela.width / 100;
        Font fontePadrao = new Font(
                Font.SANS_SERIF,
                Font.PLAIN,
                tamanhoFonte
        );

        // Painel principal com CardLayout
        JPanel painelPrincipal = new JPanel(new CardLayout());
        JPanel telaInicial = new JPanel();

        painelPrincipal.add(telaInicial, "Tela Inicial");

        // Botão Tarefas
        JButton botaoTarefas = new JButton("Tarefas");
        botaoTarefas.setFont(fontePadrao);

        botaoTarefas.addActionListener(evento -> {
            System.out.println(gerenciador.listarTarefas(null));
            System.out.println("Funcionou");
        });

        // Botão Menu
        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setFont(fontePadrao);

        telaInicial.add(botaoTarefas);
        telaInicial.add(botaoMenu);

        janela.add(painelPrincipal, BorderLayout.CENTER);

        // Centraliza e exibe a janela
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}