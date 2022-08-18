# DDD - Domain Driven Design
iniciado em 11/08/2022

terminado em 

[certificate]() 

- [DDD - Domain Driven Design](#ddd---domain-driven-design)
- [Java e Domain Driven Design: apresentando os conceitos](#java-e-domain-driven-design-apresentando-os-conceitos)
  - [Linguagem Ubíqua](#linguagem-ubíqua)
    - [Apresentaçao](#apresentaçao)
    - [Domain Driven Design](#domain-driven-design)
      - [Linguagem Ubíqua](#linguagem-ubíqua-1)
      - [Contexto delimitado](#contexto-delimitado)
      - [Blocos de construçao](#blocos-de-construçao)
    - [explicando o projeto](#explicando-o-projeto)
      - [Padrao Value Object (VO)](#padrao-value-object-vo)
      - [Padrao Repository](#padrao-repository)
    - [Para saber mais: Building blocks](#para-saber-mais-building-blocks)
    - [Linguagem Ubíqua (ubiquous language)](#linguagem-ubíqua-ubiquous-language)
      - [Use cases](#use-cases)
    - [O que aprendemos?](#o-que-aprendemos)
  - [Aggregates](#aggregates)
    - [Relação Aluno-Telefone](#relação-aluno-telefone)
      - [Invariantes (Business Invariant) - Regras de negócio](#invariantes-business-invariant---regras-de-negócio)
    - [Protegendo o acesso](#protegendo-o-acesso)
      - [Value Objects](#value-objects)
      - [Aggregate Root](#aggregate-root)
    - [DDD_Aggregate (https://martinfowler.com/bliki/DDD_Aggregate.html)](#ddd_aggregate-httpsmartinfowlercomblikiddd_aggregatehtml)
    - [Persistência de Aggregates](#persistência-de-aggregates)
  - [Eventos](#eventos)
    - [Eventos de Domínio](#eventos-de-domínio)
    - [Reagindo a eventos](#reagindo-a-eventos)
    - [Publicando eventos](#publicando-eventos)
    - [O que aprendemos?](#o-que-aprendemos-1)
  - [contextos delimitados (bounded contexts)](#contextos-delimitados-bounded-contexts)
    - [Implementando a gamificação](#implementando-a-gamificação)
    - [Separando os contextos](#separando-os-contextos)
      - [Package academico](#package-academico)
      - [Package gamificaçao](#package-gamificaçao)
    - [Mapas de contexto](#mapas-de-contexto)
      - [Nucleo compartilhado](#nucleo-compartilhado)
    - [Para saber mais: Bounded Contexts](#para-saber-mais-bounded-contexts)
      - [https://martinfowler.com/bliki/BoundedContext.html](#httpsmartinfowlercomblikiboundedcontexthtml)
    - [O que aprendemos?](#o-que-aprendemos-2)
  - [Contexto compartilhado (Shared Kernel)](#contexto-compartilhado-shared-kernel)
    - [Shared Kernel](#shared-kernel)
    - [Para saber mais: Shared Kernel](#para-saber-mais-shared-kernel)
    - [Ouvintes independentes](#ouvintes-independentes)
    - [Utilização dos contextos](#utilização-dos-contextos)
    - [O que aprendemos?](#o-que-aprendemos-3)
  - [Camada anticorrupção](#camada-anticorrupção)
    - [Sistemas distribuídos](#sistemas-distribuídos)
    - [Camada Anticorrupção](#camada-anticorrupção)
    - [Para saber mais: Referências](#para-saber-mais-referências)
    - [O que aprendemos?](#o-que-aprendemos-4)


# Java e Domain Driven Design: apresentando os conceitos

## Linguagem Ubíqua
### Apresentaçao
vamos aprender o que é Domain Driven Design, que é essa ideia de fazer o design do código orientado ao domínio da aplicação, vamos aprender os conceitos do Domain Driven Design e aplicá-los na prática em um projeto.

O projeto é o projeto que nós trabalhamos no treinamento de Clean Architecture, que é um pré-requisito desse treinamento, então recomendo que você faça antes de fazer esse curso de Domain Driven Design o treinamento sobre Clean Architecture com Java. 

Naquele treinamento, nós criamos uma aplicação e como foi um treinamento focado em arquitetura, nós até meio que já seguimos alguns dos conceitos do Domain Driven Design.

Então, nesse curso, vamos pegar aquele mesmo projeto e vamos dar continuidade a ele, então, no projeto do curso anterior, nós paramos com esse sistema, que simula uma escola, como se fosse uma Alura, e nós tínhamos uma funcionalidade de matricular aluno e de fazer indicações. 

Então nesse curso de Domain Driven Design, nós vamos dar continuidade a este projeto, adicionando algumas novas funcionalidades.

Então vamos aprender alguns conceitos sobre entidade, sobre value object, service, repositories, que já até meio que implementamos no outro projeto, mas depois vamos aprender sobre separação de contextos. 

Nós só temos esse contexto acadêmico de matrículas e tal. 

Depois vamos fazer uma parte de gameficação, simular um outro contexto, onde o aluno ganha pontos, ganha selos, a cada ação que ele dispara, criar um desacoplamento entre os contextos.

Vamos trabalhar também bastante com a parte de eventos, então vamos aprender como criar eventos, como publicar eventos e como separar esses eventos que acontecem em contextos distintos. 

Vamos aprender a delimitar esses contextos e utilizar um módulo compartilhado, então vários conceitos do Domain Driven Design vamos aprender neste treinamento.

**É um treinamento de introdução ao Domain Driven Design, então não vamos aprofundar e virar especialistas em Domain Driven Design.**

Mas já vamos ter uma boa base sobre o que é o Domain Driven Design e como eu coloco os conceitos do Domain Driven Design na prática, em um projeto utilizando Java.

### Domain Driven Design
Então talvez você já tenha ouvido falar sobre esse termo, Domain Driven Design.

Ele foi originado pelo Eric Evans, que é um autor, que escreveu esse livro famoso chamado Domain Driven Design - Atacando as Complexidades no Coração do Software. 

É um livro que ficou bem famoso porque ele não é um livro técnico, não é um livro que fala sobre tecnologias, sobre padrões de projeto, essa parte técnica, e sim sobre questões de domínio, de organização de código e outras coisas mais abstratas.

Ele é um livro um pouco difícil, a leitura dele, porque ele não é aquele livro bem técnico, mão na massa. 

Então, basicamente, a ideia principal desse livro, que o Eric quis passar para as pessoas, era que **você deveria desenvolver o seu software orientado ao domínio**. 

Na realidade é um negócio meio estranho, porque algo meio óbvio, todo software, ele deveria ser orientado ao domínio, o software é orientado ao domínio.

Só que, na verdade, não. 

Conforme vamos trabalhando em diversos projetos e diversas empresas, nós percebemos que o software, geralmente, ele não é focado no domínio. 

Quando damos uma olhada no código e tal, nós vemos que o domínio, ele está meio que escondido, está camuflado, ele não está muito explícito.

Então existem algumas dificuldades, que as pessoas têm, justamente nisso, em como deixar explícito os conceitos do domínio no software e geralmente o que fica explícito no código é a questão da tecnologia. 

Então no livro, ele tentou explicar isso, quais são os problemas dessa abordagem e como que você poderia mudar, começa a focar bastante no domínio da aplicação e as vantagens de você seguir esse modelo.

Mas, no geral, como é na prática? 

Quais são as conversas, o que falamos, o que discutimos e o que está explícito no software? 

O padrão MVC, para separar as camadas do projeto, para deixar o código desacoplado; requisições assíncronas; Data Access Object, o DAO; Spring Boot frameworks; Ajax com JQuery; identidade visual.

o foco já não é mais no domínio, o foco é totalmente na tecnologia, em questões de biblioteca, padrões, frameworks, separação em camadas, performance, requisitos não funcionais, enfim, o foco é totalmente na parte técnica e não no domínio.

Então, olhando para essa lista, você não sabe qual é o domínio dessa aplicação. É uma aplicação bancária? É uma aplicação hospitalar? É uma aplicação financeira? Não sei, não temos a menor ideia. Então por conta disso que o código acaba ficando cada vez mais distante do domínio da aplicação e, para sistemas complexos, isso dificulta muito a manutenção.

Dificulta muito também quando entra uma pessoa nova no projeto, ela tem essa dificuldade de entender do que se trata esse projeto. 

Ela vai conversar com alguém na área de negócios, a pessoa fala um termo, fala alguns conceitos de domínio, do negócio da aplicação. 

Essa pessoa vai procurar no código e tem dificuldade de achar aquilo no código, isso dificulta a manutenção e a evolução daquele projeto.

Então são alguns conceitos que o Erick escreveu no livro dele para tentar nos ajudar a como deixar o código de aplicação o mais próximo do domínio dela. 

**Então o principal, o mais importante, é focar no domínio**. 

Embora tenhamos a equipe técnica, dos desenvolvedores, as pessoas que vão trabalhar com o desenvolvimento do software, o foco não deveria ser na parte técnica.

Deveria ser na parte do domínio da aplicação, nos conceitos que fazem parte do domínio da aplicação, assim estamos entendendo bem quais são esses conceitos do domínio e se conseguimos levar eles para o software da maneira mais correta possível. 

#### Linguagem Ubíqua
Linguagem Ubíqua, um outro conceito muito importante no DDD.

Quando vamos desenvolver um software, temos dois perfis, digamos assim. 

Você tem a equipe de desenvolvimento, que é um perfil técnico, e você tem a equipe de especialistas do domínio da aplicação, as pessoas que estão nos passando as informações, nos dizendo quais são as funcionalidades, quais são os conceitos que deveriam ter no sistema, como deveriam funcionar as coisas.

E temos que ter uma linguagem comum, uma linguagem que esses dois mundos consigam conversar e consigam se entender. 

Então esse é um conceito que ele chamou de linguagem Ubíqua, de uma linguagem que é universal, comum e geralmente focada no domínio da aplicação. 

Então nós vamos discutir e ver como isso funciona, o que é esse negócio de linguagem Ubíqua.

#### Contexto delimitado
Contexto delimitado, um outro conceito também importante que precisamos ter claro na nossa cabeça. 

Quando vamos desenvolver um software, temos vários conceitos do domínio, por exemplo, o caso aqui da aplicação hospitalar, você tem um conceito de um paciente. 

Só que dependendo do sistema, dependendo do contexto, paciente pode significar uma coisa diferente.

Então, em um determinado sistema, paciente tem determinadas informações, mas, em outro sistema, o sistema financeiro, por exemplo, que trabalha a parte de pagamentos, pode ser que em algumas questões de laudos, de exame, não faça sentido. 

Eu estou preocupado com CPF, com o que foi gasto, o orçamento e tal. 

Então, dependendo do contexto, uma informação, ela pode mudar, ela pode ter outras características.

#### Blocos de construçao
Então precisamos conseguir delimitar esses contextos e esse é um outro conceito que temos no Domain Driven Design. Blocos de construção, também existe a parte técnica ali no DDD, ele é um livro bem abstrato, fala de várias coisas de domínio, algumas coisas complicadas de entender. 

Só que ele tem um pouco ali, uma pincelada da parte técnica, que não é o foco, não é o mais importante.

Mas o livro tem uma parte onde ele te dá algumas dicas de como que eu vou levar para o código, esses conceitos o domínio, então ele cita alguns padrões como entidade, service, factory, repository, separação em camadas. 

Então tem uma parte técnica, que é chamada de blocos de construção, porque isso não é a coisa mais importante.

A coisa mais importante é conseguirmos entender o domínio e conseguir modelar esse domínio do nosso software no código da aplicação. 

Mas também tem os blocos de construção para conseguirmos saber como representar esses conceitos do domínio dentro do código da aplicação.

Então aqui são alguns dos principais conceitos do Domain Driven Design que vamos abordar no curso e ver como isso pode se encaixar na prática, quando vamos trabalhar no projeto, como levamos esses conceitos meio abstratos e colocamos isso na prática, em um projeto. 

esses são os principais conceitos do Domain Driven Design

### explicando o projeto
hoje eu quero mostrar para vocês o projeto que vamos trabalhar aqui, passar por ele, explicar algumas funcionalidades e alguns conceitos relacionados ao Domain Driven Design. 

Então esse curso de Domain Driven Design, na verdade, ele é uma continuação do curso de Clean Architecture, faz parte da nossa formação de arquitetura Java.

No curso de Clean Architecture, nós chegamos a desenvolver um projeto, que é um projeto que representa como se fosse a Alura, que é uma escola que tem matrículas de alunos, indicações e coisas do gênero. 

Então no curso de Clean Architecture, nós criamos esse projeto, que é o projeto da escola, então criamos ele como sendo uma aplicação do Maven e implementamos algumas funcionalidades.

ele foi orientado ao domínio, então indiretamente algumas coisas do Domain Driven Design já meio que acabamos implementando, por conta de focar bastante nessa parte de domínio da aplicação. 

Então só dando uma passada no projeto, para nós relembrarmos e entendermos algumas das funcionalidades.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/1.PNG)

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/2.PNG)

Então, no nosso projeto, nós tínhamos aqui, na camada "dominio", os dois principais conceitos do domínio da aplicação, que é a parte de aluno, então temos a representação do que é um aluno para esse projeto. 

O aluno tem o CPF, o nome, e-mail, uma lista de telefones, uma senha depois para fazer o login. 

Tem a parte do CPF em si, que representamos em uma classe separada, o e-mail também representamos em uma classe separada.

Nós criamos uma factory para isolar a criação do objeto aluno, deixando mais simples para quem for, dentro do domínio da aplicação, fazer a criação, a instância do objeto aluno. 

A questão do telefone também, com validações do DDD, do número, enfim. 

E a parte de indicação, que um aluno, ele pode indicar um outro aluno, então temos o aluno que indicou, o aluno indicado na verdade, o aluno que indicou, quando que essa indicação ocorreu.

E, dentro da parte de infraestrutura, também precisamos criar algumas coisas, porque na parte de domínio tem só os conceitos de domínio, não tem independência com infraestrutura e com detalhes de bibliotecas, frameworks, nem nada do gênero, então ficou totalmente isolado. 

Fizemos, por exemplo, a parte de gerar uma senha usando um algoritmo de MD5, gerar o hash da senha com MD5.

como precisa ter esse hash, essa funcionalidade gerar hash de senha, isso já não faz parte do domínio, então deixamos parado na camada de infraestrutura. 

Outra coisa legal que também fizemos é que na camada de domínio, nós criamos uma interface que representa o repositório, que é aquele conceito de persistência do mundo dos negócios.

O pessoal que faz parte da área de negócios da empresa, eles talvez não saibam o que é banco de dados, mas eles têm essa noção de persistência, de armazenar e recuperar informações de algum lugar, então esse conceito é chamado de repositório. 

Nós preferimos representá-lo na aplicação como sendo uma interface e a implementação dessa interface, desse repositório, ela está na camada de infraestrutura.

Nós fizemos um repositório usando JDBC e tem o código aqui do JDBC. 

E fizemos também um repositório em Memoria, utilizando uma lista aqui em Memoria, e vimos que foi fácil de escrever testes automatizados para essa implementação do repositório. 

Então aqui são algumas das funcionalidades do código, que implementamos nessa aplicação que foi desenvolvida no curso de Clean Architecture, que é um curso que é requisito desse treinamento de Domain Driven Design.

Perceba o seguinte, como nesse outro treinamento nós focamos bastante na parte de arquitetura e de domínio da aplicação, indiretamente já acabamos adotando e seguindo vários dos conceitos do Domain Driven Design. 

Então, por exemplo, **o foco foi totalmente na parte de dominío da aplicação**, então, inclusive, temos esse pacote "dominio" e dentro dele temos esses termos: aluno, e-mail, CPF, telefone, indicação.

Então são conceitos do domínio, nós batemos o olho aqui, nós sabemos: isso aqui tem a ver com alguma escola, com alguma coisa de sistema de matrícula, ou algo do gênero. 

Então os conceitos do domínio estão bem explícitos, o foco é totalmente na parte do domínio da aplicação. 

Outra coisa importante também, acabamos separando a nossa aplicação em camadas.

No livro de Domain Driven Design, o Eric Evans, ele também cita isso, que importa fazermos essa separação em camadas, para tentar isolar o código do domínio da aplicação. 

No livro dele, ele sugere essa separação em camadas, então são as camadas do DDD.

temos a camada que representa a interface gráfica, a interface com o usuário, que vai disparar as requisições, digamos assim, para interagir com a nossa aplicação. 

Temos uma camada de aplicação, que recebe essas requisições do usuário e faz o fluxo de execução das nossas lógicas. 

Vamos para a camada de domínio, a camada mais importante, a principal.

E tem por baixo a camada de infraestrutura, que vai dar suporte para essa camada de domínio. 

No livro do DDD, ele tem essa separação em camadas. 

Só que no nosso projeto, nós fizemos uma diferença, **não seguimos o modelo do Domain Driven Design**, seguimos o modelo do **Clean Architecture**, e criamos uma arquitetura baseada no Clean Architecture.

Então uma diferença que tivemos é que no Domain Driven Design, a camada de domínio, perceba, ela pode chamar a camada de infraestrutura, então as camadas de cima podem chamar as camadas de baixo no modelo do Domain Driven Design. 

Só que no nosso projeto, que foi desenvolvido no curso de Clean Architecture, não fizemos dessa maneira, porque desse jeito a camada de domínio, ela ia conhecer detalhes de infraestrutura.

Então nós preferimos usar outra abordagem, preferimos fazer aquela inversão de dependências, onde a camada de domínio, ela fica totalmente isolada do resto do projeto. 

Então, a camada de domínio não tem acesso à camada de infraestrutura. 

Aqui, no "RepositórioDeAlunos", é um exemplo do repositório que eu acabei de falar.

Então, na camada de domínio, temos uma interface, sempre que precisávamos de coisas de infraestrutura nós criávamos uma interface e a implementação dessa interface, aí sim essa implementação está na camada de infraestrutura. 

**Então a camada de domínio, ela só tem o conceito; a interface.**

**A implementação em si, o como isso será feito, não está mais na camada de domínio, isso não faz parte da camada de domínio e sim da camada de infraestrutura.** 

Então essa foi uma diferença que fizemos em relação às camadas do Domain Driven Design. 

Então separação em camadas é algo recomendado no Domain Driven Design, é algo que já fizemos com uma sutil diferença em relação a esse modelo sugerido no Domain Driven Design.

Outra coisa também, era aquele conceito de **building blocks**, os blocos de construção. 

É uma parte mais técnica do Domain Driven Design, do livro, e ele sugere como podemos representar os conceitos do domínio da nossa aplicação, na hora de escrever o código.

Então ele sugere alguns padrões para seguirmos na hora de escrever o código que vai representar os conceitos do domínio. 

Isso nós também já acabamos fazendo, por exemplo, na classe "Aluno". 

A nossa classe "Aluno", ela é uma entidade, e é um dos building blocks do Domain Driven Design. 

O que é uma entidade, só para relembrar?

É aquela classe que tem alguma informação que diferencia ela de outra entidade. 

Então o Aluno, por exemplo, eu tenho dois alunos. 

Se os dois alunos tiverem o mesmo nome, eu estou falando do mesmo aluno? 

Nós vimos que não, às vezes você tem duas alunas chamadas Fernanda.

Eu tenho duas alunas chamadas Fernanda, mas uma não é exatamente a outra, então existe uma característica que diferencia uma da outra, que diferencia um aluno do outro. 

Aqui, no nosso caso, foi o CPF, então criamos o CPF. 

**Se dois alunos têm o mesmo nome, tem o mesmo e-mail, o mesmo telefone, mas o CPF é distinto, eu não estou mais falando do mesmo aluno, estou falando de alunos distintos**.

Então entidade é esse conceito que diferencia dois objetos. 

Já a classe CPF, por exemplo, não representamos como string, nós criamos uma classe. 

CPF, nós criamos essa classe "CPF", mas **CPF só tem um atributo número aqui, então CPF não é uma entidade**. 

**Se eu tiver dois CPF com o mesmo número, é o mesmo CPF que eu estou falando.**

Então CPF não é uma entidade, eu não tenho uma informação que diferencie um CPF do outro. 

#### Padrao Value Object (VO)
Aqui, no caso, a classe "CPF", a classe "Telefone" também segue esse conceito, e a classe "Email", todas elas não são entidades e sim VOs, **value object**, que é um outro padrão recomendado no livro de Domain Driven Design.

Então sempre que tivermos conceitos do domínio que o que interessa são os valores em si, não há uma diferença, não há uma propriedade, não há uma característica que diferencie um objeto do outro, então estamos falando do mesmo objeto, aí entra o padrão value object. 

Então acabamos seguindo esse padrão no nosso código.

#### Padrao Repository
O próprio repositório é um padrão forte que existe no Domain Driven Design. 

É a maneira que nós temos de representar esse conceito de persistência, que é algo técnico, só que aqui não estamos falando de banco de dados, nem de coisas de infraestrutura, estamos falando de um conceito do domínio, que é persistência. 

Como eu represento esse conceito de persistência, de guardar e recuperar informações de algum lugar?

O Evans, ele citou esse conceito de repositório. 

Quando falamos de persistência, quando algum especialista do domínio está falando de guardar e buscar informações, ele está indiretamente falando de um repositório, que é um local onde armazenamos e recuperamos informações. 

Então, no nosso projeto, nós também implementamos esse padrão repository usando essas interfaces, cujas implementações estão na camada de domínio.

É o mesmo projeto que desenvolvemos no curso de Clean Architecture da Alura e, indiretamente, como aquele curso focava bastante na arquitetura, no domínio da aplicação, já acabamos implementando vários conceitos do Domain Driven Design e isso vai facilitar bastante nesse treinamento.

Então conceitos de **entidade**, de **VO**, de **service** também, de **repositório**, vários desses conceitos nós já implementamos no nosso projeto porque o foco era justamente no domínio, então isso vai facilitar muito esse treinamento, não vamos ter que fazer toda uma reestruturação do nosso projeto, acabamos facilitando muito nesse treinamento também. 

E a separação em camadas, que é um conceito importante

Só que fizemos de uma maneira diferente, para não acoplar o domínio da aplicação com detalhes de infraestrutura, com bibliotecas e frameworks.

### Para saber mais: Building blocks
Os conceitos já aprendidos no curso de clean architecture são diretamente relacionados com o estudo de Domain Driven Design.

Muito do que aprendemos no curso anterior é o que chamamos de **Building blocks** ou Blocos de construção.

* Entity
* Value Object
* Repository
* Factory
* Service
  
Todos esses padrões são descritos no estudo sobre DDD e com isso já temos um belo ponto de partida.

Vale ressaltar que o termo Domain Driven Design significa literalmente modelar nosso software nos orientando pelo domínio do negócio.

### Linguagem Ubíqua (ubiquous language)
Esse é um dos conceitos que tem no Domain Driven Design. 

A ideia é justamente tentarmos criar **uma linguagem universal, uma linguagem onipresente, que vai ser a ponte entre esses dois mundos, esses dois times: o time de desenvolvimento, as pessoas técnicas da área de desenvolvimento, que vão desenvolver o software em si; e o time, as pessoas da área de negócio, que são os especialistas do domínio**.

Quando esses dois conjuntos de pessoas vão conversar, eles tem que usar uma linguagem em comum, que deve ser a linguagem do domínio da aplicação. 

Então não deveria haver uma reunião onde se falam termos técnicos, o time de desenvolvimento só conversar utilizando termos técnicos, porque o time de negócios não vai entender nada, ele não vai entender sobre padrões de projeto, sobre framework e bibliotecas.

Eles conhecem sobre o domínio, eles são os especialistas do domínio da aplicação. 

Então isso é importante. 

Mas não apenas durante essa comunicação, essa conversa, essas reuniões entre esses dois times, mas principalmente no código. 

Quando vamos escrever o código da aplicação, temos que ter no código esses termos, os mesmos termos que são conversados nas reuniões, os termos que são falados pelos especialistas do domínio.

Por exemplo na classe de "Indicação", esse é um conceito importante do domínio da nossa aplicação, o conceito de indicação. 

Então eles, os especialistas, as pessoas que são experts no domínio da aplicação, eles citam esses termos: indicação, o aluno pode indicar outro aluno, vamos ter uma indicação, precisamos cadastrar essa indicação no projeto, no software, no sistema.

Então, indicação É um termo importante, é um termo que faz parte do domínio da aplicação, temos que ter esse nome, de alguma maneira, no código da aplicação. 

Nós fizemos isso criando uma classe. 

Então, sempre que alguém falar sobre indicação, já sabemos que eu tenho a classe que representa esse conceito do domínio.

Aqui de novo, na classe Indicacao, outro exemplo bem interessante sobre isso: quando fazemos uma indicação, existem dois alunos, o aluno que está indicando e o aluno que está recebendo indicação. 

Mas como é chamado cada um desses alunos? 

Não é aluno 1 e aluno 2. 

No nosso caso, chamamos de Aluno indicado e Aluno indicante.

Então o aluno indicado é quem foi indicado, quem recebeu a indicação e o aluno indicante é quem está fazendo a indicação. 

Então esse nome, indicante, que talvez não seja um nome meio comum - talvez você até poderia até pensar: será que não tem o nome melhor, em vez de indicante não poderia ser indicador? 

Talvez, se esse for o termo utilizado pelos especialistas de domínio.

Mas aí que é está, não é esse o termo correto. 

Quando foi conversado, foi discutido sobre a funcionalidade, os especialistas do domínio da aplicação, eles citaram: indicante, em uma indicação você tem o indicante e tem o indicado. 

Então, percebe: indicante. 

É assim que eles chamam, eles não falam a palavra indicador.

Então se colocamos o nome indicador no código, estamos fazendo algo errado, algo estranho, temos um conflito gerando um atrito de comunicação, porque quando alguém da equipe técnica for conversar com um especialista de domínio, provavelmente essa pessoa vai falar indicador e o especialista de domínio vai ter que ficar fazendo essa tradução: indicador? Você está falando do indicante? Isso gera um atrito de comunicação.

Pode parecer que é besteira, que é um detalhe inútil, mas isso faz bastante diferença. 

Então, principalmente no futuro, quando entrarem novas pessoas da área técnica para dar manutenção no sistema, sempre que eles forem ler o código, eles vão encontrar facilmente esses termos.

E quando eles forem tirar dúvidas com os especialistas do domínio, vai facilitar muito a comunicação, vai evitar esses atritos e essas dificuldades de comunicação. 

Isso faz muita diferença, favorece muito, principalmente quando o software começa a ficar muito grande, muito complexo.

Outro exemplo aqui, nós fizemos na camada de "aplicacao", então temos aqueles use cases que nós seguimos do Clean Architecture, por exemplo, MatricularAluno, isso representa uma ação, uma funcionalidade, nós representamos exatamente isso. 

#### Use cases
Os especialistas de domínio falam: precisamos ter uma funcionalidade para matricular um aluno.

E nós criamos uma funcionalidade que representa essa ação, esse use case, esse caso de uso: matricular aluno. 

As regras do fluxo estão aqui: recebemos os dados, recebemos o repositório e mandamos cadastrar um aluno, mandamos puxar aqueles dados que vem de maneira abstrata, não vem vinculados a um framework, a um mundo web, e sim vem representado neste Dto.

Nós representamos um objeto aluno e chamamos o repositório para fazer a matrícula desse aluno. 

Então temos esse conceito, temos esse termo de matricular aluno. 

**Isso que é a linguagem ubíqua, é você sempre conversar utilizando os termos do domínio da aplicação.**

Lembre que isso é importante, isso é o foco no DDD, o domínio da aplicação. 

**Então nós precisamos pegar os termos que são usados pelos especialistas de negócio e tentar trazê-los para o código do jeito mais fiel possível, os mesmos termos que são falados durante as reuniões, durante as conversas.**

Quando alguém vai tirar dúvidas com os especialista de negócio, aqueles mesmos termos tem que estar aqui, o mais fácil, o mais explícito possível no código, para que consigamos entender, consigamos achar rapidamente aquela funcionalidade, para não perdermos muito tempo tentando encontrar: eu preciso fazer um ajuste na funcionalidade de relatório de faturamento, onde está esse tal de relatório de faturamento? Eu não estou achando isso.

Então se usamos outros nomes, se escondemos isso em detalhes técnicos, dificulta muito, faz com que demoremos mais para dar manutenção e podemos até escrever código duplicado. Podemos até introduzir bugs por conta de mal entendimento. 

Essa é a essência do Domain Driven Design, nós sempre conversarmos focados no domínio da aplicação, então temos essa linguagem ubíqua, essa linguagem onipresente focada no domínio da aplicação.

Então sempre tentamos criar essa linguagem padrão, essa linguagem universal e deixar sempre a comunicação o mais focada no negócio e o menos focada na parte técnica quando estávamos escrevendo as funcionalidades. 

Então isso é a tal da linguagem ubíqua, que é um nome meio esquisito, meio diferente, mas é justamente isso: ter uma linguagem neutra, uma linguagem comum, para conseguirmos exatamente fazer o que está nessa figura.

Que consigamos unir esses dois mundos, dos especialistas técnicos e dos especialistas de negócio, que esses dois times consigam conversar de uma maneira tranquila, sem atrito e sem dificuldades. 

E que nós, o time técnico, consigamos pegar esses conceitos e levá-los o mais real possível para o código da aplicação para facilitar a manutenção e a evolução do projeto.

Então isso é a linguagem ubíqua. 

### O que aprendemos?
* Aprendemos que os estudos de Clean Architecture e DDD geralmente se complementam;

* Entendemos o que é DDD;

* Vimos que diversos conceitos de DDD já foram aprendidos no curso de Clean Architecture;

* Conhecemos o termo Linguagem Ubíqua que consiste em ter uma linguagem onipresente entre a equipe de desenvolvimento e a equipe de negócios.

## Aggregates
### Relação Aluno-Telefone
precisamos fazer uma mudança, uma alteração no código da nossa aplicação. 

Depois de conversar com o pessoal da área de negócios, as pessoas que cuidam dessa parte, que são os especialistas de domínio, elas explicaram que precisava fazer uma mudança na aplicação: a parte de cadastro de telefones do aluno.

Eu não sei se vocês lembram, mas ela na classe Aluno, Nós tínhamos uma lista de telefones. 

Então um aluno, além de ter um nome, um CPF e um e-mail, ele poderia ter vários telefones. 

Nós representamos isso com a classe Telefone, que era um value object.

```
public void adicionarTelefone(String ddd, String numero) {
		this.telefones.add(new Telefone(ddd, numero));
	}
```

Para adicionar um telefone, vincular um novo telefone ao aluno, criamos esse método, o método adicionarTelefone. 

Esse método, ele é chamado a partir da própria classe Aluno e ele é quem recebe o DDD e o número, transforma em um objeto telefone e adiciona na lista de telefones do aluno.

Então criamos esse método para criar essa regra, para criar essa lógica e deixar ela encapsulada dentro da própria classe Aluno. 

É aqui que teremos que fazer uma mudança, porque o pessoal da área de negócios explicou que estava tendo um cadastro muito excessivo de telefones, tinham alunos com 10, 20, 30 telefones.

Então é alguma coisa meio estranha e isso estava atrapalhando, então quando precisavam entrar em contato com o aluno, não sabiam qual era o telefone correto, qual era o telefone apropriado para entrar em contato com o aluno. 

Então eles nos passaram a seguinte regra: um aluno pode ter no máximo dois telefones.

Precisamos criar essa validação, essa regra, isso se tornou uma regra de negócio. 

Podemos fazer essa regra aqui mesmo, no próprio método adicionarTelefone, é aqui que estamos pegando os dados do telefone e adicionando na lista.

Então aqui, acima do this.telefones, vamos fazer de um jeito bem simples, fazer um if, então if (telefones.size() ) - eu vou verificar o size da lista, se ele já for igual a 2, significa que ele já atingiu o limite de dois telefones, ele não pode adicionar um terceiro telefone. 

Se ele entrar nesse if (telefone.size() == 2), nós jogamos uma exception.

```
public void adicionarTelefone(String ddd, String numero) {
		if(this.telefones.size() == 2) {
			throw new ExcessoTelefonesCadastradosException("Numero maximo de telefones já atingidos");
		}
		this.telefones.add(new Telefone(ddd, numero));
	}
```

Aqui, dentro dos parênteses da exception, eu vou passar uma string que vai ser a mensagem ("Numero maximo de telefones ja atingido!").

Inclusive essa mensagem, quem vai passar é o pessoal da área de negócios, eles conhecem as regras do sistema, eles são as melhores pessoas para dizer qual é a melhor mensagem a ser exibida para o usuário do sistema quando essa situação acontecer. 

#### Invariantes (Business Invariant) - Regras de negócio
Isso, no mundo do Domain Driven Design, é chamado de invariante, **business invariant**, é o nome técnico utilizado no mundo do Domain Driven Design, invariante de negócio. 

É uma validação, uma regra de negócio. 

Então acabamos de aplicar mais um conceito do DDD, do Domain Driven Design, que são validações, regras de negócio.

**Essas validações devem estar no objeto em que fazem sentido**. 

Aqui no caso, colocamos a validação dentro da própria classe Aluno porque é uma regra que tem a ver com o aluno, já que ele é que mantém a lista de telefones. 

Então nada mais justo, nada mais apropriado do que colocar essa invariante dentro da própria classe Aluno.

### Protegendo o acesso
A discussão de hoje, que eu quero fazer com vocês, é justamente sobre isso, sobre essa questão, que no DDD acontece muito isso, quando vamos modelar as classes de domínio, é comum termos uma **entidade**, como no nosso caso é a classe Aluno, e ela é composta por vários objetos, que são os **value objects**.

#### Value Objects
Então, por exemplo, a classe CPF, a classe Email, e lista de telefone, são value objects. 

Isso é bem comum, você ter uma entidade que tem um conjunto de vários value objects. 

#### Aggregate Root
Quanto temos essa situação, essa classe, no mundo do DDD, é chamada de aggregate root. 

Você tem um aggregate, que é uma classe que tem uma coleção.

E a classe que mantém essa coleção de objetos relacionados, ela é chamada de **aggregate root**. 

No nosso exemplo, a classe Aluno é o aggregate root. 

Ela que mantém, ela que é a raiz dessas coleções, desses agregados. 

Isso é interessante porque quando temos essa situação, temos que tomar um cuidado, igual fizemos com a lista de telefones.

**Então o telefone, ele não vive sozinho, o telefone, ele depende de um aluno.** 

Não faz sentido eu ter um telefone sem ter um aluno. 

Então todo o acesso, **toda a manipulação dessa coleção de telefones, ela deveria ser feita diretamente de dentro da classe Aluno**, porque a classe Aluno, ela que é o root, ela que é a raiz dessa agregação.

Então, para evitar inconsistências no projeto, para evitar que de fora da classe aluno alguém adicione um terceiro telefone, adicione um telefone com validação errada, enfim, todo o acesso, toda a manipulação aos telefones deveria ser feita de dentro da classe Aluno. 

Isso é algo importante para você seguir.

Sempre que você tiver essa situação, um aggregate, **é importante que você garanta, tenha essa consciência de que todo o acesso à manipulação das informações, principalmente value objects que estiverem dentro da classe Aluno, sejam feitas de dentro da própria classe aggregate root e você consegue garantir uma consistência**, isso é importante.

Então temos que tomar cuidado, principalmente com métodos getters. 

No caso do Mundo Java, costumamos adicionar um método get, mas temos que tomar cuidado, porque pode ser que alguém acesse esse método get, pegue essa lista de telefones de fora da classe Aluno e manipule. 

Isso pode gerar uma inconsistência.

Então tomar muito cuidado com esse tipo de situação, para garantir a consistência e garantir que a sua invariante, que foi essa regra de negócio que implementamos, está funcionando corretamente e não está gerando nenhum ponto de inconsistência na aplicação. 

Eu queria discutir sobre essa questão de aggregate, aggregate root, que são dois conceitos do Domain Driven Design, e dar essa dica para vocês, de sempre observar quando você tem essa situação, de ter uma classe que está funcionando como um aggregate root para que ela mantenha e faça todo um controle de acesso às informações, aos value objects principalmente, para evitar inconsistências.

Porque assim você garante que as validações estão aqui dentro da classe e esse código não estará duplicado em outros pontos da aplicação. 

Além de garantir a consistência, você evita a duplicação de código. 

### DDD_Aggregate (https://martinfowler.com/bliki/DDD_Aggregate.html)
Aggregate is a pattern in Domain-Driven Design. A DDD aggregate is a cluster of domain objects that can be treated as a single unit. An example may be an order and its line-items, these will be separate objects, but it's useful to treat the order (together with its line items) as a single aggregate.

An aggregate will have one of its component objects be the aggregate root. Any references from outside the aggregate should only go to the aggregate root. The root can thus ensure the integrity of the aggregate as a whole.

Aggregates are the basic element of transfer of data storage - you request to load or save whole aggregates. Transactions should not cross aggregate boundaries.

DDD Aggregates are sometimes confused with collection classes (lists, maps, etc). DDD aggregates are domain concepts (order, clinic visit, playlist), while collections are generic. An aggregate will often contain mutliple collections, together with simple fields. The term "aggregate" is a common one, and is used in various different contexts (e.g. UML), in which case it does not refer to the same concept as a DDD aggregate.

### Persistência de Aggregates
No ultimo item, nós discutimos sobre essa questão da classe Aluno ser um aggregate root e sobre esse conceito do **aggregate** e do **aggregate root** do Domain Driven Design. 

Só reforçando, a ideia é você sempre observar quando você tiver essa situação de uma classe agindo como um aggregate root.

Geralmente é uma **entidade** que possui diversos **value objects** para garantir a consistência, fazer todo o acesso e as manipulações das informações de dentro do próprio aggregate root. 

No nosso caso foi a classe Aluno com relação à lista de telefones, porque tem uma regra de negócio, tem uma invariante aqui, que é validar, não permitir que o aluno tenha mais do que dois telefones. 

Então faça todo esse acesso por aqui, dentro da classe Aluno.

Uma outra coisa, que nesse vídeo eu quero discutir com vocês, que talvez vocês tenham se perguntado é sobre a questão da persistência. 

Como é que fica a questão da persistência quando eu for salvar um aluno no banco de dados, por exemplo? 

E os telefones? 

Gravo junto, gravo separado? 

Enfim, isso também é uma coisa importante. 

Só que já fugimos um pouco do conceito de DDD, já estamos indo para a parte de infraestrutura.

Em relação à persistência, a dica é você também seguir a mesma ideia: 

**faça todo o acesso pelo aggregate root**. 

Não é interessante você salvar um telefone por fora, então na hora que você for gravar um aluno, já grava junto o telefone se isso for possível, se tem uma tela no sistema em que todas as informações serão gravadas de uma vez.

Aqui, por exemplo, lembra que temos um RepositorioDeAlunos? 

Aqui nós implementamos no próprio método matricular(), quando matriculamos um aluno, já gravamos o telefone. 

Mas poderíamos ter aqui mesmo, no próprio RepositorioDeAlunos, um método para gravar um telefone.

Talvez não fosse interessante ter um repositório de telefones, inclusive não temos aqui, porque isso poderia permitir que alguém gravasse, alterasse, excluísse um telefone diretamente, por fora da classe Aluno e isso pode gerar uma inconsistência. Então acabamos gravando o telefone, no nosso caso, no RepositorioDeAlunos mesmo.

Nós implementamos esse RepositorioDeAlunosComJDBC e o método matricular, tínhamos o insert do aluno e, na sequência, percorríamos a lista de telefones e já fazia um insert diretamente, com o telefone. 

Foi um exemplo, o próprio RepositorioDeAlunos já persistimos também os telefones.

Então é um exemplo de como manter a consistência na camada de persistência, já pensando na camada de infraestrutura da aplicação, o que já foge um pouco do escopo do DDD. 

Só que aqui, a única questão que você terá que se preocupar é com relação a outros conceitos de infraestrutura, por exemplo a questão de transação.

Se der problema na hora de inserir o telefone, tem que fazer um rollBack, mas tem que fazer o rollBack do insert do aluno, **então você tem que garantir que tem uma única transação acontecendo aqui**. 

Até tínhamos usado o conceito de inversão de dependências, não estamos abrindo uma transação direto aqui no código.

Nós recebemos uma connection no construtor do RepositorioDeAlunosComJDBC, então é tomar cuidado para quem está passando essa connection, já ter aberta uma transação, fazer um try catch, um rollBack no caso de acontecer alguma exception, enfim, resumindo: a mesma preocupação que você tem com a consistência do telefone do aluno, você também tem em garantir a consistência da persistência do aluno e do telefone.

Então talvez não fosse interessante você ter um repositório de telefones, para evitar que alguém salve um telefone por fora, quebrando, talvez, a consistência. Mas nem sempre, às vezes pode ser interessante você ter um repositório de telefones para melhorar o código. Então, por exemplo, aqui, invés de ter esse sql = "INSERT INTO TELEFONE VALUES(?, ?)";, dentro do RepositorioDeAluno, nós poderíamos extrair isso para um repositório de telefones.

E, de dentro do RepositorioDeAlunos, poderíamos chamar o repositório de telefones. 

É um exemplo, seria para organizar o código. 

Então às vezes faz sentido você ter um repositório de telefones, se o objetivo é a organização de código. 

É só tomar cuidado para que esse repositório de telefones não seja chamado diretamente por fora, porque você pode ter uma inconsistência.

Ou você toma um cuidado sempre que mexer no telefone, ou em qualquer aggregate, qualquer value object, de dentro do Aluno, toma cuidado com a questão da consistência, seja em relação às regras de negócio, seja em relação à persistência também. 

Então a dica é essa. 

## Eventos
### Eventos de Domínio
Continuando os nossos estudos, até o momento já vimos vários dos blocos de construção do Domain Driven Design, como **entidades**, **value objects**, **service**, dentre outros conceitos. 

hoje, vamos discutir sobre um outro conceito que é muito importante, bastante útil e pode ajudar bastante na hora de escrever algumas funcionalidades da nossa aplicação, deixando o código bastante simples e desacoplado: a questão dos eventos.

No caso do Java, não é tão comum trabalharmos com essa questão de eventos e tal, diferente do que acontece quando você trabalha, por exemplo, com JavaScript, que tudo é orientada a eventos: evento de clique do mouse, evento de mouse over, enfim, é uma programação totalmente orientada a eventos. 

Mas no Java não é tão comum. 

Vamos entender como funciona esse conceito.

No nosso caso, um evento nada mais é do que algo que aconteceu e que tem alguma coisa que precisa ser disparada, algum processamento que precisa ser disparado, precisa ser notificado quando esse evento ocorre e precisa fazer algum tratamento. 

Pode ser um ou mais ouvintes desse evento que estão interessados em saber quando ele ocorreu para disparar alguma atividade.

Então é muito comum isso quando temos um processo, por exemplo, um site de compras online, você pode ter um processo de uma venda. 

Então, sempre que um pedido é efetuado, você pode disparar um evento e várias ações são tomadas: uma emissão de nota fiscal, o processamento do pagamento, envio de e-mail de confirmação da compra, enfim, então essas são atividades que são disparadas sempre que o evento de um pedido novo é realizado.

No nosso caso, isso também pode acontecer. 

Na camada de "aplicacao", tínhamos esse use case, o caso de uso de matricular um aluno. 

Então aqui está o fluxo de execução de MatricularAluno, de quando alguém, que utiliza o sistema, efetua a matrícula de um novo aluno.

Então temos todo o fluxo de buscar os dados que foram preenchidos e chamar o repositório para gravar. 

Esse código, se pararmos para pensar, o MatricularAluno é um evento que acontece no sistema, é o evento de um aluno matriculado. 

Então, sempre que um aluno é matriculado, um evento é disparado. 

Nós poderíamos implementar dessa maneira: disparar o evento de aluno matriculado.

E podemos ter vários ouvintes, que vão disparar alguma ação sempre que um aluno é matriculado. 

Pode ter um ouvinte, por exemplo, que gere um log para deixar registrado que um aluno foi matriculado na data tal. 

Pode ter um evento que envie um e-mail para o aluno, para um coordenador, ou algo do gênero. 

Pode ter um evento que faz alguma lógica, algum tratamento, algum cadastro daquele aluno, enfim.

Podemos nos beneficiar nessa questão de eventos e isso favorece muito a deixar o código bem desacoplado. 

Então vamos implementar esse conceito de eventos baseado nessa funcionalidade MatricularAluno, justamente esse é o evento que nós temos, o evento de um aluno matriculado. 

Esse pode ser o nome da nossa classe que vai registrar isso. 

Como tem a ver com o domínio da aplicação, eu vou colocar na camada de "dominio".

E como está relacionado com aluno, vai ficar no pacote "dominio > aluno", então eu vou criar uma classe chamada "AlunoMatriculado". 

Ela representa justamente o evento de um aluno matriculado, então sempre que um aluno for matriculado no projeto, vamos instanciar e chamar essa classe, depois vamos ver como é que funciona.

Então o que acontece nessa classe AlunoMatriculado? 

independente do evento, não só para o AlunoMatriculado, mas para qualquer evento que aconteça no sistema, uma coisa que precisamos saber é a data, quando que aquele evento ocorreu.

Como isso não é algo específico do evento de AlunoMatriculado, eu vou criar uma outra classe, que vai ser uma classe genérica, que representa todos os eventos.

Essa classe vai ter todas as coisas comuns para todos os eventos. 

Então vou criar uma nova classe, na verdade uma interface, para deixar mais flexível. Vou chamar ela de "Evento".

```
public interface Evento {

    LocalDateTime momento();
}

```

Então eu tenho uma interface que representa um evento. 

Tudo o que for genérico, em relação a eventos, ficará nessa interface. 

Uma das coisas foi o que eu acabei de falar: eu preciso saber quando o evento ocorreu, então vou chamar o método LocalDateTime momento();.

Esse método vai devolver um objeto do tipo LocalDateTime. 

Então esse método momento();, ele tem que devolver um LocalDateTime, representando o momento em que esse evento, independente de qual evento for, aconteceu. 

Na nossa classe AlunoMatriculado, eu vou implementar essa interface Evento. 

Então public class AlunoMatriculado implements Evento.

```
public class AlunoMatriculado implements Evento {



    @Override
    public LocalDateTime momento() {
        return null;
    }
}
```

Então eu preciso devolver um LocalDateTime com a data, o momento em que esse evento ocorreu. 

Como é que vai funcionar? 

Focando agora no evento de AlunoMatriculado, então o que vai acontecer no caso do evento de AlunoMatriculado?

Toda vez que um aluno é matriculado, além de eu ter que registrar o momento em que isso aconteceu, que é genérico para todos os eventos, eu preciso saber alguma informação do aluno. 

Então os ouvintes, que estarão interessados nesse evento AlunoMatriculado, eles vão precisar saber que aluno foi matriculado, eles precisam de alguma maneira identificar o aluno que foi matriculado.

Eu preciso ter alguma outra informação que seja do aluno. 

No nosso sistema, nós já vimos, tem uma informação que é única do aluno, que eu consigo diferenciar um aluno do outro, que é o CPF. 

Então além da data, do momento em que o evento aconteceu, eu preciso saber o CPF do aluno, para eu conseguir receber, carregar as informações desse aluno.

Então nessa minha classe AlunoMatriculado, vão ter dois atributos aqui: private final CPF cpfDoAluno;, vou chamar de cpfDoAluno. 

Além de cpfDoAluno, private LocalDateTime momento;, que é o momento em que o evento aconteceu. 

Eu coloquei esses dois atributos como private final só para obrigar eles a serem declarados, atribuídos ou diretamente nessa classe, o que não faz sentido, ou no construtor.

```
public class AlunoMatriculado implements Evento {

    private final CPF cpfDoAluno;
    private final LocalDateTime momento;

    public AlunoMatriculado (CPF cpfDoAluno) {
        this.cpfDoAluno = cpfDoAluno;

        
    }
    @Override
    public LocalDateTime momento() {
        return null;
    }
}
```

Qual é a lógica e como vai funcionar aqui? 

Sempre que alguém for instanciar um objeto AlunoMatriculado para representar o evento de AlunoMatriculado, vai ter que passar como parâmetro o CPF do aluno. 

O momento() não será passado, o momento é o momento em que foi instanciado o objeto. 

Então, na verdade, o momento() eu vou instanciar aqui: public AlunoMatriculado(Aluno cpfDoAluno), this.cpfDoAluno = cpfDoAluno, this.momento = LocalDateTime.now;.

```
public class AlunoMatriculado implements Evento {

    private final CPF cpfDoAluno;
    private final LocalDateTime momento;

    public AlunoMatriculado (CPF cpfDoAluno) {
        this.cpfDoAluno = cpfDoAluno;
        this.momento = LocalDateTime.now();
    }

    @Override
    public LocalDateTime momento() {
        return momento;
    }
}
```

Aqui, em public LocalDateTime momento(), eu retorno return this.momento;. 

O cpfDoAluno eu também posso gerar um método getter.

Recapitulando: essa classe AlunoMatriculado, ela representa o evento de aluno matriculado. 

Quando o evento de aluno matriculado é disparado, eu preciso saber quando esse evento ocorreu - eu já peguei a data atual com o construtor this.momento = LocalDateTime.now();. 

E preciso do CPF do aluno que acabou de ser matriculado. 

Eu recebo esse CPF com o construtor this.cpfDoAluno = cpfDoAluno), ele é um argumento do construtor.

Talvez você esteja se perguntando: Rodrigo, por que não passamos, invés do CPF, o objeto aluno aqui? 

Não faria mais sentido? 

Na hora que o evento AlunoMatriculado for disparado, eu passo o aluno inteiro e dentro do Aluno eu tenho o CPF. 

Realmente faz mais sentido, eu tenho um objeto Aluno.

Porém, tem uma desvantagem em relação a isso, o problema é que vou **expor outros atributos e outras informações do aluno que não interessam para outras partes do sistema**. 

Então a única coisa que os ouvintes precisam saber é o CPF do aluno. 

É com o CPF que eles conseguem identificar o aluno.

Se eu passo, por exemplo, o objeto Aluno, um ouvinte pode chegar e adicionar um telefone, sendo que não era para ter essa funcionalidade de adicionar telefones.

Então, se eu passo o objeto inteiro, eu estou expondo informações talvez desnecessárias. 

Nesse primeiro momento, como eu não sei se vou precisar de outras informações, eu vou repassar apenas o CPF. 

Se precisar saber outras informações, alteramos esse código posteriormente, mas por enquanto, é isso que está valendo, é só o CPF. 

eu só queria mostrar para vocês isso, discutir essa questão dos eventos, que é um outro conceito importante do Domain Driven Design.

Esse conceito, **ele permite que deixemos o código desacoplado, com que tenhamos essa programação orientada a eventos**. 

Aconteceu algo no sistema, eu disparo um evento. 

Eu posso ter vários ouvintes, cada um vai executar um passo de um processo, sem um conhecer o outro, sem o código ficar amarrado, muito acoplado e muito difícil de estender.

Isso vai favorecer muito a manutenção e a extensão desse código, e isso é focado totalmente na parte do domínio. 

### Reagindo a eventos
nós só implementamos essa parte da representação do evento. 

Agora, eu quero mostrar para vocês como eu posso criar um ouvinte, como podemos representar um ouvinte, alguém que vai ouvir e reagir a esse evento de aluno matriculado.

Então vamos criar um ouvinte na camada de "dominio" no pacote de "aluno", já que ainda estamos falando de eventos de domínio relacionados com aluno. 

O ouvinte, nada mais vai ser do que uma classe, então vou ter uma outra classe que vai executar alguma ação quando aquele evento for disparado.

Só para simplificar aqui, para não quebrarmos a cabeça com coisas complexas, vai ser um ouvinte bem simples, a única coisa que ele vai fazer vai ser gerar um log quando o aluno for matriculado. 

Então eu vou criar uma classe chamada "LogDeAlunoMatriculado". Essa aqui é a ideia: teve o evento de aluno matriculado, essa classe LogDeAlunoMatriculado vai ser um ouvinte.

A única coisa que ele vai fazer é um system out, como se fosse gerar algum log para registrar que um aluno foi matriculado, é algo bem simples. 

Então nessa classe LogDeAlunoMatriculado, o que eu preciso ter? 

Eu preciso ter um método, que é um método que vai ser disparado quando o evento acontecer, então esse método vai ser tipo a reação daquele evento.

Então public void reageAo() e ele recebe como parâmetro o evento. 

Qual é o evento que essa classe está interessada? 

É o evento de public void reageAo(AlunoMatriculado evento). 

Então quando o evento for disparado, o evento de aluno matriculado, essa classe LogDeAlunoMatriculado vai ser um dos ouvintes, você pode ter um ou múltiplos ouvintes para um determinado evento, é bem flexível.

A única coisa que a classe LogDeAlunoMatriculado vai fazer, a lógica dessa classe, desse ouvinte, é só um system out, gerando um log, então eu vou gerar um System.out.println();, bem simples, só para não ter muita complexidade. 

O que eu vou fazer é só imprimir aluno com CPF tal foi matriculado na data tal, bem simples.

Então eu vou fazer o seguinte, eu vou fazer um (String.format()), para formatar. 

O texto que eu quero imprimir é esse: System.out.println(String.format("Aluno com CPF %s matriculado em: %s" ()); e eu passo a data como parâmetro.

O String.format, eu preciso passar dois parâmetros. 

Vão ser duas strings que vou passar aqui, a primeira é o CPF do aluno - Lembra que o evento tem aquele método evento.getCpfDoAluno(). 

Então no evento, no caso Esse evento AlunoMatriculado específico, as duas coisas que ele gera, as duas informações que ele gera, é o cpfDoAluno e o LocalDateTime momento, a data que ele aconteceu.

Então no evento LogDeAlunoMatriculado, justamente, eu preciso acessar essas informações: evento.getCpfDoAluno(), e o outro parâmetro que eu preciso é evento.evento.momento(). 

Só que momento, se imprimirmos assim, ele é um local date time, um local date time tem um jeito, ele não sai aquela data bonita, formatada, então eu vou criar uma string só para formatar essa data: String momentoFormatado = evento.momento().format() e eu tenho que passar um date time format, .format(DateTimeFormatter.ofPattern() e eu vou passar qual é o padrão, como eu quero que saia a data.

Dia com dois dígitos, mês com dois dígitos, ano com quatro dígitos, hora e minuto, ("dd//MM/yyyy HH:mm"). 

Então eu vou imprimir só dessa maneira, String momentoFormatado = evento.momento().format(DataTimeFormatter.ofPattern("dd//MM/yyyy HH:mm"));. 

Então no "System.out.println, o segundo parâmetro não vai ser evento.momento, vai ser momentoFormatado.

Então essa é a classe LogDeAlunoMatriculado. 

```
public class LogDeAlunoMatriculado {

    public void reageAo(AlunoMatriculado evento) {
        String momentoFormatado = evento.momento().format(DateTimeFormatter.ofPattern("dd//MM/yyyy HH:mm"));
        System.out.println(String.format("Aluno com CPF %s matriculado em %s ",
                evento.getCpfDoAluno(),
                momentoFormatado));
    }
}
```

Essa classe, ela representa um dos ouvintes do evento AlunoMatriculado. 

Quando essa classe for chamada, ela estará ouvindo o evento AlunoMatriculado e o trabalho dela é apenas gerar um log, apenas fazer um System.out.println(String.format("Aluno com CPF %s matriculado em: %s", evento.getCpfDoAluno momentoFormatado));.

Passei o CPF, passei a data formatada, morreu. 

Essa é a única lógica, é a única ação que essa classe, LogDeAlunoMatriculado vai fazer, que esse ouvinte vai fazer. 

Obviamente poderíamos ter mais classes, cada uma vai fazer uma ação. 

Um ouvinte não conhece o outro ouvinte, eles não estão amarrados, acoplados entre si, então se mudar a regra, mudar a lógica de um ouvinte, você não precisa mexer nos outros ouvintes.

Isso te permite ter uma boa flexibilidade no código. 

Se eu excluir um ouvinte ou precisar cadastrar um novo ouvinte, os outros nem vão perceber que isso aconteceu. 

O código fica mais fácil de manter e de estender, de adicionar novos comportamentos. 

### Publicando eventos
Continuando aqui então, nós já criamos a classe que representa um evento e é um ouvinte na verdade do evento. 

Já temos a classe que representa o evento genérico em si e agora falta o último passo, que é o passo de publicar o evento.

Então sempre que um aluno for matriculado no sistema, eu preciso publicar o evento AlunoMatriculado para que a classe LogDeAlunoMatriculado seja chamada e quaisquer outras futuras classes que precisam ouvir esse evento também sejam executadas. 

Vamos começar fazendo o seguinte: no pacote "dominio", vamos criar uma classe que vai ter essa responsabilidade, de publicar os eventos do sistema.

Eu vou criar essa classe e o nome dela vai ser "PublicadorDeEventos". 

A ideia dessa classe é exatamente essa: publicar os eventos que forem disparados na aplicação. 

Essa classe, ela vai ser genérica, ela publica qualquer tipo de evento, não apenas o evento AlunoMatriculado, então precisamos implementar essa classe de um jeito que ela fique genérica.

Para fazer isso, nós criamos a interface Evento, que é para representar um evento de maneira genérica, só que para o ouvinte nós não fizemos isso. 

Nós já criamos direto esse ouvinte, que é o LogDeAlunoMatriculado, só que não temos uma classe genérica que representa todos os ouvintes.

Então vamos criar também uma classe e vai se chamar "Ouvinte". 

O objetivo dessa classe é justamente representar um ouvinte de um evento, independente de qual evento que seja esse. 

Essa classe, qual vai ser o objetivo dela? 

Ela não vai ser uma interface, vamos transformar ela em uma classe abstrata, na verdade, e os ouvintes, eles vão herdar dessa classe.

Essa classe, ela vai ter que fazer o seguinte, ela vai ser a classe que vai disparar o processamento do evento. 

Então eu vou ter um método public void processa(Evento evento), que recebe o objeto do tipo evento, então não é um aluno matriculado, é um objeto o evento, é um evento genérico. 

Essa public abstract class Ouvinte, ela vai processar esse evento.

Só que como ela é uma classe genérica - lembra, nem todo ouvinte está interessado em todos os eventos, então o ouvinte, ele só está interessado em um determinado tipo de evento. 

No nosso caso, o LogDeAlunoMatriculado, ele está interessado só no evento AlunoMatriculado. 

Então nessa classe Ouvinte, nós temos que filtrar.

Eu preciso saber se é para processar esse evento ou não. 

Então eu vou colocar um if (this.deveProcessar(Evento)), passando o evento como parâmetro, e eu tenho que ter esse método .deveProcessar. 

Só que eu vou criar esse método sendo abstrato: protected abstract boolean deveProcessar(Evento evento);.

Então quem vai dizer se esse evento deve ser processado ou não, é cada classe em específico, porque cada classe é que vai ter a lógica, se ela tem ou não que processar esse evento. 

Se a classe tiver que processar, aí sim esse método reageAo será executado. 

Então dentro do if, eu vou chamar this.reageAo(evento) e eu passo o parâmetro evento.

Esse método .reageAo também não existe aqui dentro e ele também vai ser um método abstrato: protected abstract void reageAo(Evento evento);. 

Então essa classe Ouvinte, esse que será o objetivo dela: ela representa um ouvinte genérico e a única coisa que ela sabe fazer é processar um evento. 

Só que para processar, ela verifica se tem que processar. Se tiver que processar, ela chama o método .reageAo.

Como essa classe Ouvinte, ela é abstrata, eu preciso herdá-la, todas as classes ouvintes precisam herdar dessa classe Ouvinte, então vou fazer o public class LogDeAlunoMatriculado extends Ouvinte na classe LogDeAlunoMatriculado. 

Ele já está reclamando aqui, porque precisa implementar os métodos, aqueles dois métodos.

O protected void reageAo(Evento evento) e o protected boolean deveProcessar(Evento evento). 

O deveProcessar, essa classe em específico, o LogDeAlunoMatriculado, só deve processar o evento se ele for do tipo aluno matriculado, então o booleano que eu vou retornar em protected boolean deveProcessar(Evento evento) será return evento instanceof AlunoMatriculado;.

Então eu só vou processar se o evento for esse, que está no deveProcessar do ouvinte LogDeAlunoMatriculado, porque esse ouvinte só está interessado nesse evento AlunoMatriculado, então o deveProcessar só vai devolver true se for o evento AlunoMatriculado.

O reageAo já é um método que tínhamos no LogDeAlunoMatriculado, só que o método que nós tínhamos está recebendo um evento específico, que é o AlunoMatriculado, só que ele tem que receber um objeto genérico, que é o evento. 

Então vou substituir aqui por public void reageAo(Evento evento). Vou apagar esse método protected void reageAo(Evento evento).

Ele vai compilar aqui, vai funcionar certo, porém em evento.getCpfDoAluno() vai dar erro, porque esse evento, agora ele é um objeto genérico, não é um evento AlunoMatriculado, então não tem o .getCpfDoAluno(). 

Eu vou ter que fazer um casting aqui - deixa eu ver se ele sugere; ele sugeriu. 

System.out.println(String.format("Aluno com CPF %s matriculado em: %s", ((AlunoMatriculado) evento) .getCpfDoAluno(), momentoFormatado));.

É só fazer um casting e pronto. 

Eu sei que esse método reageAo só vai reagir se for um objeto do tipo AlunoMatriculado, se for um evento AlunoMatriculado, então é garantido que vai ter o método .getCpfDoAluno. 

Pronto, com isso nós criamos uma classe ouvinte que representa todos os ouvintes.

```
public abstract class Ouvinte {

    public void processa(Evento evento) {
        if(this.deveProcessar(evento)) {
            this.reageAo(evento);
        }
    }

    protected abstract boolean deveProcessar(Evento evento);

    protected abstract void reageAo(Evento evento);
}
```


O PublicadorDeEventos, ele vai trabalhar justamente com o ouvinte, de maneira genérica. 

Só que como ele pode trabalhar com mais de um ouvinte, vamos ter que representar isso em uma lista, eu vou ter que gravar, guardar todos os ouvintes no PublicadorDeEventos. 

Então eu vou criar uma lista em PublicadorDeEventos, de objetos do tipo ouvinte.

Vou fazer private List< Ouvinte > ouvintes = new ArrayList <>();, já vou instanciar aqui a lista para não ficar vazia, não ficar nula e não termos que fazer aquelas verificações de nulo. 

Esse PublicadorDeEventos, ele vai ter um método que vai publicar o evento, só que ele precisa disparar para todos os ouvintes.

Então eu preciso ter um jeito de adicionar um novo ouvinte aqui, então preciso ter um método public void adicionar(Ouvinte ouvinte), que recebe como parâmetro um objeto do tipo ouvinte. 

Então esse método é igual ao da classe aluno, lembra que eu podia ter vários telefones? 

Eu não tinha um método que adicionava um novo telefone? 

Aqui é a mesma coisa.

O PublicadorDeEventos, ele tem vários ouvintes, então eu preciso ter um método que pega a lista de ouvintes e adiciona esse novo ouvinte, que foi passado como parâmetro, this.ouvintes.add(ouvinte);. 

Ele vai adicionando, adicionando, mas o que ele faz de importante mesmo é publicar o evento.

Então eu preciso ter um outro método, que é o método principal, digamos assim, que é o método que vai publicar um determinado evento. 

O public void publicar(Evento evento) recebe um determinado evento, recebe de uma maneira genérica, eu não sei qual é o evento aqui dentro da classe, pode ser qualquer tipo de evento, e eu preciso pegar cada um dos ouvintes e chamar o método processar passando esse evento como parâmetro.

Cada ouvinte vai fazer aquele if para saber se tem que processar ou não esse evento. Então eu preciso pegar a lista de ouvintes this.ouvintes, tenho que percorrer uma por uma, this.ouvintes.forEach(o ->{, para cada ouvinte chama esse lambda aqui. 

E o que ele vai fazer? o.processa(evento);, passando como parâmetro o evento.

```
public class PublicadorDeEventos {

    private List<Ouvinte> ouvintes = new ArrayList<>();

    public void adicionar(Ouvinte ouvinte) {
        this.ouvintes.add(ouvinte);
    }

    public void publicar (Evento evento) {
        this.ouvintes.forEach(o -> {
            o.processa(evento);
        });
    }
}
```

Pronto, esse é o objetivo do PublicadorDeEventos: guardar cada um dos ouvintes e, quando o método for publicado para um determinado evento, disparar o método .processa para cada um desses ouvintes. 

Como tem ouvinte que não estará interessado nesse evento, cada ouvinte tem aquele método que vai devolver se o ouvinte processa ou não esse determinado tipo de evento.

Então pronto, já está tudo funcionando aqui, já tenho a minha classe Ouvinte que representa um ouvinte genérico, já tenho um PublicadorDeEventos, que vai guardar todos os ouvintes e publicar o evento. 

Agora onde esse PublicadorDeEventos vai ser chamado? 

Ele pode ser chamado, por exemplo, no use case, naquela nossa classe MatricularAluno.

O MatricularAluno é a classe que tem o caso de uso de matrícula de aluno, então é onde eu vou disparar, publicar o evento de aluno matriculado, porque é aqui que está acontecendo o evento AlunoMatriculado. 

O que eu preciso fazer? 

Além de pegar os dados dos alunos e guardar no banco de dados, no repositório, eu preciso publicar o evento.

Então esse nosso use case MatricularAluno, além de depender de um repositório, ele também precisa depender de um publicador de eventos, então vou ter mais um atributo em MatricularAluno, o private final PublicadorDeEventos publicador;, vou chamar de publicador.

E, para a classe use case não depender diretamente do publicador, não saber como instanciar e quais são os ouvintes, nós também vamos receber ela como parâmetro no construtor, então com public MatricularAluno(RepositorioDeAlunos repositorio, PublicadorDeEventos publicador) { this.repositorio = repositorio;}, nós aplicamos de novo aquele conceito de inversão de dependências.

Então a classe MatricularAluno, a use case, ela não sabe criar o repositório, não sabe qual é o repositório, e também não sabe quem é o publicador e quais são os ouvintes que estão associados ao publicador. Então eu inverto as dependências para deixar o código desacoplado.

Então no MatricularAluno eu simplesmente recebo o meu publicador e quando o método de executar esse use case for chamado, além de ele gravar, de chamar o repositório, eu preciso também chamar o publicador e preciso chamar o método publicador.publicar(evento);, passando um evento. 

Qual o evento que será disparado?

Como é o use case MatricularAluno, é o evento de aluno matriculado, AlunoMatriculado evento = new AlunoMatriculado();. 

Na hora de instanciar o AlunoMatriculado, eu preciso passar o CPF do aluno, então preciso passar entre os parênteses o CPF.

O CPF, eu posso receber ele dos dados do MatricularAlunoDto ou do próprio objeto aluno. 

Vou pegar do MatricularAlunoDto: (dados. get); - do MatricularAlunoDto não tem como, porque ele está encapsulado. 

Então vai ser do objeto aluno: AlunoMatriculado evento = new AlunoMatriculado(novo.getCpf());.

Porém temos um problema, o AlunoMatriculado, ele recebe como parâmetro um objeto do tipo CPF, só que em AlunoMatriculado evento = new AlunoMatriculado(novo.getCpf());, esse CPF não é um objeto CPF, é uma string, que tínhamos feito aquele método String getCpf() e devolvemos uma string.

Então eu vou alterar o String getCpf() para devolver um objeto CPF mesmo. 

Invés de devolver a string com o número do CPF, eu estou devolvendo o objeto CPF mesmo, o return cpf;. 

Pronto, o nosso use case, ele vai funcionar dessa maneira: quando ele for executado, ele vai pegar os dados do aluno, que virão de uma tela, de uma interface gráfica ou seja lá de onde estiverem vindo.

Ele vai chamar o repositório, vai criar um evento de aluno matriculado e mandar o publicador publicar esse evento. 

Então daqui, do use case, eu não sei quem são os ouvintes, se tem um ouvinte, dois, cinco, dez, e nem o que cada um desses ouvintes faz. 

Então perceba: o código está totalmente desacoplado.

Eu posso adicionar um novo ouvinte sem ter que mexer no use case, posso remover um ouvinte sem ter que mexer no use case. 

Então o código fica bastante desacoplado, fácil de manter e fácil de estender. Até conseguimos testar esse use case, testar essa funcionalidade naquela classe - tem uma classe main que fizemos, a MatricularAlunoPorLinhaDeComando.

Essa classe foi para simular uma interface com o usuário, já que não temos tela, interface com o usuário, nós criamos no método main. 

Está até dando erro de compilação, porque estamos instanciando o use case passando só o repositório e agora eu preciso passar o publicador. 

O que podemos fazer nesse main?

Eu preciso criar um publicador de eventos também: PublicadorDeEventos publicador = new PublicadorDeEventos();. 

Nesse publicador eu vou adicionar só um evento - lembra que eu tenho que chamar o publicador.adicionar(new LogDeAlunoMatriculado);, chamando um ouvinte, e eu vou passar o LogDeAlunoMatriculado, que é o único ouvinte que temos no momento.

Eu vou passar aqui o publicador, além de passar o repositório, eu passo o publicador. 

Então eu consigo testar, eu posso rodar essa classe main. 

Talvez vá dar erro de compilação, porque nós fizemos aquela alteração do CPF. Deixa eu ver se ele vai rodar. Ele rodou. Percebe? Ele rodou, matriculou um aluno e ele deu esse system out. Esse system out está no nosso ouvinte.

Então nós matriculamos o aluno, publicamos o evento e o evento foi ouvido, digamos assim, pela classe, que é o único ouvinte que nós temos, que é o LogDeAlunoMatriculado, e ele imprimiu o texto. Aqui só deu um problema por causa do to string, ele está chamando o to string porque agora o CPF é o objeto CPF, não é mais um número.

Dá para resolver isso de um jeito simples. Nós podemos abrir a classe CPF e implementar aqui o método to string. Vou colocar o Eclipse para gerar: "Source > Generate toString()..." usando o atributo número, public String toString() { return "CPF [numero" + numero + "]";}. Ele gera desse jeito, vou colocar só um return numero;. Então quando a classe CPF for impressa no system out, ele simplesmente vai devolver um número.

```
public class MatricularAluno {
	
	private final RepositorioDeAlunos repositorio;

	private final PublicadorDeEventos publicador;

	public MatricularAluno(RepositorioDeAlunos repositorio, PublicadorDeEventos publicador) {

		this.repositorio = repositorio;
		this.publicador = publicador;
	}
	
	// COMMAND
	public void executa(MatricularAlunoDto dados) {
		Aluno novo = dados.criarAluno();
		repositorio.matricular(novo);
		AlunoMatriculado evento = new AlunoMatriculado(novo.getCpf());
		publicador.publicar(evento);
	}

}
```

Vou rodar novamente aquela classe main, deixa eu rodar ela aqui. Está dando erro causa dos testes e tal, mas para o nosso objetivo está funcionando corretamente. Então eu disparei a matrícula do aluno, o caso de uso, e o ouvinte foi processado, ele recebeu aquele evento de aluno matriculado e reagiu fazendo aqui um log, que no caso é um system out.

Como desafio, eu vou deixar para você só corrigir onde estiver dando erro de compilação. Está dando erro de compilação justamente aqui, na hora de pegar o CPF do aluno. Esse é fácil, é só chamar o ps.setString(1, aluno.getCpf() toString());, pronto, matou. Agora eu vou deixar como desafio lá no teste automatizado.

No teste automatizado eu estou passando o MatricularAluno, só que eu preciso passar, além do repositório, eu preciso passar o publicador. Então eu vou deixar isso como desafio para vocês: vocês corrigirem esse teste, passar o publicador e fazer esse teste funcionar normalmente.

Então essa aula, ela foi um pouco mais extensa, mais densa, porque essa é a parte mais chata dos eventos, como fazer esse passo de publicar o evento e deixar da maneira mais genérica possível, o mais desacoplada possível. Então vou pedir para vocês assistirem com calma esse vídeo, reassistirem, implementem tudo o que vimos aqui na aula.

Faz o passo a passo, entende cada componente, cada classe que criamos e perceba que o código vai ficar totalmente desacoplado e fácil de entender. Espero que vocês tenham gostado dessa aula e com isso fechamos essa parte de eventos, nós conseguimos disparar eventos de domínio da nossa aplicação, reagir a eventos e publicar os eventos em determinados momentos.

E com um código bem flexível, para qualquer tipo de novos eventos, nós conseguimos utilizar esse publicador e reagir, fazendo a ação de cada ouvinte. Então espero que vocês tenham gostado, vejo vocês na próxima aula, que vamos continuar discutindo outros conceitos do Domain Driven Design. Um abraço e até lá.

### O que aprendemos?
* Definimos o que é um evento;

* Entendemos o que é um evento de domínio;

* Aprendemos a implementar, emitir e reagir a um evento de domínio.

## contextos delimitados (bounded contexts)
### Implementando a gamificação
Às vezes esse assunto é um pouco difícil de entender, então vou tentar simplificar ao máximo para vocês. 

Só que antes de chegar nesse assunto, precisamos entender uma mudança que teremos que fazer na aplicação.

Aqui na nossa aplicação, vamos implementar um conceito que é conhecido como gamificação, que é aquela ideia de você incentivar uma colaboração no software. 

É até parecido com o que temos aqui na Alura. 

Na Alura, quando você termina um curso, você ganha uma pontuação, quando você abre uma dúvida no fórum, quando você ajuda alguém no fórum, então existem várias ações que você faz que você vai ganhando pontos.

Você pode ganhar medalhas, selos, quando você atinge determinada marca. 

Então é justamente isso o que vamos fazer aqui. 

Vamos começar criando essa classe que representa essa ideia de selo. 

Toda vez que o aluno, ele atinge uma determinada ação, ele vai ganhar um selo. 

Então precisamos implementar isso no domínio da aplicação.

Isso não está diretamente relacionado com aluno e com a indicação, então vamos criar uma classe dentro de um outro pacote. 

Vou criar uma classe, essa classe vai se chamar "Selo" e ela vai ficar dentro da camada "dominio", só que dentro do pacote selo. 

O selo nada mais é do que como se fosse uma medalha que um aluno vai receber.

O selo, ele tem um nome e ele estará vinculado a algum aluno, que é o aluno que ganhou aquele selo. 

Então na classe Selo, eu vou ter dois atributos: private CPF cpfDoAluno;, então conseguimos identificar o aluno pelo CPF, e private String nome;, que é o nome do selo. 

Então, por exemplo, o aluno se matriculou, ele ganhou o selo novo aluno, o aluno concluiu um curso, é iniciante.

Enfim, podemos ter vários selos, cada selo tem um nome e cada selo pertence, é atribuído a um aluno. 

Vou gerar aqui um construtor, para que na hora que essa classe for instanciada, o nome do selo e do CPF do aluno já serem passados como parâmetro.

Vou gerar também os métodos getters, vou gerar somente os getters do selo e do nome, se alguém precisar de fora acessar o nome do selo e o CPF do aluno. 

Está pronta a nossa classe Selo. 

```
public class Selo {

    private CPF cpfDoAluno;
    private String nome;

    public Selo(CPF cpfDoAluno, String nome) {
        this.cpfDoAluno = cpfDoAluno;
        this.nome = nome;
    }

    public CPF getCpfDoAluno() {
        return cpfDoAluno;
    }

    public String getNome() {
        return nome;
    }
}
```

Essa classe, ela representa justamente essa ideia de selo, aqui nós começamos a implementar esse conceito da gamificação.

Porém, aqui tem uma questão importante, **essa parte de gamificação, ela poderia estar separada do resto**, das outras funcionalidades da aplicação. 

Então a parte de gamificação, essa parte de selo e as outras classes que vamos criar, relacionadas com gamificação, não precisam saber das regras de matrícula do aluno, de telefone, que o aluno pode ter dois telefones.

Seria legal começarmos a pensar em separar esses contextos da aplicação, você ter de um lado o contexto, vamos chamar de acadêmico, cuida da parte de aluno, de matrícula de indicação, e um contexto de gamificação, que cuida só dessa parte de pontuação, de selos e de outras coisas relacionadas com gamificação.

Nós começamos a fazer uma separação para simplificar o código, para não deixar o código amarrado, o meu código de gamificação totalmente acoplado com o meu código acadêmico. 

Até para isolarmos essas regras, esse contexto de gamificação não ter acesso a coisas que não fazem sentido para ele. 

Eu vou deixar como desafio para vocês - eu só implementei a classe Selo. 

Eu vou deixar como desafio você implementar um repositório de selo. 

Eu quero que você implemente a interface do repositório e uma implementação, uma implementação de selo em Memória mesmo, só para facilitar. 

### Separando os contextos
Na última aula eu tinha deixado um desafio para vocês, de criar o repositório de selos. 

Eu fiz aqui, bem rápido, então é aquele mesmo esquema, igual o repositório de alunos: você tem public interface RepositorioDeSelos, no pacote de "dominio".

Essa é só a interface que representa o repositório.

E eu coloquei dois métodos, o método void adicionar(Selo selo);, para adicionar um selo, e um método List<Selo> selosDoAlunoDeCPF(CPF cpf);, para listar os selos de um determinado aluno, baseado pelo CPF dele. 

```
public interface RepositorioDeSelo {

    void adicionar(Selo selo);

    List<Selo> selosDoAlunoDeCPF(CPF cpf);

}
```

Na camada de infraestrutura, a implementação, uma classe implementa RepositorioDeSelosEmMemoria, bem parecido com o que fizemos de com o de aluno.

Então tem uma lista, o adicionar(Selo selo) vai adicionar nessa lista e para buscar os selos do aluno eu uso o .stream e filters do Java 8. 

Então, continuando os nossos estudos, agora um problema que nós temos é justamente esse, que estamos misturando essa questão do selo, que isso pertence a um conceito de gamificação, isso deveria estar separado do contexto acadêmico.

Quando fazemos essa mistura, estamos criando um acoplamento, sendo que isso poderia ser tratado de maneira distinta, poderiam ser dois módulos, até duas aplicações separadas, com times distintos e sem criar essa dependência forte, para que favoreça a manutenção e a evolução deste software.

O que vamos fazer nessa aula é justamente isso: separar essa parte de selos no contexto de gamificação, e o resto da aplicação fica no contexto acadêmico, naquela parte de aluno, de matrículas e de indicação. 

Então essa separação entre contextos, ela pode ser feita criando-se dois módulos. 

Esses módulos têm diversas maneiras de você implementar esses dois módulos.

Uma delas é você utilizar, por exemplo, os módulos Java 9, outra seria transformar em módulos do Maven, enfim, tem várias abordagens. 

Só para não perder muito tempo com isso, que é algo específico do Java, eu vou utilizar pacotes mesmo, então eu vou separar em pacotes dentro do mesmo projeto, mas poderia até ser dois projetos separados.

#### Package academico
Então, para não perder muito tempo e não quebrar a cabeça com coisas que não são o foco do curso, eu vou separar em dois pacotes. 

Dentro do nosso pacote raiz, que é o nosso pacote "br.com.alura.escola", eu vou criar um subpacote acadêmico e um subpacote gamificação.

Para agilizar, eu vou fazer o seguinte, eu vou pegar esse pacote raiz "br.com.alura.escola", vou renomear ele - botão direito do mouse, "Refactor > Rename", vou colocar como "br.com.alura.escola.academico". 

Vou colocar para ele renomear todos os subpacotes. 

Tudo vai ser jogado dentro desse pacote "br.com.alura.escola.academico" e depois eu separo o pacote de gamificação.

A parte dos testes, eu vou deixar para vocês implementarem, para vocês fazerem essa refatoração dos pacotes, então não vou olhar para essa parte dos testes. 

#### Package gamificaçao
Agora eu preciso criar um outro pacote. vai ser invés de acadêmico, "br.com.alura.gamificacao". 

Então dentro do pacote raiz "br.com.alura.escola", eu tenho o pacote "academico", o módulo "academico" e o módulo "gamificação", que são dois contextos.

Dentro do "gamificacao", eu tenho que fazer parecido com o que eu fiz com o "academico", eu tenho que ter os módulos, as camadas de aplicação, domínio e infraestrutura. 

Vou criar esse subpacotes no "gamificacao", então mais um pacote, "br.com.alura.escola.gamificacao.aplicacao".

Vai ser parecido com o acadêmico, eu vou ter a camada de aplicação "br.com.alura.escola.gamificacao.dominio", vou ter a camada de domínio, com as classes de domínio. E "br.com.alura.escola.gamificacao.infra" com a camada de infraestrutura também, da mesma maneira que fizemos no começo do projeto, com o módulo de "academico".

Agora, aquela classe Selo, eu tenho que mover ela para o pacote "dominio" da "gamificacao". 

"dominio.selo" e eu vou arrastar só a classe, a interface, o repositório e a classe para "gamificacao". 

E eu apago esse pacote "selo" do contexto "academico".

Então o contexto "academico" agora, ele só tem a parte de aluno e de indicação, de matrícula de indicação. 

E o pacote, o módulo, o contexto de gamificação ficou com o "dominio", com essa parte de "Selo" e "RepositorioDeSelos". 

Faltou um detalhe na parte de infraestrutura do "academico", também tenho esse "selo". 

Então vou jogar para "gamificacao", vou criar um pacote "br.com.alura.gamificacao.infra.selo".

Essa classe, que é o "RepositorioDeSelosEmMemoria", que eu tinha implementado, que eu tinha deixado como um desafio para vocês, joguei para "gamificacao > infra.selo" e agora vou apagar esse pacote do "academico". 

Agora eu tenho esses dois contextos separados. 

No contexto acadêmico, tudo o que for referente ao acadêmico: matrícula, indicação e outras coisas relacionadas com o acadêmico, jogamos aqui, nesse contexto.

Tudo o que for relacionado com gamificação fica nesse contexto separado. Então eu tenho dois contextos separados em pacotes, poderiam ser módulos do Java 9, módulos do Maven, enfim, o jeito que você vai implementar não tem tanta importância aqui, o foco é você separar esses contextos e deixá-los distintos, para não criar um acoplamento.

Inclusive, eles poderiam ser projetos distintos. 

Se você trabalha com arquitetura de microsserviços, provavelmente você teria dois microsserviços aqui, um microsserviço acadêmico e um microsserviço de gamificação. 

Cada microsserviço poderia ter um time de desenvolvimento separado, enfim.

Porém, se fossemos fazer essa separação, teríamos um problema, porque no contexto acadêmico, na nossa classe Selo, temos o CPF do aluno. 

O CPF, se formos olhar o import, ele está puxando esse CPF, ele está importando lá do pacote do contexto acadêmico, então isso vai gerar um acoplamento, acabamos nos acoplando do mesmo jeito.

Então, se quisermos extrair esse módulo, esse contexto de gamificação para um projeto separado, não íamos conseguir extraí-lo e deixá-lo de maneira totalmente isolada. 

Para extrair esse módulo de gamificação, ele tem essa dependência do CPF do módulo acadêmico, então eu ia ter que ter também junto, eu teria que levar junto uma ou mais classes do contexto acadêmico.

Então não estamos separando totalmente, ainda temos um acoplamento. 

O ideal seria buscarmos uma alternativa para o módulo de gamificação não depender do módulo acadêmico. 

Então o CPF, eu não poderia colocar o CPF no Selo, eu teria que fazer de uma outra maneira. 

Vou deixar como desafio você fazer o refactor dos testes, porque os testes ainda não estão com a separação dos contextos. 

### Mapas de contexto
Existe um conceito no Domain Driven Design chamado mapa de contextos, que nada mais é do que uma representação visual dos contextos que existem dentro daquele domínio, daquela aplicação e os conceitos do domínio, onde eles se encaixam.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/3.PNG)

Eu peguei na internet um exemplo aleatório de uma representação, um mapa de contexto, como seria isso no Domain Driven Design. 

Perceba que temos os especialistas de domínio, no lado direito, conversando, interagindo, e utilizando aquela ideia da linguagem ubíqua para definir, para fazer esse desenho, esse mapa mental, sobre os contextos do domínio da aplicação.

Nesse exemplo, perceba que temos vários contextos. 

Você tem isolado o contexto de CRM, você tem o contexto de categoria, de pedidos, de produtos, de locação, de entrega, enfim, você tem vários contextos. 

Dentro de cada contexto você tem as várias informações: as entidades, os value objects, os services.

a grande questão desse mapa é identificarmos como esses contextos estão separados, mas também principalmente como eles se comunicam - qual contexto conversa com qual contexto? 

Então você percebe, por exemplo, que temos um contexto de produto e ele tem do outro lado um contexto de pedidos.

#### Nucleo compartilhado
Mas eles precisam trocar informações em comum, então entre eles você tem um contexto, um núcleo compartilhado. 

Então o contexto de produtos e pedidos, eles conversam entre si e eles estão isolados, tem essa linha pontilhada em volta. Temos um contexto de categorias, que também precisa conversar com produtos, mas já é algo que está separado. 

Lá fora, externo, você tem um domínio separado, que é o domínio CRM.

Enfim, então perceba que isso é um trabalho que é feito em conjunto com o time de desenvolvimento e o time de especialistas de domínio da aplicação. 

Nós precisamos entender aqueles conceitos e entender como funcionam esses contextos, quais são os contextos e como eles se comunicam. 

Quais contextos pertencem ao mesmo domínio, a um subdomínio, se terá que ficar separado e quais são as informações em comum entre eles.

No nosso caso, da nossa aplicação eu fiz um desenho. 

Temos o nosso projeto e temos esses dois contextos no momento, o contexto acadêmico e o contexto gamificação. 

Duas informações específicas de cada um desses contextos: no contexto acadêmico eu tenho a informação do aluno, já no contexto de gamificação eu tenho aquela ideia de um selo.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/4.PNG)

São duas informações separadas. 

Porém esses dois contextos terão que conversar. 

Lembra que no conceito de gamificação eu preciso do CPF do aluno, o selo, ele precisa saber: esse selo é de qual aluno? 

Nós fizemos esse mapeamento com o CPF do aluno. 

Então temos basicamente duas abordagens para fazer essa comunicação entre esses dois contextos. 

Uma delas seria essa, dessa outra figura.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/5.PNG)

Nós poderíamos ter um núcleo compartilhado que, no caso aqui, estaria dentro dele o CPF. 

Então no nosso exemplo eu tenho o contexto acadêmico, que tem o aluno, o contexto de gamificação separado, ele tem um selo, mas tem uma interseção entre esses contextos, que é justamente usando o CPF.

Aqui a vantagem é que compartilhamos informações, tudo o que é comum entre esses dois contextos vai ficar nesse núcleo central. 

Você favorece essa união, você tem esse código que está compartilhado. 

Porém você tem aquele problema do acoplamento, um contexto, ele vai depender diretamente do outro contexto.

Então se quiséssemos extrair esse contexto da gamificação, jogar para um outro retângulo, que seria um outro sistema, teríamos essa dificuldade, porque teríamos que levar junto o CPF, só que o CPF, ele está amarrado aqui no meio. 

Então, talvez, uma outra abordagem seria essa, dessa outra figura.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/6.PNG)

Eu tenho os dois contextos, só que agora eles estão completamente separados. 

**Só que a informação comum entre eles, ela está duplicada**, então o CPF está duplicado no contexto acadêmico e também no contexto de gamificação. 

A desvantagem é essa: você tem a duplicação de código, porém você tem essa vantagem de deixar o código totalmente desacoplado.

Eu posso extrair esse contexto de gamificação para fora, jogar para um sistema externo, que ele não vai depender, não vai gerar impactos no contexto acadêmico. 

**São duas abordagens distintas e não tem um certo e um errado aqui**. 

Lembre, a arquitetura é esse negócio complexo, nós precisamos entender as vantagens e desvantagens, cada caminho que podemos seguir e quais são as vantagens e desvantagens de cada um desses caminhos.

Mas, no geral, não tem uma única maneira. 

Você terá que pesar, ponderar qual caminho é mais apropriado para vocês. Se há uma desvantagem, se ela é aceitável ou se não, se não é aceitável, ela gera um impacto muito grande no seu contexto. 

Então são abordagens distintas, com vantagens e desvantagens.

A grande questão aqui, que precisamos ter em mente, que você precisa ter em mente, é que em um projeto de software complexo, em um projeto grande, que tem muita informação, muitos conceitos complexos e complicados, você vai se esbarrar com esse problema, você eventualmente vai ter que separar esses contextos.

Você vai ter conceitos que vão pertencer a um contexto e outros conceitos que vão pertencer a outros contextos. 

Você vai ter que entender, conseguir enxergar quais são os contextos da aplicação e que informação pertence a cada um desses contextos e, principalmente, como esses contextos vão se comunicar, como vai ser feita a separação entre esses contextos, como será feita a delimitação entre esses conceitos, entre esses contextos.

Existe uma discussão, muita coisa importante sobre essa separação, como separar e como fazer essa comunicação entre esses contextos. 

vamos utilizar essa primeira abordagem, vamos usar um contexto compartilhado. 

Então vamos ter um contexto compartilhado, onde o CPF, no caso, estará compartilhado entre o contexto de gamificação e o contexto de acadêmico.

### Para saber mais: Bounded Contexts
Bounded Contexts, ou contextos delimitados, são um dos conceitos mais complexos de entender e implementar do estudo do DDD.

#### https://martinfowler.com/bliki/BoundedContext.html
Bounded Context is a central pattern in Domain-Driven Design. 

It is the focus of DDD's strategic design section which is all about dealing with large models and teams. 

DDD deals with large models by dividing them into different Bounded Contexts and being explicit about their interrelationships.

DDD is about designing software based on models of the underlying domain. 

A model acts as a UbiquitousLanguage to help communication between software developers and domain experts. 

It also acts as the conceptual foundation for the design of the software itself - how it's broken down into objects or functions. 

To be effective, a model needs to be unified - that is to be internally consistent so that there are no contradictions within it.

As you try to model a larger domain, it gets progressively harder to build a single unified model. 

Different groups of people will use subtly different vocabularies in different parts of a large organization. 

The precision of modeling rapidly runs into this, often leading to a lot of confusion. 

Typically this confusion focuses on the central concepts of the domain. 

Early in my career I worked with a electricity utility - here the word "meter" meant subtly different things to different parts of the organization: was it the connection between the grid and a location, the grid and a customer, the physical meter itself (which could be replaced if faulty). 

These subtle polysemes could be smoothed over in conversation but not in the precise world of computers. 

Time and time again I see this confusion recur with polysemes like "Customer" and "Product".

In those younger days we were advised to build a unified model of the entire business, but DDD recognizes that we've learned that "**total unification of the domain model for a large system will not be feasible or cost-effective**". 

So instead DDD divides up a large system into Bounded Contexts, each of which can have a unified model - essentially a way of structuring MultipleCanonicalModels.

Bounded Contexts have both unrelated concepts (such as a support ticket only existing in a customer support context) but also share concepts (such as products and customers). 

Different contexts may have completely different models of common concepts with mechanisms to map between these polysemic concepts for integration. 

Several DDD patterns explore alternative relationships between contexts.

Various factors draw boundaries between contexts. 

Usually the dominant one is **human culture**, since models act as Ubiquitous Language, you need a different model when the language changes. 

You also find multiple contexts within the same domain context, such as the separation between in-memory and relational database models in a single application. 

This boundary is set by the different way we represent models.

DDD's strategic design goes on to describe a variety of ways that you have relationships between Bounded Contexts. 

It's usually worthwhile to depict these using a context map.

### O que aprendemos?
* Conhecemos o conceito de Bounded Contexts ou Contextos Delimitados;
* Vimos que a separação em contextos nos dá mais flexibilidade porém aumenta (e muito) a complexidade;
* Conhecemos o desenho conhecido como Mapa de Contexto.

## Contexto compartilhado (Shared Kernel)
### Shared Kernel
vamos começar a fazer essa separação, precisamos utilizar aquela estratégia de ter um contexto compartilhado. 

No nosso caso, vai ser em relação ao CPF do aluno, **nós precisamos compartilhar o CPF, tanto no contexto acadêmico quanto no contexto de gamificação**.

Então **não** vamos usar aquela abordagem de duplicar aquelas informações nos dois contextos e sim a abordagem de criar um contexto compartilhado entre eles. 

Para representar esse contexto, eu vou criar um pacote, mais ou menos utilizando a mesma abordagem que nós fizemos, fazendo essa separação de pacotes.

O pacote vai se chamar "br.com.alura.shared", que é um nome bem comum, que o pessoal utiliza para representar esse contexto que é compartilhado, onde tem classes, tem conceitos do domínio que são compartilhados entre um ou mais contextos. 

Dentro desse "shared", vou criar um outro pacote, que vai ser o pacote "br.com.alura.shared.dominio".

No caso, agora precisamos trazer a classe CPF para esse "br.com.alura.shared.dominio", já que a classe "CPF", ela pertence ao "br.com.alura.shared.dominio", só que ela ficará compartilhada. 

Pronto, agora temos o contexto acadêmico, o contexto de gamificação e o contexto shared, então tudo o que for comum, toda a classe, seja entidade, VO, service, enfim, qualquer tipo de classe que for compartilhada, que precisarmos utilizar em mais de um contexto, jogamos para cá, no pacote, no diretório que representa o módulo compartilhado.

Então aqui, de novo, poderia ser um módulo do Maven, do Java 9, nós estamos usando pacotes só para simplificar. 

Agora já começamos a fazer essa separação e compartilhar informações que serão utilizadas em mais de um contexto. 

Com isso podemos continuar o desenvolvimento. 

Eu vou deixar como desafio para vocês: ajustar os testes automatizados, porque temos aqueles testes do CPF.

Então vou deixar esse desafio para vocês, para vocês extraírem para o diretório certo, do contexto compartilhado. 

### Para saber mais: Shared Kernel
Aqui nesse artigo há uma revisão sobre Bounded Contexts e uma breve citação sobre Shared Kernel: https://maiconheck.medium.com/domain-driven-design-os-building-blocks-parte-3-domain-subdomains-e-bounded-contexts-a51d5a9d9851

### Ouvintes independentes
A próxima funcionalidade que vamos fazer vai ser justamente em relação ao contexto de gamificação, aquela parte de selos. 

Nós queremos implementar a seguinte funcionalidade: sempre que um aluno é matriculado, um aluno novo é matriculado no sistema, precisamos gerar um selo para ele, que é o selo de aluno novato. 

À medida que formos implementando, vamos ver que alguns problemas vão aparecer, já que começamos a separar esses contextos, então vamos passar por alguns problemas e vamos tentar resolvê-los da melhor maneira possível. 

Como isso tem a ver com gamificação, aqui mesmo, na camada "gamificacao", no contexto de gamificação, vamos iniciar uma funcionalidade, é um caso de uso.

Então eu preciso gerar um selo sempre que um aluno for matriculado. 

Então no meu pacote "gamificacao > aplicacao", vamos criar uma classe, "New > Class". O nome dessa classe vai ser "GeraSeloAlunoNovato", é essa a ideia dessa classe, ela vai gerar um selo para um aluno novato.

Essa classe, ela é, na verdade, um ouvinte, então ela precisa herdar daquela classe ouvinte, para ouvir o evento AlunoMatriculado e poder disparar o processo de geração de selo. 

Porém temos um problema aqui. 

Se formos importar essa classe Ouvinte, o import, perceba, o Ouvinte está no pacote, no contexto acadêmico, e é isso que não poderíamos fazer, o contexto de gamificação acessar diretamente as coisas do contexto acadêmico.

Então um primeiro problema que nos esbarramos. 

Na realidade, "eventos" é algo que também é compartilhado, essa parte de ouvir um evento, publicar um evento, isso não é algo específico do contexto acadêmico, é algo geral, então eu quero poder também ouvir eventos, disparar eventos do contexto de gamificação e de qualquer outro contexto.

Então vamos pegar no contexto "academico", vamos migrar essas classes "Evento", "Ouvinte" e "PublicadorDeEventos" e levar elas para o contexto "shared", o contexto compartilhado, já que isso vai ser comum entre esses dois contextos.

Dentro da pasta "shared.dominio", só para não deixar solta essa parte de eventos, vou criar um pacote, vou criar um subpacote chamado "evento", só para separar dentro do pacote "shared.dominio" as coisas relacionadas com eventos. 

Agora sim eu vou selecionar aquelas três classes e vou arrastar para "shared.dominio > evento". 

então agora, no nosso contexto compartilhado, tem o CPF do aluno e tem as três classes relacionadas a um evento. 

Agora podemos continuar com o GeraSeloAlunoNovato. 

Perceba que agora eu estou importando o Ouvinte do pacote shared, do contexto compartilhado, então não tem problema.

Ele está reclamando porque eu estou herdando essa classe Ouvinte, lembre que ela tem dois métodos abstratos, que precisamos implementar, o protected void reageAo(Evento evento), que é o método que vai de fato executar a lógica desse ouvinte, e o protected boolean deveProcessar(Evento evento), que é para indicar se essa classe GeraSeloAlunoNovato vai processar um determinada evento que está acontecendo.

Lembra, eu não sei qual é o evento que está acontecendo. 

E aquilo deve processar? 

Nós deveríamos usar aquela mesma abordagem, eu só devo processar se o evento for AlunoMatriculado, então return evento instanceof AlunoMatriculado;, da mesma maneira que fizemos no outro evento de gerar log de matrícula.

Então eu só quero disparar o evento GerarSeloAlunoNovato se o evento que foi disparado é AlunoMatriculado, outros eventos não importam para essa classe. 

Nos esbarramos no segundo problema, olha o import que estamos fazendo, tem o pacote acadêmico. 

Eu estou no contexto gamificação, eu não posso acessar as coisas do contexto acadêmico, eu estou ferindo aquela regra de delimitar os contextos.

Esse é o segundo problema. 

Agora vem essa questão: como fazemos então para saber qual é o evento, sendo que esse evento, ele pertence a um outro contexto? 

E agora? 

Tem várias opções, várias possibilidades. 

Eu vou seguir uma abordagem aqui, você pode seguir uma outra, caso você prefira. 

O que eu vou fazer então?

Já que eu preciso conhecer qual é o tipo do evento e tal, eu vou fazer o seguinte: no contexto compartilhado, no pacote mesmo de "eventos", eu vou criar um "enum", esse enum eu vou chamar de "TipoDeEvento", então todos os eventos vão ser registrados nesse enum. 

A princípio, o único evento que nós temos é esse, então ALUNO_MATRICULADO. 

Se futuramente eu tiver outros tipos de evento, eu coloco outras constantes aqui.

Agora, na nossa classe Evento, na public interface Evento, que define um evento, lembra que tinha um método que todo método deveria devolver qual é o momento que ele aconteceu? 

Eu vou criar um outro método, que todo evento vai ter que devolver qual é o tipo dele. 

Então vou chamar o método de TipoDeEvento tipo();, e o que ele devolve? 

Um objeto do tipo: tipo evento, que é um enum.

Então, a partir desse momento, que eu fiz isso, no contexto "acadêmico > dominio > aluno", no evento AlunoMatriculado, como ele está implementando aquela interface Evento, e eu adicionei um segundo método, eu vou ter que implementar ele aqui também. 

Eu preciso dizer, esse evento específico, qual é o tipo dele. 

Então return TipoDeEvento.ALUNO_MATRICULADO;, que por enquanto nós só temos esse. 

Então eu estou agora no contexto acadêmico, eu importei o tipo evento, mas o tipo evento está todo no "shared", então ok, eu posso acessar tudo que está no módulo, no contexto compartilhado, não tem problema.

A partir de agora eu consigo saber, dado um evento, qual é o tipo dele, sem ter que ficar acessando informações de um outro contexto. 

Vamos voltar para a nossa classe GeraSeloAlunoNovato. 

Então eu preciso mudar essa lógica, ela só vai processar esse evento se return evento.tipo == TipoDeEvento.ALUNO_MATRICULADO;.

```

```

Vou organizar os imports, olha que legal, só tem imports do contexto compartilhado. 

No contexto de gamificação não estou mais acessando nada do contexto compartilhado. 

Show de bola, então eu consigo saber qual é o tipo do evento sem saber informações, acessar informações do outro contexto diretamente. Então segundo problema resolvido.

Agora precisamos implementar no GeraSeloAlunoNovato o método protected void reageAo(Evento evento), que é aqui que vem a lógica dessa classe, desse ouvinte, de gerar selo. 

A lógica vai ser qual? Criar um selo e gravar no mecanismo de persistência. 

Então eu preciso criar um selo: Selo novato = new Selo();.

Só que na hora de instanciarmos o selo, nós precisamos passar duas informações: o CPF e uma string, que é o nome do selo. 

Eu vou precisar passar entre os parênteses o (cpfDoAluno, "") e o nome do selo. 

O nome do selo, eu vou chamar de (cpfDo Aluno, "Novato");. 

Poderia ser outro nome, poderia ser um enum também, que tem os nomes dos selos, enfim, só para não perder muito tempo eu vou deixar assim.

Aqui vamos para um terceiro problema: eu preciso passar o CPF do aluno. 

Como eu vou pegar o CPF do aluno? 

O CPF do aluno vem do Evento. 

O evento não é o AlunoMatriculado? 

Beleza. E o evento AlunoMatriculado não tem um CPF? Ok. 

Só que no GeraSeloAlunoNovato, eu não tenho um objeto AlunoMatriculado, eu tenho a interface Evento.

É só fazer um casting, aqui é a interface, mas nós sabemos que é um objeto do tipo AlunoMatriculado. 

Só que aí é que está, eu não posso fazer o casting, se eu fizer o casting eu vou acessar a classe AlunoMatriculado, que vem do contexto acadêmico e vou ferir de novo a delimitação do contexto. 

Então vem o terceiro problema: como acessar informações específicas de um evento?

De novo, tem um monte de opções que poderíamos utilizar para resolver esse problema sem um contexto acessar o outro contexto diretamente, a não ser que seja um contexto compartilhado. 

Eu vou usar uma abordagem aqui, você pode utilizar outra, caso prefira. 

O que eu vou fazer? 

Como eu vou precisar fazer isso nos ouvintes, os ouvintes, eles precisarão acessar informações específicas de cada evento, só que eu não quero que essas informações sejam do outro contexto.

Eu vou fazer o seguinte: na interface Evento, além de todas as classes que implementam essa interface devolverem o momento em que aconteceram e devolverem o tipo, eu também quero devolver uma ou mais informações, só que sem gerar um acoplamento entre os contextos. 

Eu vou criar um método que devolve um mapa Map<String, Object> informacoes();.

Um mapa que tem uma chave string que devolve o nome da propriedade que eu quero acessar e o valor pode ser qualquer informação, então eu vou colocar como Object e vou chamar esse método de informações. 

A partir de agora, todo evento, ele tem que me devolver qual foi o momento em que ele ocorreu, tem que me devolver qual é o tipo e tem que me devolver quais são as informações que esse evento grava, que esse evento gera.

Utilizando um Map<String, Object> informacoes();, não ficamos acoplados.

Novamente a classe AlunoMatriculado começou a dar erro de novo, porque eu preciso agora implementar esse terceiro método. Vamos implementar esse terceiro método, que é o método para devolver as informações.

Nesse evento AlunoMatriculado, por enquanto, a única informação que eu preciso devolver é o CPF do aluno, então basta eu devolver um public Map<String, Object> informacoes() que contenha uma string CPF e o valor um objeto CPF, que o objeto CPF em si está no contexto compartilhado, então eu posso devolver ele.

Então vou usar um recurso do Java 9, return Map.of () e eu passo uma chave e um valor entre parênteses. 

A chave vai ser ("cpf", ) e o valor vai ser o objeto ("cpf", cpfDoAluno);, que eu tenho dentro do evento AlunoMatriculado, que é esse atributo. 

Se eu quisesse devolver outras informações, eu podia continuar dentro dos parênteses, colocando vírgula, a chave e o valor, vírgula, a chave e o valor.

Vamos separando todas as informações que esse evento quer compartilhar. 

Pronto, gravei. 

Vou voltar para o GeraSeloAlunoNovato. 

Agora, para pegar o cpf, dentro de reageAo(Evento evento), CPF cpfDoAluno = evento.informacoes()., que vai devolver aquele mapa, .get("cpf");, que eu vou acessar a chave CPF.

Obviamente, isso vai me devolver um object, que é uma string object, e eu faço um cast para o CPF. 

Mas eu estou acessando o CPF - só que o CPF, olha, ele vem no contexto compartilhado. 

Se olharmos os imports, eu não estou acessando nada no contexto acadêmico.

Pronto, consegui compartilhar informações de evento, de um determinado contexto com um ouvinte que está no outro contexto, sem nenhum ferir, sem nenhum acessar e burlar aquela barreira de delimitação dos contextos. 

Pronto, implementamos o nosso GeraSeloAlunoNovato.

Então perceba, a partir do momento que você faz essa separação, que você faz essa delimitação entre os contextos, você vai ter que extrair um monte de código e você vai se esbarrar, uma hora ou outra, em um contexto que precisará acessar informações de outros contextos.

E você vai ter que pensar em alguma abordagem, usando o enum, usando o mapa, usando interfaces, enfim, usando algum mecanismo para que um contexto não acesse informações diretas de um outro contexto, a não ser o contexto compartilhado, que esse é o objetivo dele.

Aqui, só para fechar, eu gerei esse selo, só que eu não fiz nada com ele, então nessa classe, nesse ouvinte, o que temos que fazer? 

Gerar o selo e gravar, gravar no banco de dados, gravar no mecanismo de persistência. 

Então, nessa classe eu vou precisar também do repositório. 

Vou declarar ele como um atributo private final RepositorioDeSelos repositorioDeSelos;.

Vou criar o construtor, vou fazer aquela ideia de inversão de dependências, porque aqui eu não quero instanciar a implementação do repositório. 

Então pode vir qualquer uma implementação, em Memória, com JDBC, com JPA, não importa. 

Gravei aqui, só atribuí a variável.

Vou pegar o repositorioDeSelos.adicionar(novato);, vou pegar esse selo do aluno novato, que foi instanciado na linha de cima. 

Morreu o assunto, acabou. 

Está aí a nossa classe que gera um selo para um aluno novato utilizando aquela ideia de eventos, para deixar desacoplado, e agora com eventos que conseguem acessar informações que estão em um outro contexto sem ferir, sem burlar a barreira que separa os dois contextos.

Sempre que estiver implementando alguma funcionalidade, terminou, dê uma olhada nos imports. 

Aqui eu estou no contexto de gamificação, veja se você não está acessando nada de outros contextos, exceto o contexto shared.

GeraSeloAlunoNovato
```
public class GeraSeloAlunoNovato extends Ouvinte {

    private final RepositorioDeSelo repositorio;

    public GeraSeloAlunoNovato(RepositorioDeSelo repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    protected boolean deveProcessar(Evento evento) {
        return evento.tipo() == TipoDeEvento.ALUNO_MATRICULADO;
    }

    @Override
    protected void reageAo(Evento evento) {
        CPF cpfDoAluno = (CPF) evento.informacoes().get("cpf");
        Selo novato = new Selo(cpfDoAluno, "Novato");
        repositorio.adicionar(novato);
    }
}
```

TipoDeEvento
```
public enum TipoDeEvento {
    ALUNO_MATRICULADO;
}
```

Evento
```
public interface Evento {

    LocalDateTime momento();
    TipoDeEvento tipo();

    Map<String, Object> informacoes();
}
```

AlunoMatriculado
```
public class AlunoMatriculado implements Evento {

    private final CPF cpfDoAluno;
    private final LocalDateTime momento;

    public AlunoMatriculado (CPF cpfDoAluno) {
        this.cpfDoAluno = cpfDoAluno;
        this.momento = LocalDateTime.now();
    }

    @Override
    public LocalDateTime momento() {
        return momento;
    }

    @Override
    public TipoDeEvento tipo() {
        return TipoDeEvento.ALUNO_MATRICULADO;
    }

    @Override
    public Map<String, Object> informacoes() {
       return Map.of("cpf", cpfDoAluno);
    }

    public CPF getCpfDoAluno() {
        return cpfDoAluno;
    }
}
```

### Utilização dos contextos
No final das contas, a grande questão aqui, que temos que nos preocupar, é exatamente essa: conseguir identificar quais são os contextos da aplicação.

Representá-los no projeto, seja com pacotes, com módulos ou qualquer outra tecnologia ou esquema que você utilize para separar, criar o contexto compartilhado, tudo o que for comum entre eles ficar aqui, e tentar ao máximo evitar que um contexto acesse informações de outro diretamente, para não criar aquele acoplamento forte, que pode gerar uma dependência e dificultar a manutenção e a extensão do software.

Agora você deve estar se perguntando: como seria o uso dessa aplicação na prática? 

Já que não é o foco do treinamento nós montarmos uma aplicação web, uma API Rest, ou coisa do gênero, talvez não fique tão claro assim para vocês como usar essa aplicação, como eu vou chamar esses contextos e tudo mais.

Não sei se vocês lembram, mas está até no contexto acadêmico, essa classe MatricularAlunoPorLinhaDeComando. 

Lembra que eu tinha criado essa classe só para simular o uso da nossa aplicação? Como se fosse um uso via interface de linha de comando, que eu vou fornecer os dados e fazer a matrícula de um aluno?

Inclusive aqui, no MatricularAlunoPorLinhaDeComando, a ideia é que essa classe, ela não deveria estar na verdade dentro do contexto acadêmico, ela seria uma aplicação web, uma API Rest, uma aplicação de linha de comando, enfim. 

Então ela deveria estar fora, vou jogar para o pacote "br.com.alura.escola", o pacote mais externo que tem aqui de todos.

A ideia é que essa classe, na verdade, fosse um outro projeto. 

Aqui já seria um projeto onde eu teria o framework web, o framework MVC, as telas, as interações com usuário. 

Esse projeto pode ser até dentro desse projeto, um módulo web aqui dentro. 

Essa classe MatricularAlunoPorLinhaDeComando, querendo ou não, ela vai ter que interagir com praticamente todos os contextos.

Então nela, não teria problema você acessar classes do contexto acadêmico, classes do contexto de gamificação e classes do contexto compartilhado, porque, afinal, essa classe, ela pertence a uma camada separada, uma camada que vai ter acesso a todos os outros contextos da aplicação.

Inclusive aqui, nós estávamos simulando a matrícula de um aluno, puxando os dados, fixos mesmo na tela, criando o dto, criando o PublicadorDeEventos e efetuando a matrícula do aluno. 

Aqui, inclusive, poderíamos publicar mais um evento, publicar o evento que nós criamos na última aula, que é aquele evento publicador.adicionar(new GeraSeloAlunoNovato());.

Só que para gerar um selo do aluno novato, eu preciso de um repositório de selos, então eu preciso criar um repositório de selos antes. 

Da mesma maneira que foi criado um repositório em Memória, eu preciso criar um publicador.adicionar(new GerarSeloAlunoNovato(new RepositorioDeSelosEmMemoria));.

Essa classe aqui - criei o repositório de selos, em Memória, senão eu estaria instanciando a interface, aqui na verdade é a implementação. Vou quebrar a linha para ficar mais simples. Então aqui, nessa classe, se formos olhar os imports, estamos importando coisas do contexto acadêmico, coisas do contexto de gamificação e coisas do contexto compartilhado.

Mas não tem problema, porque essa classe, ela - de novo, ela representa uma classe que é o ponto de entrada da aplicação, como se fosse a aplicação web. 

Ela geralmente seria um módulo ou até uma aplicação separada. 

É o local onde nós conseguimos e não temos problemas em acessar esses múltiplos contextos, porque é a utilização de fato da aplicação.

Então a MatricularAlunoPorLinhaDeComando seria um exemplo de como utilizar a aplicação, de como colocar tudo isso em prática, como instanciar e chamar essas classes. 

Então, só para reforçar, aqui é um exemplo de utilização, mas não vai ser o foco nós criarmos uma interface gráfica e nem simular a utilização da aplicação.

**A grande questão que você tem que se preocupar é em manter essa separação dos contextos ao máximo, tentar evitar que um contexto ultrapasse a linha que delimita o outro contexto**. 

Porque se um dia você quiser extrair um desses contextos, como eu tinha comentado em aulas anteriores, se você quiser extrair para uma aplicação separada, para um microsserviço, se você usar a arquitetura em microsserviços, o impacto vai ser pequeno.

Obviamente, se você for extrair esse módulo "gamificacao" para um projeto separado, você vai ter que fazer mudanças no código, mas essas mudanças serão mínimas, porque nós desde cedo, desde sempre, estamos nos preocupando em desacoplar ao máximo os contextos. 

Isso vai favorecer muito, caso um dia essa aplicação evolua e você queira separá-la em uma aplicação separada ou um microsserviço separado.

Então nós já facilitamos muito a vida. 

Esse tipo de arquitetura, que é bem comum hoje em dia, vai ser muito natural e muito simples, nós migrarmos o nosso projeto para seguirmos esse modelo de arquitetura. 

Porque isso favorece não só a manutenção, mas a evolução, a extração do código para projetos e microsserviços separados. 

MatricularAlunoPorLinhaDeComando
```
public class MatricularAlunoPorLinhaDeComando {
	
	public static void main(String[] args) {
		String nome = "Fulano da Silva";
		String cpf = "123.456.789-00";
		String email = "fulano@email.com";

		PublicadorDeEventos publicador = new PublicadorDeEventos();
		publicador.adicionar(new LogDeAlunoMatriculado());
		publicador.adicionar(new GeraSeloAlunoNovato(new RepositorioDeSelosEmMemoria()));
		MatricularAluno matricular = new MatricularAluno(new RepositorioDeAlunosEmMemoria(), publicador);
		matricular.executa(
				new MatricularAlunoDto(nome, 
						cpf, 
						email));
	}

}
```

### O que aprendemos?
* Entendemos que os contextos devem ser independentes, mas precisam se comunicar de alguma forma;
* Conhecemos o conceito de Shared Kernel, e vimos que há vantagens e desvantagens;
* Utilizamos eventos de domínio para realizar parte da comunicação entre contextos;
* Vimos que cada contexto pode fornecer seus Use Cases de forma independente.

## Camada anticorrupção
### Sistemas distribuídos
Nós temos o projeto, que tem o contexto acadêmico, o contexto de gamificação e o contexto compartilhado.

Cada contexto desse tem as suas funcionalidades, os seus casos de uso, suas entidades, services, enfim, e o projeto, ele acaba ganhando uma complexidade quando seguimos por essa linha. 

No módulo "academico" eu tenho esse pacote "aplicacao", tenho esse outro "indicacao", aqui outro "dominio".

Olhe o tanto de classes, pacotes de "indicação", de "infra", "aluno". 

Olhe o tanto de pacotes e subpacotes, classes, interfaces, enfim, isso fora as classes de teste. 

Isso porque a nossa aplicação não tem quase nada, só tem a parte de matricular, de indicar e de gerar selo. 

**Esse tipo de arquitetura, ele tende a ser mais complexo olhando por esse ponto de vista**. 

Eu começo a ter muitos pacotes, muitas classes, muitas separações.

Então **nem todo projeto faz sentido você utilizar esses tipos de conceitos do Domain Driven Design e implementar dessa maneira**. 

Se o seu sistema é um CRUD, aquele cadastro simples que no máximo tem uma validação de formulário, não faz sentido você seguir essa linha, fazer essa separação de camadas, de contextos, da maneira que estamos fazendo aqui no treinamento.

**Você acaba não ganhando, você acaba dificultando mais ainda**. 

Esse tipo de abordagem é mais para projetos muito grande, muito complexos, que tenham um contexto, um domínio que é muito complicado, com muitas informações, muitas regras e tal. 

Então isso, essa separação, ela acaba favorecendo você manter e principalmente estender, adicionar novas funcionalidades.

Então nem todo projeto fará sentido você usar esse tipo de abordagem, esse tipo de arquitetura, esses conceitos do Domain Driven Design, que são um pouco mais avançados. 

Eu até costumo dizer também que eu, particularmente, eu enxergo esse tipo de projeto é para uma equipe mais sênior.

Se você tem uma equipe que tem muitas pessoas iniciantes, mais no nível júnior, talvez elas tenham dificuldade, elas não vão conseguir sair sozinhas do outro lado, elas vão se perder e ter dificuldade em como fazer essas separações, vão ferir as regras de contexto, por exemplo, enfim, são coisas mais avançadas.

Outra coisa que eu quero discutir com vocês é como isso pode evoluir. 

cada contexto desses futuramente pode ser separado em um projeto a parte. 

Isso é bem comum hoje em dia, principalmente para projetos que utilizam aquela arquitetura de microsserviços.

Então é muito provável que, posteriormente, comece a aumentar, comece a ter uma dificuldade, problemas de performance, o seu time, ele já não está mais dando conta de dar manutenção, você quer quebrar em times separados e, para não ficar todo mundo trabalhando no mesmo projeto, no mesmo repositório, vai ser como você separar cada contexto desse em um microsserviço, em um projeto a parte.

Provavelmente isso vai acontecer no futuro, à medida que o projeto vai crescendo, crescendo, crescendo, vai haver essa necessidade e, lá na frente, é comum que o contexto acadêmico vire um projeto separado, um projeto acadêmico, o contexto de gamificação vire projeto separado, vire um microsserviço separado de gamificação.

Isso é interessante, porque você pode ter projetos distintos, cada projeto pode ter um time de desenvolvimento distinto com pessoas distintas, cada projeto pode utilizar tecnologias distintas. 

Você não precisa ter aquele projeto gigantesco, que está amarrado em uma mesma tecnologia e que você não consegue evoluir, senão vai gerar um impacto muito grande no projeto inteiro.

Então cada projeto pode ter times e tecnologias distintas, você consegue favorecer também a questão de escalabilidade. 

Você pode, sei lá, o módulo acadêmico é provável que ele seja muito mais utilizado e muito mais crítico do que o módulo de gamificação, então você pode ter servidores mais parrudos, com mais memória, enfim, do que no módulo de gamificação.

Se o módulo acadêmico cair, isso pode gerar um impacto, você pode ter um tratamento. 

O de gamificação não gera tanto impacto assim, ao meu ver, então você pode ter outro tipo de tratamento. 

Começa a favorecer esse tipo de coisa. 

Mas tudo isso faz muito sentido para projetos complexos, projetos que você quer evoluir de maneira simples e projetos que você quer fazer essa separação, que essa separação, ela vai ser natural no futuro.

Para projetos pequenos, CRUDs, cadastros, talvez não faça muito sentido. 

E, se você faz essa separação entre projetos distintos, você vai cair em um outro mundo, um outro universo de problemas que também pode acontecer, que é o mundo de programação distribuída.

Não é o foco aqui do treinamento discutir sobre programação distribuída e implementar a programação distribuída, porque isso é um outro universo, daria para ter vários treinamentos sobre isso. 

Mas aqui a ideia é: se você separa em projetos distintos, você vai ter que fazer comunicação entre sistemas, vai ser uma comunicação distribuída. 

A partir desse momento, que você tem projetos distintos, a comunicação entre os contextos, ela vai mudar um pouco, ela não vai ser igual estamos fazendo nesse projeto.

Temos esse pacote "shared" e uma classe conversa com a outra pelo pacote "shared". 

A partir do momento que você distribuir as aplicações, a comunicação entre contextos também vai ser distribuída e isso aumenta um pouco a complexidade. 

Então tem vantagens, mas também tem desvantagens.

É mais difícil, é mais complexo e outros problemas podem surgir. 

### Camada Anticorrupção
haviamos comentado sobre aquela questão de complexidade, sobre como o nosso projeto poderia evoluir para uma arquitetura com cada contexto sendo separado em uma aplicação, em um microsserviço. 

Com isso, você acaba criando essa arquitetura distribuída, com vários projetos fazendo integração entre si. 

E algumas coisas podem acontecer, algumas mudanças serão necessárias.

![](https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/7.PNG)

Diagrama começando com o elemento "Publisher", conectado ao elemento "Message Broker" por uma seta tracejada com um ícone de carta. Este último elemento por sua vez se conecta da mesma forma a "Subscriber 1", a "Subscriber 2" e a "Subscriber X". Há uma reticências entre estes dois últimos elementos.

Eu tenho uma imagem aqui, uma das maneiras de você fazer isso. 

Utilizando um sistema de mensageria, por exemplo, no caso do Java tem o tal do JMS, aquela especificação que suporta esse tipo de integração. 

Então de um lado você tem um publisher, que é quem está enviando uma mensagem, disparando um evento.

Aqui poderia ser, por exemplo, o nosso sistema acadêmico, o contexto acadêmico que seria um sistema separado. 

Sempre que um aluno é matriculado, ele envia uma mensagem para um message broker, para o servidor de mensageria, para alguém que estará centralizando essas mensagens.

Do outro lado, você ter um ou mais subscribers, que são pessoas, sistemas interessados em receber aquela mensagem e serem notificados quando aquele evento, aquela mensagem for encaminhada. 

Então, por exemplo, esse subscriber seria o nosso módulo de gamificação. 

No nosso sistema, o contexto extrai ele para uma aplicação de gamificação.

Então sempre que um aluno for matriculado, o sistema acadêmico envia uma mensagem para um servidor de mensageria e o sistema de gamificação, ele vai ser notificado, ele vai consumir essa mensagem e fazer algum tratamento. 

E eu posso ter outros sistemas interessados nessa mensagem.

Por exemplo, o sistema financeiro: sempre que um aluno foi matriculado, o sistema financeiro também estará interessado nesse evento. 

Ele vai ler essa mensagem, vai puxar o CPF do aluno para gerar uma nota fiscal. 

Um outro sistema aqui vai enviar um e-mail de boas-vindas, enfim, esse é um tipo de arquitetura bem comum, um tipo de solução bem comum para esse tipo de arquitetura, você ter um sistema de mensageria.

Aqui a ideia é justamente essa, nesse tipo de arquitetura, nesse tipo de tecnologia, você consegue fazer a separação, separar cada aplicação uma da outra, sem que elas conversem diretamente entre si, criando aquele acoplamento, aquele problema que se uma evoluir, gera impacto na outra.

Desse jeito uma aplicação não conversa diretamente com a outra, elas tem esse message broker, esse sistema de mensageria aqui no meio. 

Então uma aplicação executou alguma coisa, ela manda uma mensagem e ela não sabe quem vai ler a mensagem, qual que vai ser o tratamento, quantos outros sistemas vão ler e o que vão fazer.

Da mesma maneira, o sistema que está recebendo a mensagem, ele também não sabe se tem outros ouvintes para essa mensagem, quem está mandando essa mensagem, então um sistema evolui totalmente separado do outro. 

De novo, podem ser aplicações distintas, com times distintos, tecnologias distintas, bancos de dados distintos.

Você tem um monte de vantagens. 

Você só tem algumas outras dificuldades e problemas. 

Se esse sistema cair, esse sistema de mensageria cair ou se o outro sistema não receber essa notificação, enfim, são outros problemas e outros assuntos mais complexos, que fogem do escopo deste treinamento.

É só para mostrar para vocês uma visão. 

Nesse tipo de abordagem, mais ainda você tem que ter aquela preocupação de manter a separação entre os contextos, manter essa separação entre os sistemas, para não criar um acoplamento forte, uma dependência que pode dificultar a manutenção.

https://github.com/luizClaudioMendes/DDD-domain_driven_design/blob/main/images/8.PNG

Zoom de parte da mesma imagem apresentada primeiro no terceiro vídeo da aula quatro. Nesta ampliação, é exibido a intersecção de nome "Shared Kernel" ao lado do elemento "Orders" que se conecta ao adendo "Anti Corruption Layer".

Então voltei para aquela imagem que eu tinha mostrado pra vocês em alguma aula atrás. 

Nessa imagem, que mostrava um mapa de contextos, se dermos um zoom, vou mostrar para vocês. 

Aqui, no canto, nós temos essa camada rosa, que é o anti corruption layer, uma camada de anticorrupção.

Ela está separando o sistema de shipping, o sistema de envios, de frete, do sistema de pedidos e de produtos. 

Essa camada seria aquele sistema de mensageria, então provavelmente você vai ter algumas classes compartilhadas, uma classe que terá que fazer a ponte entre uma informação que vem de um sistema para uma informação do outro sistema.

Um exemplo, no nosso caso, seria a classe CPF. 

A classe CPF, ela poderia ser compartilhada entre esses dois sistemas. 

Você poderia ter uma classe que faz uma conversão, recebe uma informação de um lado, converte para uma informação que é entendida pelo outro lado.

Ou essa camada pode até ser um sistema inteiro, separado, enfim. 

Então essa camada anticorrupção, o objetivo dela é justamente esse, evitar que você gere um acoplamento, que você gere esse acoplamento entre os vários sistemas, o que pode causar uma dificuldade de manutenção e de evolução.

Então essas são algumas considerações importantes que você precisa ter em relação a sistemas distribuídos. 

Perceba que agora já estamos indo mais para um lado de arquitetura, um lado que é um pouco mais complexo, então não dá para eu ensinar isso para vocês aqui e nem é o objetivo.

Tem um monte de cursos, de treinamentos, e de referências bibliográficas sobre sistemas distribuídos, arquitetura de microsserviços, enfim, é um outro universo gigantesco. 

### Para saber mais: Referências
* O livro que originou o termo DDD: https://www.amazon.com.br/Domain-Driven-Design-Eric-Evans/dp/8550800651/
* Uma releitura com uma linguagem um pouco mais palatável: https://www.amazon.com.br/Implementando-Domain-Driven-design-Vernon/dp/8576089521/
* Um livro prático sobre implementação em Java com SpringBoot de conceitos do DDD: https://www.amazon.com.br/Domain-Driven-Design-Spring-Boot-ebook/dp/B07K5W7CTZ

Há ainda diversos canais do YouTube, blogs e sites que falam sobre DDD. Vale a pena a pesquisa.

### O que aprendemos?
* Conversamos sobre o que é um sistema distribuído;
* Vimos que através de contextos delimitados podemos distribuir sistemas realmente complexos;
* No caso de sistemas distribuídos, falamos que há a necessidade de uma camada anti-corrupção;
* Vimos algumas referências para nos aprofundar no assunto de DDD.