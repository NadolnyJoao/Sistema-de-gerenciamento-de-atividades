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

    public void concluirTarefa(String nome) {

        int indiceencontrado = procurarTarefas(nome);
        if (indiceencontrado == -1) {
            System.out.println("Nao encontrado");
        }else {
            Tarefa indiceTarefa = tarefas.get(indiceencontrado);
            indiceTarefa.setStatus(Status.CONCLUIDO);
        }

    }

    public void cancelarTarefa(int indice) {

        Tarefa indiceTarefa = tarefas.get(indice - 1);
        indiceTarefa.setStatus(Status.CANCELADO);
        if (indiceTarefa.getStatus() == Status.CANCELADO){
           tarefas.remove(indice - 1);
        }

    }
    private int procurarTarefas(String nome) {
        int holder = -1;
        for (int i = 0; i < tarefas.size(); i++)
        {
            if (tarefas.get(i).getNome().equals(nome))
            {
                holder = i;
                break;
            }
        }
    return holder;
    }


    public String listarTarefas() {
        String resultado = null;
        for (int i = 0; i < tarefas.size(); i++)
        //for (Tarefa tarefa : tarefas)
            {
            if (resultado == null) {
                resultado = (i + 1) + " nome: " + tarefas.get(i).getNome() + " Status: " + tarefas.get(i).getStatus();
            } else {
                resultado += ("\n" + (i + 1) + " nome: " + tarefas.get(i).getNome() + " Status: " + tarefas.get(i).getStatus());
            }
        }
        return resultado;

    }

}
