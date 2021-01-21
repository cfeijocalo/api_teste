## Tecnologias Utilizadas
 - Spring Boot 2.4.2
 - Java 1.8
 - Maven 3.6.2
 - Lombok 1.8
 - Swagger 2
 - Embedded H2

## Executar Aplicação
Para utilizar a aplicação basta clonar este repositório e na pasta raiz do projeto utilizar o comando 
``` mvn spring-boot:run ``` (Certifique-se que possua o Maven instalado e configurado, caso necessário [Maven](http://maven.apache.org/install.html))

Para compilar e gerar build do projeto utilize os comando ```mvn compile``` e ```mvn package``` este comando deverá gerar um executável.

## Observações
Para que não haja problemas com a visualização do código, configure sua IDE para aceitar as anotações do Lombok (Mais info [Lombok](https://projectlombok.org/))

A aplicação está configurada para acessar o console do h2 pelo path ```/api/h2-console```

O Swagger está disponível no path ```/api/swagger-ui.html```

:smile:
