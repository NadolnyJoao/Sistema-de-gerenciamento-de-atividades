
import java.util.Locale;
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
                String escolhamenuUpper = escolhausrmenu.toUpperCase();

                switch (escolhamenuUpper) {

                    case "MENU":
                        menu = true;
                        break;

                    case "TAREFAS":

                        String showtarefas = gerente.listarTarefas(null);
                        if(showtarefas.isEmpty()) {
                            System.out.println("\n Voce nao tem nenhuma tarefa adicionada, \n por favor, adicione alguma.");
                        }else
                        {
                            System.out.println("\n Quais tarefas você quer listar? \n Todas - Pendentes - Concluidas - Canceladas");
                            String tarefasoption = entrada.nextLine();
                            String tarefasoptionUpper = tarefasoption.toUpperCase();
                            switch (tarefasoptionUpper)
                            {
                                case "PENDENTES":
                                    System.out.println(gerente.listarTarefas((Status.PENDENTE)));

                                    break;
                                case "TODAS":
                                    System.out.println(gerente.listarTarefas((null)));

                                    break;
                                case "CONCLUIDAS":
                                    System.out.println(gerente.listarTarefas((Status.CONCLUIDO)));
                                    break;
                                case "CANCELADAS":
                                    System.out.println (gerente.listarTarefas((Status.CANCELADO)));
                                    break;



                            }
                        }

                        break;
                    case "SAIR":
                        System.out.println("Encerrando...");
                        sair = true;
                        break;

                }

                if (menu) {
                System.out.println("O que deseja fazer? \n adicionar: Adicionar uma tarefa. \n concluir: Concluir uma tarefa. \n cancelar: Cancelar uma tarefa. \n voltar: volta para o menu");
                String escolhausr = entrada.nextLine();
                String escolhausrUpper = escolhausr.toUpperCase();
                switch (escolhausrUpper) {
                    case "ADICIONAR":
                        System.out.println("Qual a tarefa a ser adicionada?");
                        gerente.adicionarTarefa(entrada.nextLine());
                        String showtarefas = gerente.listarTarefas(null);
                        System.out.println(showtarefas);
                        menu = false;
                        break;

                    case "CONCLUIR":
                        System.out.println("Qual tarefa deseja concluir?");
                        boolean achou = gerente.concluirTarefa(entrada.nextLine());

                         if (achou){
                             System.out.println("Tarefa concluida, o que deseja fazer agora?  ");
                             menu = false;
                             break;
                        }  else {break;}

                    case "CANCELAR":
                        System.out.println("Qual tarefa quer cancelar?");
                        achou = gerente.cancelarTarefa(entrada.nextLine());
                        if(achou)
                        {
                            System.out.println("Tarefa cancelada com sucesso");
                            menu = false;
                            break;
                        }else{break;}

                    case "VOLTAR":
                        menu = false;
                        break;


                    default:
                        System.out.println("Por favor, digite uma opção válida.");

                }


            }

        }
    }


}


