
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        boolean sair = false;
        boolean sairmenu = false;
        boolean menu = false;
        GerenciadorDeTarefas gerente = new GerenciadorDeTarefas();
            while (!sair) {

                System.out.println("Tarefas - Menu - Sair");
                String escolhausrmenu = entrada.nextLine();
                String noEnter = escolhausrmenu.replace("\n" , " ");
                switch (escolhausrmenu) {

                    case "Menu":
                    case "menu":
                        menu = true;
                        break;

                    case "Tarefas":
                    case "tarefas":

                        String showtarefas = gerente.listarTarefas();
                        if(showtarefas == null) {
                            System.out.println("\n Voce nao tem nenhuma tarefa adicionada, \n por favor, adicione alguma.");
                        }else System.out.println(showtarefas);

                        break;
                    case "Sair":
                    case "sair":
                        System.out.println("Encerrando...");
                        sair = true;
                        break;

                }

                if (menu) {
                System.out.println("O que deseja fazer? \n adicionar: Adicionar uma tarefa. \n concluir: Concluir uma tarefa. \n cancelar: Cancelar uma tarefa. \n voltar: volta para o menu");
                String escolhausr = entrada.nextLine();
                switch (escolhausr) {
                    case "Adicionar":
                    case "adicionar":
                        System.out.println("Qual a tarefa a ser adicionada?");
                        gerente.adicionarTarefa(entrada.nextLine());
                        String showtarefas = gerente.listarTarefas();
                        System.out.println(showtarefas);
                        menu = false;
                        break;

                    case "Concluir":
                    case "concluir":
                        System.out.println("Qual o numero da tarefa que deseja concluir?");
                        gerente.concluirTarefa(entrada.nextLine());
                        System.out.println("Tarefa concluida, o que deseja fazer agora?  ");
                        menu = false;
                        break;
                    case "Cancelar":
                    case "cancelar":
                        System.out.println("Qual tarefa quer cancelar?");
                        gerente.cancelarTarefa(entrada.nextInt());
                        System.out.println("Tarefa cancelada, ela não vai mais aparecer em sua lista de tarefas");
                        menu = false;
                        break;
                    case "Voltar":
                    case "voltar":
                        menu = false;
                        break;


                    default:
                        System.out.println("Por favor, digite uma opção válida.");

                }


            }

        }
    }


}


