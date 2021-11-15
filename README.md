# Desafio Builder

Este projeto foi desenvolvido utilizando as tecnologias e conceitos abaixo:

- Java 11
- Spring boot
- Banco de dados postgres
- Banco de dados h2 (para teste de componente)
- Lombok
- ModelMapper
- Jacoco
- Sonar
- JUnit
- Exception Handler
- Pageable
- Postman Json
- Actuator (http://localhost:8090/actuator/health)
- Swagger (http://localhost:8090/swagger-ui.html)
- Docker Compose (para a aplicação e o banco de dados postgres)

## Installation

Para executar o projeto é ncessário ter o Docker e Docker Compose instalado na máquina.

Com o docker compose instalado vá até a raiz do projeto (diretório que se encontra o docker-compose.yml).
Execute os dois comando abaixo:

```sh
-> docker-compose build
-> docker-compose up
```

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.

O projeto estará disponível no endereço abaixo:

```sh
http://localhost:8090/clientes
```
