# Sistema de Gerenciamento de Atividades

Um sistema de gerenciamento de tarefas (to-do list) desenvolvido em Java como projeto de aprendizado, construído do zero, com foco em entender profundamente cada conceito antes de avançar — programação orientada a objetos, estruturas de dados, interação via terminal e, em breve, interface gráfica e persistência de dados.

## Sobre o projeto

Este é o primeiro projeto Java "de verdade" do autor, construído em paralelo com o aprendizado da linguagem. A regra desde o início foi clara: nenhuma linha de código pronto é entregue — cada funcionalidade é implementada pelo próprio autor, com explicações conceituais, analogias e revisão de erros, mas nunca com a solução escrita por outra pessoa. O resultado é um projeto que cresceu de forma incremental, refletindo genuinamente o processo de aprendizado.

## Funcionalidades

- **Adicionar tarefas**, com prevenção automática de nomes duplicados
- **Concluir tarefas**, buscando por nome (não é necessário lembrar a posição na lista)
- **Cancelar tarefas** — tarefas canceladas permanecem no histórico, não são apagadas
- **Listar tarefas** com filtro por status: todas, pendentes, concluídas ou canceladas
- **Busca case-insensitive** — não importa se o nome foi digitado em maiúsculas, minúsculas ou misturado
- Menu interativo via terminal, com navegação entre "menu de status" e "menu de ações"

## Arquitetura

O projeto segue o princípio de responsabilidade única: cada classe sabe fazer uma coisa, e só essa coisa.

### `Tarefa`
Representa uma única tarefa. Guarda o nome e o status (`private`, protegidos por getters/setter), e nasce sempre com status `PENDENTE` através do construtor. Não sabe que existe uma lista de tarefas — só sabe de si mesma.

### `Status`
Um `enum` com três valores possíveis: `PENDENTE`, `CONCLUIDO`, `CANCELADO`. Substituiu uma versão inicial baseada em `boolean`, que só conseguia representar dois estados — insuficiente para o projeto desde que "cancelar" deixou de ser sinônimo de "concluir".

### `GerenciadorDeTarefas`
O coração do projeto. Guarda a coleção de tarefas (`ArrayList<Tarefa>`) e expõe os métodos que o resto do programa usa para manipulá-la:

- `adicionarTarefa(String nome)` — cria e adiciona, com checagem de duplicados
- `concluirTarefa(String nome)` / `cancelarTarefa(String nome)` — buscam a tarefa pelo nome e retornam `boolean`, indicando se a operação teve sucesso
- `procurarTarefas(String nome)` — método de busca interno, usado pelos três métodos acima, que percorre a lista comparando nomes com `.equalsIgnoreCase()` e devolve a posição encontrada (ou `-1`)
- `listarTarefas(Status filtro)` — monta uma string com as tarefas que batem com o filtro informado; `null` como filtro significa "sem filtro, mostrar todas"

### `Main`
Só cuida da interação com quem está usando o programa: exibe menus, lê respostas via `Scanner`, e delega toda a lógica de negócio para o `GerenciadorDeTarefas`. Não manipula a lista de tarefas diretamente em nenhum momento.

## Como rodar

```
javac -d bin src/*.java
java -cp bin Main
```

## Jornada de aprendizado

Esta seção documenta os principais obstáculos conceituais enfrentados durante a construção do projeto — não como uma lista de erros, mas como um registro de como cada conceito foi internalizado.

### Fundamentos de orientação a objetos

O início foi marcado por confusões clássicas de quem está vendo OOP pela primeira vez: tentar declarar uma classe dentro do método `main`, confundir o método `main` com um construtor, e errar repetidamente a **direção** de uma atribuição (escrever `nome = this.nome` quando o correto era `this.nome = nome`). Esse último erro em particular se repetiu em getters, setters e construtores até a lógica "o que está à direita do `=` vai para o que está à esquerda" ficar automática.

A diferença entre classe e objeto, o papel de um construtor, e por que os atributos deveriam ser `private` (protegidos por getters e setters) em vez de públicos foram os primeiros grandes saltos conceituais do projeto.

