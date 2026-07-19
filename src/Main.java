import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JButton;

public class Main {

    public static void main(String[] args) {
        JFrame janela = new JFrame("Gerenciador de tarefas do João!");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 700);
        janela.setLayout(new BorderLayout());
        JButton btnNorte = new JButton("Norte");
        btnNorte.setPreferredSize(new Dimension(100, 80));
        JButton btnCentro = new JButton("Centro");
        janela.add(btnCentro, BorderLayout.CENTER);
        janela.add(btnNorte, BorderLayout.NORTH);



        janela.setVisible(true);
    }
}