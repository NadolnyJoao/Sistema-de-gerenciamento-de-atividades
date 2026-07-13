
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

                switch (escolhausrmenu) {

                    case "Menu":
                    case "menu":
                        menu = true;
                        break;

                    case "Tarefas":
                    case "tarefas":

                        String showtarefas = gerente.listarTarefas(null);
                        if(showtarefas.isEmpty()) {
                            System.out.println("\n Voce nao tem nenhuma tarefa adicionada, \n por favor, adicione alguma.");
                        }else
                        {
                            System.out.println("\n Quais tarefas você quer listar? \n Todas - Pendentes - Concluidas - Canceladas");
                            String tarefasoption = entrada.nextLine();
                            switch (tarefasoption)
                            {
                                case "Pendentes":
                                case "pendentes":
                                    System.out.println(gerente.listarTarefas((Status.PENDENTE)));

                                    break;
                                case "Todas":
                                case "todas":
                                    System.out.println(gerente.listarTarefas((null)));

                                    break;
                                case "Concluidas":
                                case "concluidas":
                                    System.out.println(gerente.listarTarefas((Status.CONCLUIDO)));
                                    break;
                                case "Canceladas":
                                case "canceladas":
                                    System.out.println (gerente.listarTarefas((Status.CANCELADO)));
                                    break;



                            }
                        }

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
                        String showtarefas = gerente.listarTarefas(null);
                        System.out.println(showtarefas);
                        menu = false;
                        break;

                    case "Concluir":
                    case "concluir":
                        System.out.println("Qual tarefa deseja concluir?");
                        boolean achou = gerente.concluirTarefa(entrada.nextLine());

                         if (achou){
                             System.out.println("Tarefa concluida, o que deseja fazer agora?  ");
                             menu = false;
                             break;
                        }  else {break;}

                    case "Cancelar":
                    case "cancelar":
                        System.out.println("Qual tarefa quer cancelar?");
                        achou = gerente.cancelarTarefa(entrada.nextLine());
                        if(achou)
                        {
                            System.out.println("Tarefa cancelada com sucesso");
                            menu = false;
                            break;
                        }else{break;}

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


