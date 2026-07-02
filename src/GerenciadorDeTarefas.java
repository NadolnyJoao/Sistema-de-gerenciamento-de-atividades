import java.util.ArrayList;

public class GerenciadorDeTarefas {
    private ArrayList<Tarefa> tarefas;

    public GerenciadorDeTarefas() {

        this.tarefas = new ArrayList<Tarefa>();

    }

    public void adicionarTarefa(String nome) {
        Tarefa novaTarefa = new Tarefa(nome);
        tarefas.add(novaTarefa);
    }

    public void concluirTarefa(int indice) {

        Tarefa indiceTarefa = tarefas.get(indice);
        indiceTarefa.setStatus(Status.CONCLUIDO);
    }

    public void cancelarTarefa(int indice) {

        Tarefa indiceTarefa = tarefas.get(indice);
        indiceTarefa.setStatus(Status.CANCELADO);
    }

    public String listarTarefas() {
        String resultado = null;
        for (Tarefa tarefa : tarefas) {
            if (resultado == null) {
                resultado = ("nome: " + tarefa.getNome() + " Status: " + tarefa.getStatus());
            } else {
                resultado += ("\n nome: " + tarefa.getNome() + " Status: " + tarefa.getStatus());
            }
        }
        return resultado;

    }

}
