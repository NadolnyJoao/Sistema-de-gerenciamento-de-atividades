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
        CardLayout layoutC = new CardLayout();
        JPanel painelPrincipal = new JPanel();
        JPanel telaInicial = new JPanel();
        JPanel telaLista = new JPanel();
        JPanel telaMenu = new JPanel();
        painelPrincipal.setLayout(layoutC);
        painelPrincipal.add(telaInicial, "Tela Inicial");
        painelPrincipal.add(telaLista, "Lista das tarefas");
        painelPrincipal.add(telaMenu, "Menu");
        // Botão Tarefas
        JButton botaoTarefas = new JButton("Tarefas");
        botaoTarefas.setFont(fontePadrao);
        botaoTarefas.addActionListener(evento -> {
            layoutC.show(painelPrincipal, "Lista das tarefas");
            
        });

        //Botão Voltar
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(fontePadrao);
        botaoVoltar.addActionListener(evento ->{
            layoutC.show(painelPrincipal, "Tela Inicial");
        });
        //Botão Voltar do Menu
        JButton botaoVoltarDois = new JButton("Voltar");
        botaoVoltarDois.setFont(fontePadrao);
        botaoVoltarDois.addActionListener(evento ->{
            layoutC.show(painelPrincipal, "Tela Inicial");
        });



        // Botão Menu
        JButton botaoMenu = new JButton("Menu");
        botaoMenu.setFont(fontePadrao);
        botaoMenu.addActionListener(evento ->{
            layoutC.show(painelPrincipal, "Menu");

        });

        //Config da tela inicial
        telaInicial.add(botaoTarefas);
        telaInicial.add(botaoMenu);
        janela.add(painelPrincipal, BorderLayout.CENTER);

        //Config da tela de tarefas
        telaLista.add(botaoVoltar);


        //Config da tela do Menu
        telaMenu.add(botaoVoltarDois);



        // Centraliza e exibe a janela
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}