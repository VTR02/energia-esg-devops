Projeto - Cidades ESG Inteligentes
API ESG - Eficiência Energética e Sustentabilidade
Projeto desenvolvido para a atividade Navegando pelo Mundo DevOps - FIAP, utilizando uma aplicação Java Spring Boot com foco em ESG (Environmental, Social and Governance).
O sistema apoia o monitoramento de eficiência energética por meio do cadastro de equipamentos, registros de consumo, limites de consumo, alertas e desligamentos.

Integrantes
- Vitor Hugo Araujo Brandão
- Vinicius Martins Galvarino
- Alessandro Ribeiro

Tecnologias utilizadas
- Java 21
- Spring Boot 3.5.x
- Spring Data JPA
- Spring Security
- Jakarta Validation
- Maven / Maven Wrapper
- Oracle XE 21 Slim
- H2 Database para o perfil cloud
- Flyway
- Eureka Server / Eureka Client
- Docker
- Docker Compose
- GitHub
- GitHub Actions
- GitHub Container Registry (GHCR)
- Microsoft Azure
- Azure Container Apps

Arquitetura do projeto
A solução é composta por três serviços principais no ambiente local:
- energia-ms: microsserviço principal, exposto na porta 8081.
- eureka-server: serviço de descoberta, exposto na porta 5050.
- oracle-xe: banco Oracle local, exposto na porta 1521.

Fluxo local simplificado:
Cliente
  |
  v
energia-ms :8081
  |       \
  |        \--> eureka-server :5050
  v
oracle-xe :1521
No Azure é utilizado o perfil cloud, que configura H2 em memória e desabilita o registro no Eureka para permitir a execução independente do microsserviço nos Azure Container Apps.
Observação: o H2 utilizado no perfil cloud é em memória. Portanto, os dados do ambiente cloud não devem ser tratados como persistência definitiva após reinicializações do container.

Como executar localmente com Docker
Pré-requisitos
- Docker Desktop
- Docker Compose
- Git

1. Clonar o repositório
git clone https://github.com/VTR02/energia-esg-devops.git
cd energia-esg-devops

2. Criar o arquivo .env
No Windows PowerShell:
Copy-Item .env.example .env
Em Linux/macOS:
cp .env.example .env
O arquivo .env.example contém:
ORACLE_PASSWORD=oracle
DB_USERNAME=system
DB_PASSWORD=oracle

3. Subir os serviços
docker compose up -d --build

4. Verificar os containers
docker compose ps
Resultado esperado:
- oracle-xe: healthy
- eureka-server: Up
- energia-ms: Up

5. Testar a API local
curl http://localhost:8081/equipamentos

6. Acessar o Eureka
Abra no navegador:
http://localhost:5050
A aplicação ENERGIA-MS deve aparecer registrada com status UP.

7. Encerrar os serviços
docker compose down
Containerização e orquestração
Dockerfile do energia-ms
O Dockerfile utiliza build multi-stage:
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
O Eureka utiliza a mesma estratégia, expondo a porta 5050.
Docker Compose
O docker-compose.yml orquestra:
- oracle-xe
- eureka-server
- energia-ms
O ambiente utiliza:
- volume oracle-data para os dados do Oracle;
- rede energia-network para comunicação entre containers;
- variáveis de ambiente para banco e Eureka;
- healthcheck no Oracle;
- depends_on para controlar a ordem de inicialização.
Banco de dados e Flyway
No ambiente local, o serviço utiliza Oracle XE.
As migrations versionadas são:
V1__create_table_equipamento.sql
V2__create_table_consumo_energia.sql
V3__create_table_limite_consumo.sql
V4__create_table_alerta_consumo.sql
V5__create_table_desligamento.sql
Configuração relevante do Flyway:
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=0
No perfil cloud, o Flyway é desabilitado porque o banco utilizado é H2 em memória com ddl-auto=update.
Endpoints da API

Recurso	        GET	    POST	    PUT	                    DELETE
/equipamentos	  ✅	    ✅	     /equipamentos/{id}	    /equipamentos/{id}
/consumo	      ✅	    ✅	     /consumo/{id}	        /consumo/{id}
/limites	      ✅	    ✅	     /limites/{id}	        /limites/{id}
/alertas	      ✅	    ✅	     /alertas/{id}	        /alertas/{id}
/desligamentos	✅	    ✅	     /desligamentos/{id}    /desligamentos/{id}


Status HTTP validados durante os testes:
- 200 OK
- 201 Created
- 204 No Content
- 400 Bad Request para dados inválidos
Pipeline CI/CD

