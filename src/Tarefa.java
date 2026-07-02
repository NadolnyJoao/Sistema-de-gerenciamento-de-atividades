public class Tarefa {
    private String nome;
    private Status status = Status.PENDENTE;

    public Tarefa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status novoStatus) {
        this.status = novoStatus;
    }

}
