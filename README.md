# 📦 Inventory Management API

[![API Live no Render](https://img.shields.io/badge/Swagger-Testar_API_na_Nuvem-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://inventory-api-yvvz.onrender.com/swagger-ui/index.html)

Uma API RESTful corporativa para gestão de estoque, controle de movimentações e geração de relatórios de produtos e categorias.

## 🚀 Tecnologias Utilizadas
* **Linguagem:** Java 25
* **Framework:** Spring Boot 3.5.x
* **Banco de Dados:** PostgreSQL 16
* **Segurança:** Spring Security + JWT (Auth0)
* **Infraestrutura:** Docker & Docker Compose
* **Documentação:** Swagger (OpenAPI 3.0)

## ⚙️ Arquitetura
O projeto segue o padrão de design em camadas (Controller -> Service -> Repository -> Entity), garantindo isolamento de responsabilidades e facilitando a manutenção.
* **Transações Seguras:** Uso de `@Transactional` para garantir integridade no banco de dados durante cálculos de entrada e saída de estoque.
* **Autenticação Stateless:** Tokens JWT para proteção de rotas, dispensando o uso de sessões no servidor.

## 🛠️ Como executar localmente

1. Certifique-se de ter o **Docker** e o **Docker Compose** instalados na sua máquina.
2. Clone este repositório:
   ```bash
   git clone https://github.com/WillbioCloud/inventory-api.git
Acesse a pasta do projeto e suba os containers (Banco de Dados + API):


docker compose up -d --build

A API estará disponível na porta 8080.

  ```markdown
📚 Documentação da API (Swagger)
Com a aplicação rodando, acesse a interface interativa do Swagger para testar os endpoints:

Em Produção (Nuvem): Acessar Swagger Live

Localhost: http://localhost:8080/swagger-ui/index.html
