# Farmacia Social API

##### Sobre projeto

O projeto Famácia Social é uma proposta de projeto integrador que tem a finalidade de auxiliar usuários na doação gratuito de medicamentos entre pessoas que tem uma determinada medicação que não será usada e pessoas que precisam de uma medicação específica.

# Tecnologias utilizadas

- [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Framework Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)

# Instalação

##### Pré requisitos:

- [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

```sh
$ cd farmacia-social-api
```

Copie o conteúdo do arquivo application.properties.example que fica dentro de /src/main/resources
e crie um novo arquivo chamado application.properties dentro do mesmo diretório, cole o conteúdo copiado do
primeiro arquivo dentro deste novo arquivo. Troque onde está NOME_DO_DB pelo nome do seu banco de dados,
troque USUARIO_BD pelo usuário de acesso ao seu banco de dados e troque SENHA_BD pela senha de acesso ao seu banco de dados.

```sh
$ ./mvnw spring-boot:run
```