### Estruturas de dados e o enum `Status`

A introdução do `ArrayList<Tarefa>` trouxe a primeira experiência com generics e com os métodos `.add()`, `.get()` e `.size()`. Migrar de `boolean` para o `enum Status` — motivado pela necessidade real de representar três estados — exigiu propagar a mudança por `Tarefa`, `GerenciadorDeTarefas` e `Main` de forma consistente, um exercício de rastrear todos os lugares que dependiam do tipo antigo.

### Interação via terminal

Construir o menu no terminal passou por várias iterações estruturais: uma primeira tentativa via recursão (um método chamando a si mesmo) foi abandonada em favor de um `while` único, mais eficiente e mais fácil de acompanhar. Nesse processo, surgiram bugs sutis relacionados a escopo de variáveis (parâmetros não utilizados, objetos recriados dentro de métodos em vez de reaproveitados) que exigiram entender a diferença entre variável local e parâmetro compartilhado.

### Sessão de testes reais

Em um ponto do desenvolvimento, o projeto foi compilado e executado de fato — não apenas revisado por leitura — em busca de bugs. Isso revelou vários problemas que não eram visíveis apenas olhando o código:

- **`Scanner.nextInt()` deixando uma quebra de linha pendente no buffer**, causando um prompt duplicado logo depois de usar `concluir`/`cancelar` por número
- **Ausência de validação de índice**, causando `IndexOutOfBoundsException` com números inválidos, fora do intervalo ou lista vazia
- **`InputMismatchException`** ao digitar texto onde um número era esperado
- Uma variável de controle de menu que não era resetada em todos os caminhos possíveis, fazendo o menu de ações "grudar" e sequestrar entradas seguintes do usuário

A resposta a esses problemas foi arquitetural, não só um remendo pontual: trocar a busca por **índice numérico** pela busca por **nome da tarefa**, eliminando de raiz a necessidade de `nextInt()` e, consequentemente, o bug do buffer.

### Refatoração: buscar por nome

Migrar `concluirTarefa`/`cancelarTarefa` de `int indice` para `String nome` exigiu criar `procurarTarefas`, um método de busca linear com um valor sentinela (`-1` para "não encontrado"), e adaptar os métodos que dependiam dele para devolver `boolean`, permitindo que `Main` soubesse se a operação teve sucesso antes de decidir qual mensagem mostrar.

O filtro de listagem (`listarTarefas(Status filtro)`) passou por iterações parecidas: comparações fixas contra cada valor do enum foram substituídas por uma única comparação contra o parâmetro recebido, e um bug de concatenação (a string começando como `null` e a palavra "null" vazando para o resultado) foi corrigido trocando a inicialização para uma string vazia.

### Decisões de design

Duas escolhas deliberadas, tomadas conscientemente durante o desenvolvimento, valem registro:

1. **Cancelar não remove a tarefa da lista** — apenas muda seu status para `CANCELADO`. Isso foi uma correção de rumo: a versão inicial removia a tarefa por completo, o que a tornava invisível para sempre, incompatível com a ideia de manter um histórico completo de atividade.
2. **O menu de ações permanece aberto após uma entrada inválida ou uma busca sem resultado**, em vez de retornar automaticamente ao menu principal — uma escolha de fluxo intencional, priorizando permitir nova tentativa imediata sobre navegação "seguinda".

## Roadmap

- [x] **Fase 1** — Fundamentos: classes, encapsulamento, construtores
- [x] **Fase 2** — Estrutura de dados: `ArrayList`, `enum Status`, lógica de negócio
- [x] **Fase 3** — Interação via terminal: menu com `Scanner`, filtros de listagem
- [ ] **Fase 4** — Interface gráfica com Swing *(em andamento)*
- [ ] **Fase 5** — Persistência com banco de dados relacional via JDBC

## Tecnologias

- Java (JDK)
- IntelliJ IDEA
- Swing *(em breve)*
- JDBC / SQL *(planejado)*
