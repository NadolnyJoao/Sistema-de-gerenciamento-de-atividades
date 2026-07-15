import java.util.ArrayList;

public class GerenciadorDeTarefas {
    private ArrayList<Tarefa> tarefas;

    public GerenciadorDeTarefas() {

        this.tarefas = new ArrayList<Tarefa>();

    }

    public void adicionarTarefa(String nome) {
        Tarefa novaTarefa = new Tarefa(nome);
        int indiceencontrado = procurarTarefas(nome);
        if (indiceencontrado == -1) {
            tarefas.add(novaTarefa);
        }else
        {
            System.out.println("Essa tarefa já existe");
        }


    }

    public boolean concluirTarefa(String nome) {

        int indiceencontrado = procurarTarefas(nome);
        if (indiceencontrado == -1) {
            System.out.println("Nao encontrado");
        }else {

            Tarefa indiceTarefa = tarefas.get(indiceencontrado);
            indiceTarefa.setStatus(Status.CONCLUIDO);
            return true;
        }
        return false;
    }

    public boolean cancelarTarefa(String nome) {

        int indiceencontrado = procurarTarefas(nome);
        if (indiceencontrado == -1) {
            System.out.println("Nao encontrado");
        }else {
            Tarefa indiceTarefa = tarefas.get(indiceencontrado);
            indiceTarefa.setStatus(Status.CANCELADO);
            //tarefas.remove(indiceencontrado);
            return true;
        }
        return false;



    }
    public int procurarTarefas(String nome) {
        int holder = -1;
        for (int i = 0; i < tarefas.size(); i++)
        {
            if (tarefas.get(i).getNome().equalsIgnoreCase(nome))
            {
                holder = i;
                break;
            }
        }
    return holder;
    }


    public String listarTarefas(Status filtro) {
        String resultado = "";
        for (int i = 0; i < tarefas.size(); i++)
        //for (Tarefa tarefa : tarefas)
            {
                if (filtro == null)
                {
                    resultado += (i + 1) + " - nome: " + tarefas.get(i).getNome() + " Status: " + tarefas.get(i).getStatus() + "\n";
                }
                else if(tarefas.get(i).getStatus() == filtro)
                {
                    resultado += (i + 1) + " - nome: " + tarefas.get(i).getNome() + " Status: " + tarefas.get(i).getStatus() + "\n";

                }
            //if (resultado == null) {
              //  resultado = (i + 1) + " - nome: " + tarefas.get(i).getNome() + " Status: " + tarefas.get(i).getStatus() + "\n";
            //}
        }
        return resultado;

    }

}
