# Ponto Eletrônico

#### Tecnologias Utilizadas
- Spring Boot
- Liquibase
- Swagger
- Postgresql
- Docker

## Instruções
#### Criar o container do Postgresql
- Acessar o diretório .docker e executar o comando abaixo
```
docker-compose up
```
- Obs: O schema será criado durante a inicialização do container e tabelas do banco de dados serão criadas através do Liquibase. 

### Executar o projeto
- Entrar no diretório raiz do projeto e executar o comando
```
.\gradlew bootRun
```

### Endereço da documentação das APIs
- Url Swagger
```
http://localhost:8080/pontoeletronico/swagger-ui.html
```

### Exemplo Funcionalidade Registrar Horário
- URL
```
POST http://localhost:8080/pontoeletronico/v1/registro-horarios
```
- Registrar Entrada
```
{
	"matricula": 1,
	"hora": "17:00",
	"data": "2020-10-16",
	"tipoHorarioEnum": "ENTRADA"
}
```

- Registrar Saída Almoço
```
{
	"matricula": 1,
	"hora": "12:00",
	"data": "2020-10-15",
	"tipoHorarioEnum": "SAIDA_ALMOCO"
}
```

- Registrar Retorno Almoço
```
{
	"matricula": 1,
	"hora": "13:00",
	"data": "2020-10-15",
	"tipoHorarioEnum": "RETORNO_ALMOCO"
}
```

- Registrar Saída
```
{
	"matricula": 1,
	"hora": "17:00",
	"data": "2020-10-15",
	"tipoHorarioEnum": "SAIDA"
}
```

### Exemplo Funcionalidade Alocar Hora
- URL
```
POST http://localhost:8080/pontoeletronico/v1/alocacao-horas
```
- Alocar Hora
```
{
	"matricula": 1,
	"codigoProjeto": 1,
	"horas": "5:00",
	"data": "2020-10-15"
}
```

### Exemplo Funcionalidade Calculo Horas
- Alocar Hora
```
GET http://localhost:8080/pontoeletronico/v1/registro-horarios/1/banco-horas/10/2020
```