O pipeline está em:
.github/workflows/ci-cd.yml
Ferramenta utilizada: GitHub Actions.
Gatilhos
push -> main
push -> develop
pull_request -> main
Etapas do pipeline

1. Build e Testes Eureka
   - Java 21 Temurin
   - ./mvnw clean package -DskipTests
   - ./mvnw test

2. Build e Testes Energia MS
   - sobe um Oracle XE como service do job;
   - executa build e testes do energia-ms.

3. Docker Build e Publicação GHCR
   - build da imagem do Eureka;
   - build da imagem do energia-ms;
   - publicação no GitHub Container Registry em pushes.

4. Deploy Staging Azure
   - executado automaticamente em push na branch develop.

5. Deploy Produção Azure
   - executado automaticamente em push na branch main.

Fluxo resumido:
Desenvolvimento
      |
      v
   develop
      |
      v
Build + Testes
      |
      v
Docker Build
      |
      v
     GHCR
      |
      v
Staging Azure
      |
      v
Validação / Pull Request
      |
      v
     main
      |
      v
Produção Azure

Imagem Docker publicada

Imagem principal:
ghcr.io/vtr02/energia-ms:latest

O pipeline também publica uma tag imutável baseada no SHA do commit:
ghcr.io/vtr02/energia-ms:<github-sha>

Ambientes Azure

Staging
Aplicação:
energia-esg-staging

URL:
https://energia-esg-staging.jollyplant-4a80fd66.northcentralus.azurecontainerapps.io
O staging foi utilizado para validar os CRUDs antes da promoção para produção.

Produção
Aplicação:
energia-esg-prod

URL:
https://energia-esg-prod.jollyplant-4a80fd66.northcentralus.azurecontainerapps.io

A revisão de produção foi validada com:
{
  "healthState": "Healthy",
  "replicas": 1,
  "runningState": "Running"
}
O endpoint GET /equipamentos respondeu com HTTP 200 em produção.

Evidências e links
- Repositório: https://github.com/VTR02/energia-esg-devops
- GitHub Actions: https://github.com/VTR02/energia-esg-devops/actions
- Staging: https://energia-esg-staging.jollyplant-4a80fd66.northcentralus.azurecontainerapps.io
- Produção: https://energia-esg-prod.jollyplant-4a80fd66.northcentralus.azurecontainerapps.io
As capturas de tela de build, testes, deploy, execução local, staging e produção estão organizadas na documentação técnica em PDF entregue junto ao projeto.

Desafios encontrados e soluções

1. Testes dependentes do Oracle no CI
Problema: os testes falhavam quando a aplicação não conseguia conectar ao Oracle durante o GitHub Actions.
Solução: foi adicionado um Oracle XE como service no job de testes do energia-ms, com healthcheck e variáveis de ambiente adequadas.

2. Baseline do Flyway
Problema: a aplicação apresentou falha de migration durante a evolução do banco.
Solução: foi ajustado spring.flyway.baseline-on-migrate=true e spring.flyway.baseline-version=0, mantendo as migrations versionadas de V1 a V5.

3. Execução da aplicação no Azure
Problema: a configuração padrão dependia de Oracle e Eureka locais, o que impedia a inicialização do container no ambiente cloud.
Solução: foi criado application-cloud.properties, ativado por SPRING_PROFILES_ACTIVE=cloud, utilizando H2 em memória e desabilitando o Eureka Client no Azure.

4. Restrições de região no Azure
Problema: a assinatura possuía política de regiões permitidas.
Solução: o ambiente foi criado em North Central US, região permitida pela política da assinatura.

5. Separação entre staging e produção
Problema: era necessário impedir que alterações chegassem diretamente à produção sem validação.
Solução: foi adotado o fluxo develop -> staging -> Pull Request -> main -> produção.
Checklist de entrega

Item	OK
Projeto compactado em .ZIP com estrutura organizada	☐
Dockerfile funcional	☑
docker-compose.yml ou arquivos Kubernetes	☑
Pipeline com etapas de build, teste e deploy	☑
README.md com instruções e evidências/links	☑
Documentação técnica com evidências (PDF)	☑
Deploy realizado nos ambientes staging e produção	☑



Status final
Build                         OK
Testes automatizados          OK
Docker                        OK
Docker Compose                OK
Eureka local                  OK
Oracle local                  OK / Healthy
GitHub Actions                OK
GHCR                          OK
Staging Azure                 OK
Produção Azure                OK
Revisão de produção           Healthy / Running
