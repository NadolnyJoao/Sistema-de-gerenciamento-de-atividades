import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception erro){
            erro.printStackTrace();
        }
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        DefaultListModel<Tarefa> modeloLista = new DefaultListModel<>();
        gerenciador.adicionarTarefa("TarefaTeste");

        atualizarLista(modeloLista, gerenciador);
        JList<Tarefa> listaTarefas = new JList<>(modeloLista);

        // Configuração básica da janela
        JFrame janela = new JFrame("Gerenciador de tarefas do João!");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        // Tamanho da janela proporcional à tela do usuário
        Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();

        int larguraJanela = (int) (tamanhoTela.width * 0.26);
        int alturaJanela = (int) (tamanhoTela.height * 0.65);

        janela.setSize(larguraJanela, alturaJanela);

        JPopupMenu menuOpcoes = new JPopupMenu();
        JMenuItem concluir = new JMenuItem("Concluir");
        concluir.addActionListener(evento -> {
            Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();

            if (tarefaSelecionada != null){
                String nomeDaTarefa = tarefaSelecionada.getNome();
                gerenciador.concluirTarefa(nomeDaTarefa);
                atualizarLista(modeloLista, gerenciador);
            }
        });
        menuOpcoes.add(concluir);

        JMenuItem cancelar = new JMenuItem("Cancelar");
        cancelar.addActionListener(evento -> {
            Tarefa tarefaSelecionada = listaTarefas.getSelectedValue();
            if (tarefaSelecionada != null){
                String nomeDaTarefa = tarefaSelecionada.getNome();
                gerenciador.cancelarTarefa(nomeDaTarefa);
                atualizarLista(modeloLista, gerenciador);
            }
        });
        menuOpcoes.add(cancelar);
        listaTarefas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                int indiceClicado = listaTarefas.locationToIndex(evento.getPoint());
                if (indiceClicado != -1){
                   Rectangle areaTarefa = listaTarefas.getCellBounds(indiceClicado, indiceClicado);
                   if (areaTarefa.contains(evento.getPoint())){
                        listaTarefas.setSelectedIndex(indiceClicado);
                       menuOpcoes.show(listaTarefas, evento.getX(), evento.getY());
                   }else {
                       listaTarefas.clearSelection();
                   }
                }else{
                    listaTarefas.clearSelection();
                }

                //

            }

        });


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



        //Botão Adicionar Tarefa
        JButton botaoAdd = new JButton("Adicionar Tarefa");
        botaoAdd.addActionListener(evento -> {
           // gerenciador.adicionarTarefa("TarefaTestedois");
            String tarefaUsr = JOptionPane.showInputDialog(janela, "Qual a tarefa a ser adicionada?");
            if ((tarefaUsr != null)&& (!tarefaUsr.isBlank())){
                gerenciador.adicionarTarefa(tarefaUsr);
            }
            atualizarLista(modeloLista, gerenciador);

        });
        //Botão Filtrar tarefas
        JButton botaoFiltro = new JButton("Filtrar");
        botaoFiltro.addActionListener(evento -> {

        });


        // Botão Menu


        //Config da tela inicial
        telaInicial.setLayout(new BorderLayout());
        telaInicial.add(listaTarefas, BorderLayout.CENTER);
        janela.add(painelPrincipal, BorderLayout.CENTER);
        telaInicial.add(botaoAdd, BorderLayout.NORTH);

        // Centraliza e exibe a janela
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    private static void atualizarLista(DefaultListModel<Tarefa> modeloLista, GerenciadorDeTarefas gerenciador){
        modeloLista.clear();
        ArrayList<Tarefa> tarefasAtuais = gerenciador.getTarefas();
        for (Tarefa tarefa : tarefasAtuais){
            modeloLista.addElement(tarefa);
        }

    }
}