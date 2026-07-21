import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // --- Configuração básica da janela ---
        JFrame janela = new JFrame("Gerenciador de tarefas do João!");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        // --- Calcula o tamanho da janela proporcional à tela do usuário ---
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = (int) (tela.width * 0.26);
        int altura = (int) (tela.height * 0.65);
        janela.setSize(largura, altura);

        // --- Calcula um tamanho de fonte proporcional à tela ---
        int tamanhoFonte = tela.width / 100;
        Font fontePadrao = new Font("SansSerif", Font.PLAIN, tamanhoFonte);
        
        // --- Painel de baixo (SOUTH), com os botões Tarefas e Menu ---
        JPanel inicio = new JPanel();
        JButton btnTarefas = new JButton("Tarefas");
        btnTarefas.setFont(fontePadrao);
        JButton btnMenu = new JButton("Menu");
        btnMenu.setFont(fontePadrao);
        inicio.add(btnTarefas);
        inicio.add(btnMenu);
        janela.add(inicio, BorderLayout.CENTER);

        // --- Centraliza a janela na tela e exibe ---
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}