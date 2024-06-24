
<h1 align="center" style="font-weight: bold;">Dynamic Mapper 💻</h1>

<p align="center">
<a href="#description">Descrição</a>
<a href="#technologies">Tecnologias</a>
<a href="#started">Primeiros Passos</a>
<a href="#contribute">Contribute</a> 
</p>


<p align="center">Dynamic Mapper é uma aplicação Java Spring Boot que fornece uma maneira de mapear dinamicamente objetos de um tipo para outro.</p>


<p align="center">
<a href="https://github.com/LucasLimaLL/dynamicmapper">📱 Visit this Project</a>
</p>

<h2 id="description">💻 Descrição</h2>

- Mapeamento dinâmico de objetos: A aplicação pode mapear campos de um objeto para outro, mesmo que os campos não estejam na mesma ordem.
- Suporte para classes sem construtores: A aplicação pode lidar com classes que não têm construtores e apenas métodos setter.
- Mapeamento de tipos fixos: A aplicação suporta o mapeamento de tipos fixos.


<h2 id="technologies">💻 Tecnologias</h2>

- Java
- Spring Boot
- Maven

<h2 id="started">🚀 Primeiros Passos</h2>

Para executar a aplicação, você pode usar o comando java -jar target/dynamic-mapper-0.0.1-SNAPSHOT.jar.

<h3>Pré requisitos</h3>

Para construir a aplicação, você precisa ter o Maven instalado. Em seguida, você pode usar o comando mvn clean install para construir a aplicação.

<h3>Exemplos</h3>


Aqui estão alguns exemplos de como a aplicação pode ser usada:

- Mapear um objeto `Person` para um objeto `Client`:

```java

@Autowired
private MapperUseCase mapperUseCase;

Person person = new Person("John Doe", "john.doe@example.com", "123-456-7890");
Client client = (Client) mapperUseCasemapperUseCase.map(object, Client.class);

```

<h2 id="contribute">📫 Contribuição</h2>

Contribuições são bem-vindas! Por favor, sinta-se à vontade para abrir um problema ou enviar um pull request.

<h3>Documentações que podem ajudar</h3>

[💾 Conventional Commits](https://www.conventionalcommits.org/pt-br/v1.0.0/)<br/>
[💾 Commit pattern](https://medium.com/linkapi-solutions/conventional-commits-pattern-3778d1a1e657)

