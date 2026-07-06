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

        if ( indice <= 0 || indice > tarefas.toArray().length){
            System.out.println("Numero invalido, por favor, digite outra opçao");
        }else  {
            Tarefa indiceTarefa = tarefas.get(indice - 1);
            indiceTarefa.setStatus(Status.CONCLUIDO);}



    }

    public void cancelarTarefa(int indice) {

        Tarefa indiceTarefa = tarefas.get(indice - 1);
        indiceTarefa.setStatus(Status.CANCELADO);
        if (indiceTarefa.getStatus() == Status.CANCELADO){
           tarefas.remove(indice - 1);
        }

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
