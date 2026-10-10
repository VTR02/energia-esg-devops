# Projeto - Cidades ESG Inteligentes

## API ESG - Eficiência Energética e Sustentabilidade

Projeto desenvolvido para a disciplina de DevOps da FIAP, utilizando uma aplicação Java Spring Boot com foco em ESG (Environmental, Social and Governance).

O sistema tem como objetivo auxiliar no controle e monitoramento de consumo energético, permitindo o gerenciamento de equipamentos, registros de consumo, limites de consumo, alertas e desligamentos.

---

## Integrantes

- Nome: Vitor Hugo Araujo Brandão 
- Nome: VINICIUS MARTINS GALVARINO
- Nome: Alessandro Ribeiro

---

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Jakarta Validation
- Maven
- Oracle XE
- H2 Database para ambiente cloud
- Flyway
- Eureka Server
- Docker
- Docker Compose
- GitHub
- GitHub Actions
- GitHub Container Registry (GHCR)
- Microsoft Azure
- Azure Container Apps

---

## Arquitetura do projeto

A solução é composta pelos seguintes serviços:

- energia-ms: microsserviço principal responsável pelas funcionalidades relacionadas ao consumo energético.
- eureka-server: servidor de descoberta de serviços.
- oracle-xe: banco de dados Oracle utilizado no ambiente local com Docker Compose.

No ambiente em nuvem foi utilizado um perfil específico chamado `cloud`, configurado para utilizar banco H2 em memória e desabilitar o registro no Eureka.

---

## Como executar localmente com Docker

### Pré-requisitos

É necessário possuir:

- Docker
- Docker Compose
- Git

Clone o projeto e acesse a pasta:

```bash
git clone <URL_DO_REPOSITORIO>
cd energia-esg-devops
