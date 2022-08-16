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



Então, no nosso projeto, nós tínhamos aqui, na camada "dominio", os dois principais conceitos do domínio da aplicação, que é a parte de aluno, então temos a representação do que é um aluno para esse projeto. O aluno tem o CPF, o nome, e-mail, uma lista de telefones, uma senha depois para fazer o login. Tem a parte do CPF em si, que representamos em uma classe separada, o e-mail também representamos em uma classe separada.

Nós criamos uma factory para isolar a criação do objeto aluno, deixando mais simples para quem for, dentro do domínio da aplicação, fazer a criação, a instância do objeto aluno. A questão do telefone também, com validações do DDD, do número, enfim. E a parte de indicação, que um aluno, ele pode indicar um outro aluno, então temos o aluno que indicou, o aluno indicado na verdade, o aluno que indicou, quando que essa indicação ocorreu.

E, dentro da parte de infraestrutura, também precisamos criar algumas coisas, porque na parte de domínio tem só os conceitos de domínio, não tem independência com infraestrutura e com detalhes de bibliotecas, frameworks, nem nada do gênero, então ficou totalmente isolado. Fizemos, por exemplo, a parte de gerar uma senha usando um algoritmo de MD5, gerar o hash da senha com MD5.

Então isso, como precisa ter esse hash, essa funcionalidade gerar hash de senha, isso já não faz parte do domínio, então deixamos parado na camada de infraestrutura. Outra coisa legal que também fizemos é que na camada de domínio, nós criamos uma interface que representa o repositório, que é aquele conceito de persistência do mundo dos negócios.

O pessoal que faz parte da área de negócios da empresa, eles não talvez não saibam o que é banco de dados, mas eles têm essa noção de persistência, de armazenar e recuperar informações de algum lugar, então esse conceito é chamado de repositório. Nós preferimos representá-lo na aplicação como sendo uma interface e a implementação dessa interface, desse repositório, ela está na camada de infraestrutura.

Nós fizemos um repositório usando JDBC e tem o código aqui do JDBC. E fizemos também um repositório em Memoria, utilizando uma lista aqui em Memoria, e vimos que foi fácil de escrever testes automatizados para essa implementação do repositório. Então aqui são algumas das funcionalidades do código, que implementamos nessa aplicação que foi desenvolvida no curso de Clean Architecture, que é um curso que é requisito desse treinamento de Domain Driven Design.

Perceba o seguinte, como nesse outro treinamento nós focamos bastante na parte de arquitetura e de domínio da aplicação, indiretamente já acabamos adotando e seguindo vários dos conceitos do Domain Driven Design. Então, por exemplo, o foco foi totalmente na parte de dominío da aplicação, então no menu do lado esquerdo, inclusive, temos esse pacote "dominio" e dentro dele temos esses termos: aluno, e-mail, CPF, telefone, indicação.

Então são conceitos do domínio, nós batemos o olho aqui, nós sabemos: isso aqui tem a ver com alguma escola, com alguma coisa de sistema de matrícula, ou algo do gênero. Então os conceitos do domínio estão bem explícitos, o foco é totalmente na parte do domínio da aplicação. Outra coisa importante também, acabamos separando a nossa aplicação em camadas.

Esquema com dois elementos que transitam através de setas pelas camadas "User Interface", "Application", "Domain" e "Infrastructure".

Aqui eu coloquei uma foto. No livro de Domain Driven Design, o Eric Evans, ele também cita isso, que importa fazermos essa separação em camadas, para tentar isolar o código do domínio da aplicação. No livro dele, ele sugere essa separação em camadas, então são as camadas do DDD.

Aqui em cima, temos a camada que representa a interface gráfica, a interface com o usuário, que vai disparar as requisições, digamos assim, para interagir com a nossa aplicação. Temos uma camada de aplicação, que recebe essas requisições do usuário e faz o fluxo de execução das nossas lógicas. Vamos para a camada de domínio, a camada mais importante, a principal.

E tem por baixo a camada de infraestrutura, que vai dar suporte para essa camada de domínio. No livro do DDD, ele tem essa separação em camadas. Só que no nosso projeto, nós fizemos uma diferença, não seguimos o modelo do Domain Driven Design, seguimos o modelo do Clean Architecture, e criamos uma arquitetura baseada no Clean Architecture.

Então uma diferença que tivemos é que no Domain Driven Design, a camada de domínio, perceba, ela pode chamar a camada de infraestrutura, então as camadas de cima podem chamar as camadas de baixo no modelo do Domain Driven Design. Só que no nosso projeto, que foi desenvolvido no curso de Clean Architecture, não fizemos dessa maneira, porque desse jeito a camada de domínio, ela ia conhecer detalhes de infraestrutura.

Então nós preferimos usar outra abordagem, preferimos fazer aquela inversão de dependências, onde a camada de domínio, ela fica totalmente isolada do resto do projeto. Então, no nosso projeto, a camada de domínio não tem acesso à camada de infraestrutura. Aqui, no "RepositórioDeAlunos", é um exemplo do repositório que eu acabei de falar.

