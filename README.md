# Trabalho Prático: *Java Concurrency Utilities*

<sub>Última atualização: 17/06/2023</sub>

## Sumário

- [Visão geral e objetivos](#visão-geral-e-objetivos)  
- [Tarefas](#tarefas)
  - [Implementação](#implementação)
  - [Experimentação](#experimentação)
  - [Relato](#relato)
- [Autoria e política de colaboração](#autoria-e-política-de-colaboração)
- [Entrega](#entrega)
- [Avaliação](#avaliação)
- [Dúvidas e informações](#dúvidas-e-informações)

## Visão geral e objetivos

O objetivo deste trabalho é colocar em prática alguns mecanismos avançados providos pela linguagem de programação Java dentre as *Java Concurrency Utilities* para a implementação de soluções para problemas por meio de programas concorrentes.

## Tarefas

### Implementação

A principal tarefa deste trabalho consiste em implementar, na linguagem de programação Java, uma nova versão concorrente do algoritmo de ordenação *merge sort* utilizando as *Java Concurrency Utilities* implementadas no pacote [`java.util.concurrent`](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/package-summary.html). Assim como a [versão anterior](https://github.com/ufrn-concprog/mergesort), o programa deverá receber como entrada um arquivo de texto dentre os disponíveis no diretório [`input`](input) deste repositório, cada um deles contendo um conjunto de valores inteiros a ser ordenado. Para cada arquivo, o programa deverá (i) ler os valores a partir do arquivo, (ii) realizar a ordenação dos valores, (iii) medir o tempo de execução despendido pela ordenação e (iv) gerar, como saída, um novo arquivo no diretório `output` com o conjunto ordenado de valores e o mesmo nome do arquivo fornecido como entrada. Ou seja, se o arquivo `A.in` foi utilizado como entrada, deve ser gerado um arquivo `A.out` como saída.

Na implementação do algoritmo, deverão ser utilizadas *threads* **apenas** na etapa de ordenação propriamente dita, ou seja, a entrada e saída de dados devem ser sequenciais. Por questões de simplicidade, o procedimento de junção das partições ordenadas do arranjo (*merge*) deve ser feita de forma sequencial/iterativa, de modo que essencialmente novas *threads* serão alocadas apenas para tratar as partições do arranjo que estão sendo ordenadas. Para gerenciamento das *threads* que executarão as tarefas definidas para o programa, devem ser utilizados *thread pools* de diferentes tipos disponibilizados pelo *framework* `Executor` e sua classe [`Executors`](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/concurrent/Executors.html),mais precisamente, *fixed*, *cached* e *work stealing thread pools*. **Deverão ser testados os três tipos de *thread pools***, porém será considerado para experimentação e análise **apenas** aquele que apresentar melhor eficiência em termos de tempo de execução e processamento de carga de trabalho (*workload*), isto é, conseguir ordenar um número maior de elementos.

Especificamente para o caso da versão do programa que utiliza um *fixed thread pool*, o programa deverá fixar um número predefinido de *threads* a serem utilizadas. Para as versões que utilizam *cached* e *work stealing thread pools*, os quais empregam umnúmero dinâmico de *threads*, deverá ser verificado e informado na saída padrão quantas *threads* estão sendo utilizadas. Isso pode ser verificado por meio do [JConsole](https://docs.oracle.com/en/java/javase/20/management/using-jconsole.html#GUID-A2AE31B2-6C50-47B4-B854-5212C5AE4955), uma ferramenta para monitoramentoda máquina virtual Java (JVM) sobre a `java.lang.management` API, ou invocando-se o método `activeCount` da classe [`Thread`](https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/lang/Thread.html).

### Experimentação

A tarefa de experimentação consiste em realizar sucessivas execuções da nova versão concorrente do *merge sort*, exatamente da mesma forma que foi realizado na [versão anterior](https://github.com/ufrn-concprog/mergesort). Um total de dez cenários deverá ser considerado na experimentação, cada um deles associado a uma quantidade $2^x$ de números inteiros a serem ordenados, sendo $10 \leq x < 20$, os quais estão dispostos em arquivos individuais disponíveis no diretório [`input`](input) deste repositório e estão nomeados alfabeticamente (`A`, `B`, `C`, ...).

No intuito de tornar os experimentos representativos do ponto de vista estatístico, será necessário executar cada uma das versões em um total de vinte vezes para cada cenário. Os tempos de execução de cada versão em cada uma dessas vinte execuções deverá ser registrado a fim de calcular os valores máximo, mínimo e médio dos tempos nas vinte execuções, além do desvio padrão observado. É importante utilizar uma unidade de medida em um nível de granularidade que torne possível a observação dos valores. Por exemplo, a utilização de segundos (ou mesmo milissegundos, dependendo do caso) como unidade de medida pode resultar em valores significativamente pequenos que sejam expressos praticamente como zero, o que não é desejável para o propósito da análise.

**Observação:** Não é necessário executar novamente a versão anterior da implementação concorrente do *merge sort*, mas sim apenas a nova versão implementada neste trabalho.

### Relato

Uma vez realizadas as tarefas de implementação e de experimentação, deverá ser elaborado um relatório contendo, no mínimo, as seguintes seções:

1. **Introdução.** Apresentar uma visão geral do programa implementado, inclusive descrevendo o tipo de *thread pool* utilizado na implementação.
2. **Resultados.** Apresentar os resultados obtidos na forma de gráfico de linha e tabelas. Deverá ser apresentada uma tabela contendo os tempos mínimo, médio e máximo obtidos em cada cenário de experimentação, além dos valores de desvio padrão. Por sua vez, o gráfico de linha deverá exibir apenas os tempos médios obtidos nas execuções.
3. **Conclusões.** Discutir os resultados, ou seja, o que foi possível concluir através dos resultados obtidos através dos experimentos, **incluindo necessariamente uma comparação com a versão anterior da implementação do algoritmo**, com foco principalmente no tempo de execução e na capacidade de processamento de carga de trabalho.

**Observação:** Caso a versão anterior não tenha sido implementada na linguagem de programação Java, a comparação deverá se ater unicamente à capacidade de processamento de carga de trabalho, uma vez que não seria possível realizar uma comparação justa no que se refere a tempo de execução em razão das diferenças significativas entre linguagens de programação, compilador e ambiente de execução.

## Autoria e política de colaboração

Este trabalho deverá necessariamente ser realizado em equipe composta de **até dois estudantes**, sendo importante, dentro do possível, dividir as tarefas igualmente entre os integrantes da equipe. Após a implementação das soluções para os problemas propostos, o arquivo [`author.md`](https://github.com/ufrn-concprog/mergesort-jcu/tree/master/author.md) presente no repositório deverá ser editado preenchendo as informações de identificação dos integrantes da equipe, na seção [Informações de Autoria](https://github.com/ufrn-concprog/mergesort-jcu/tree/master/author.md#identificação-de-autoria).

O trabalho em cooperação entre estudantes da turma é estimulado, sendo admissível a discussão de ideias e estratégias. Contudo, tal interação não deve ser entendida como permissão para utilização de (parte de) código fonte de colegas, o que pode caracterizar situação de plágio. **Trabalhos copiados no todo ou em parte de outros colegas ou da Internet serão anulados e receberão nota zero.**

## Entrega

O sistema de controle de versões [Git](https://git-scm.com) e o serviço de hospedagem de repositórios [GitHub](https://git-scm.com) serão utilizados para possibilitar a entrega da implementação realizadas. Para possibilitar a associação de repositórios Git para cada equipe e reuni-los sob uma mesma infraestrutura, foi criada uma atividade (*assignment*) no GitHub Classroom. Cada integrante de equipe deverá acessar este [*link*](https://classroom.github.com/a/6hu2SmRt), aceitar o convite para ingressar no GitHub Classroom e finalmente seguir as instruções em tela para acessar a atividade e ingressar em uma equipe existente ou criar outra. Este [vídeo](https://youtu.be/ObaFRGp_Eko) demonstra como ocorre esse processo.

No momento de criação de uma equipe, o GitHub Classroom cria um repositório Git privado acessível unicamente pelos integrantes da equipe e pelo docente, sob a organização [`ufrn-concprog`](https://github.com/ufrn-concprog). A fim de garantir a boa manutenção do repositório, deve-se ainda configurar corretamente o arquivo `.gitignore` para desconsiderar arquivos que não devam ser versionados, a exemplo dos arquivos executáveis gerado a partir da compilação do código fonte. Também não é necessário versionar os arquivos de saída resultantes do processo de ordenação.

A entrega deste trabalho deverá ser realizada até as **23:59 do dia 28 de junho de 2023** no respectivo repositório Git da equipe. O relatório elaborado, preferencialmente em formato *Adobe Portable Format* (PDF), deverá ser enviado através da opção *Tarefas* da Turma Virtual do SIGAA, juntamente com, no campo *Comentários*, o endereço do repositório. Um único membro da equipe deve realizar esse envio e não serão aceitos envios por outros meios ou repositórios que não sejam os descritos nesta especificação.

## Avaliação

A avaliação do trabalho contabilizará nota de até 4,0 pontos na 2ª Unidade da disciplina. O trabalho será avaliado de acordo com os seguintes critérios:

- utilização corretados mecanismos existentes dentre as *Java Concurrency Utilities*;
- a corretude da execução da solução implementada, tanto com relação a funcionalidades quanto a concorrência;
- a aplicação de boas práticas de programação, incluindo legibilidade, organização e documentação de código fonte, e;
- correta utilização do repositório Git, no qual deverá ser registrado todo o histórico da implementação por meio de *commits*, e;
- qualidade do relatório produzido.

## Dúvidas e informações

Caso haja qualquer dúvida, questionamento ou necessidade de informação adicional, é possível:

- enviar *e-mail* para o endereço <everton.cavalcante@ufrn.br>;
- enviar mensagem privada diretamente ao docente, utilizando o servidor Discord;
- enviar mensagem no canal de texto `#duvidas` no servidor Discord, ou;
- agendar encontros síncronos pelo canal de áudio `Fale com Prof. Everton` no servidor Discord.
