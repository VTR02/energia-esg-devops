# API ESG - Eficiência Energética e Sustentabilidade

## Descrição

Projeto desenvolvido utilizando Spring Boot com foco em ESG (Environmental, Social and Governance), no tema:

Eficiência energética e sustentabilidade.

O sistema realiza monitoramento de equipamentos, consumo energético, limites de consumo, alertas automáticos e desligamentos programados.

---

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Eureka Server
- Oracle XE
- Flyway
- Docker
- Maven

---

## Como executar o projeto

### 1 - Iniciar Oracle pelo Docker

Executar:

docker-compose up -d

---

### 2 - Executar Eureka Server

Abrir projeto:

eureka-server

Executar:

EurekaServerApplication

Acessar:

http://localhost:5050

---

### 3 - Executar microsserviço

Abrir projeto:

energia-ms

Executar:

EnergiaMsApplication

Acessar:

http://localhost:8081

---

## Endpoints disponíveis

### Equipamentos

GET /equipamentos

POST /equipamentos

PUT /equipamentos/{id}

DELETE /equipamentos/{id}

---

### Consumo

GET /consumo

POST /consumo

PUT /consumo/{id}

DELETE /consumo/{id}

---

### Limites

GET /limites

POST /limites

---

### Alertas

GET /alertas

POST /alertas

---

### Desligamentos

GET /desligamentos

POST /desligamentos

---

## Banco de dados

Oracle XE executado via Docker:

gvenzl/oracle-xe:21-slim

Porta:

1521

Usuário:

system

Senha:

oracle