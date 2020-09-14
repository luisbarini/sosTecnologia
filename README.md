# SOS Tecnologia

Para o desenvolvimento desta aplicação foi utilizado:

01. Git Flow
02. Java 14
03. Maven
04. Jquery
05. Layout AdminLTE (utiliza o BootStrap)
06. Select2
07. Spring Boot
08. Spring Data
09. Thymeleaf
10. Lombok
11. Swagger
12. DWR
13. JQuery
14. Flyway
15. Servidor Undertow

## Para utilizar essa aplicação siga os seguintes passos:

Faça o clone da aplicação no GitHub nesse [link](https://github.com/luisbarini/sosTecnologia)

Execute o arquivo **inicializacao.sql** que está disponivel na raiz do projeto. 
Observe que esse arquivo simplesmente cria o usuário que vai acessar o banco e o database.
Exemplo do comando para rodar o script: **mysql -uroot -p mysql < inicializacao.sql**

Na pasta do projeto execute o seguinte comando do Maven **mvn clean install -DskipTests -Pdev** para criar o arquivo jar.
Observe que o comando -P pode ser utilizado para criar um ambiente de desenvolvimento (vai rodar na porta 8080) ou prod para o ambiente de produção (vai rodar na porta 80)

Com o arquivo JAR criado (procure na pasta target) basta executar o comando:

**java -jar desafio_sos_tecnologia.jar**

## Não consegui fazer:
Infelizmente por falta de tempo, não consegui realizar a tarefa de inserir o arquivo PDF no cadastro do patrimônio.

## Camada de serviços
A camada de serviços está presente no link: http://localhost:8080/swagger-ui/index.html