
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        boolean sair = false;
        boolean sairmenu = false;
        boolean menu = false;
        GerenciadorDeTarefas gerente = new GerenciadorDeTarefas();
            while (!sair) {

                System.out.println("Tarefas - Menu");
                String escolhausrmenu = entrada.nextLine();

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

                }

                if (menu) {
                System.out.println("O que deseja fazer? \n adicionar: Adicionar uma tarefa. \n concluir: Concluir uma tarefa. \n cancelar: Cancelar uma tarefa. \n sair: Encerra o programa");
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
                        System.out.println("Qual tarefa deseja concluir?");

                        break;
                    case "Cancelar":
                    case "cancelar":
                        System.out.println("Qual tarefa quer cancelar?");
                        break;
                    case "Sair":
                    case "sair":
                        System.out.println("Encerrando...");
                        sair = true;
                        break;

                    default:
                        System.out.println("Por favor, digite uma opção válida.");

                }


            }

        }
    }


}