Então, na camada de domínio, temos uma interface, sempre que precisávamos de coisas de infraestrutura nós criávamos uma interface e a implementação dessa interface, aí sim essa implementação está na camada de infraestrutura. Então a camada de domínio, ela só tem o conceito; a interface.

A implementação em si, o como isso será feito, não está mais na camada de domínio, isso não faz parte da camada de domínio e sim da camada de infraestrutura. Então essa foi uma diferença que fizemos em relação às camadas do Domain Driven Design. Então separação em camadas é algo recomendado no Domain Driven Design, é algo que já fizemos com uma sutil diferença em relação a esse modelo sugerido no Domain Driven Design.

Outra coisa também, que eu citei no vídeo anterior que era um dos conceitos do Domain Driven Design, era aquele conceito de building blocks, os blocos de construção. É uma parte mais técnica do Domain Driven Design, do livro, e ele sugere como podemos representar os conceitos do domínio da nossa aplicação, na hora de escrever o código.

Então ele sugere alguns padrões para seguirmos na hora de escrever o código que vai representar os conceitos do domínio. Isso nós também já acabamos fazendo, por exemplo, na classe "Aluno". A nossa classe "Aluno", ela é uma entidade, e é um dos building blocks do Domain Driven Design. O que é uma entidade, só para relembrar?

É aquela classe que tem alguma informação que diferencia ela de outra entidade. Então o Aluno, por exemplo, eu tenho dois alunos. Se os dois alunos tiverem o mesmo nome, eu estou falando do mesmo aluno? Nós vimos que não, dois alunos, não necessariamente, às vezes você tem duas alunas chamadas Fernanda.

Eu tenho duas alunas chamadas Fernanda, mas uma não é exatamente a outra, então existe uma característica que diferencia uma da outra, que diferencia um aluno do outro. Aqui, no nosso caso, foi o CPF, então criamos o CPF. Se dois alunos têm o mesmo nome, tem o mesmo e-mail, o mesmo telefone, mas o CPF é distinto, eu não estou mais falando do mesmo aluno, estou falando de alunos distintos.

Então entidade é esse conceito que diferencia dois objetos. Já a classe CPF, por exemplo, não representamos como string, nós criamos uma classe. CPF, nós criamos essa classe "CPF", mas CPF só tem um atributo número aqui, então CPF não é uma entidade. Se eu tiver dois CPF com o mesmo número, é o mesmo CPF que eu estou falando.

Então CPF não é uma entidade, eu não tenho uma informação que diferencie um CPF do outro. Aqui, no caso, a classe "CPF", a classe "Telefone" também segue esse conceito, e a classe "Email", todas elas não são entidades e sim VOs, value object, que é um outro padrão recomendado no livro de Domain Driven Design.

Então sempre que tivermos conceitos do domínio que o que interessa são os valores em si, não há uma diferença, não há uma propriedade, não há uma característica que diferencie um objeto do outro, então estamos falando do mesmo objeto, aí entra o padrão value object. Então acabamos seguindo esse padrão no nosso código.

O próprio repositório é um padrão forte que existe no Domain Driven Design. É a maneira que nós temos de representar esse conceito de persistência, que é algo técnico, só que aqui não estamos falando de banco de dados, nem de coisas de infraestrutura, estamos falando de um conceito do domínio, que é persistência. Como eu represento esse conceito de persistência, de guardar e recuperar informações de algum lugar?

O Evans, ele citou esse conceito de repositório. Quando falamos de persistência, quando algum especialista do domínio está falando de guardar e buscar informações, ele está indiretamente falando de um repositório, que é um local onde armazenamos e recuperamos informações. Então, no nosso projeto, nós também implementamos esse padrão repository usando essas interfaces, cujas implementações estão na camada de domínio.

Então perceba: esse é o projeto que vamos trabalhar aqui no curso. É o mesmo projeto que desenvolvemos no curso de Clean Architecture da Alura e, indiretamente, como aquele curso focava bastante na arquitetura, no domínio da aplicação, já acabamos implementando vários conceitos do Domain Driven Design e isso vai facilitar bastante nesse treinamento.

Então conceitos de entidade, de VO, de service também, de repositório, vários desses conceitos nós já implementamos no nosso projeto porque o foco era justamente no domínio, então isso vai facilitar muito esse treinamento, não vamos ter que fazer toda uma reestruturação do nosso projeto, acabamos facilitando muito nesse treinamento também. E a separação em camadas, que é um conceito importante

Só que fizemos de uma maneira diferente, para não acoplar o domínio da aplicação com detalhes de infraestrutura, com bibliotecas e frameworks. Então nesse vídeo, esse era o objetivo, mostrar para vocês qual é o projeto que vamos trabalhar no treinamento. E que, indiretamente, para nossa alegria, para a nossa facilidade, no outro treinamento acabamos adotando e seguindo vários dos padrões recomendados pelo Domain Driven Design.

Então isso vai facilitar muito a nossa vida durante esse treinamento. No próximo vídeo começaremos a discutir sobre alguns conceitos do Domain Driven Design e como eles se encaixam no nosso projeto. Espero que vocês tenham gostado do vídeo de hoje. Vejo vocês no próximo vídeo. Um abraço e até lá.