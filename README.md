# API de Gestão - Papelaria Candy

API RESTful desenvolvida como parte de um projeto de sistema de gestão, utilizando Java 17 e o ecossistema Spring Boot. A API é responsável por gerenciar produtos, categorias, estoque e vendas.

## ✨ Funcionalidades Principais

* **CRUD de Produtos:** Operações completas de Criar, Ler, Atualizar e Deletar produtos.
* **CRUD de Categorias:** Gerenciamento das categorias de produtos.
* **Relacionamentos:** Uso de `@ManyToOne` para associar Produtos a Categorias.
* **Padrão DTO:** Utilização de Data Transfer Objects (DTOs) para a criação e atualização de entidades.
* **Controle de Estoque:** Endpoints `PATCH` dedicados para adicionar e remover itens do estoque.
* **Lógica de Vendas:** Endpoint `POST` transacional (`@Transactional`) que valida o estoque, registra a venda e dá baixa nos produtos.

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA** (Hibernate)
* **MySQL** (Banco de Dados)
* **Maven** (Gerenciador de Dependências)
* **Lombok**
* **DBeaver** (Gerenciador de Banco de Dados)
* **Postman** (Ferramenta de Teste de API)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* JDK 17 ou superior
* Maven 3.x
* Um servidor MySQL rodando.
* Um cliente de banco de dados (como **DBeaver** ou MySQL Workbench).

### 1. Configuração do Banco de Dados

1.  Usando o **DBeaver**, crie um novo banco de dados (schema) no seu MySQL com o nome `gestao_papelaria`.
2.  Abra o arquivo `src/main/resources/application.properties`.
3.  Configure suas credenciais de acesso ao MySQL:

    ```properties
    # Configuração para o MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/gestao_papelaria
    spring.datasource.username=SEU_USUARIO_AQUI
    spring.datasource.password=SUA_SENHA_AQUI
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

### 2. Rodando a Aplicação

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/NataniMoraes/gestao-papelaria-api.git](https://github.com/NataniMoraes/gestao-papelaria-api.git)
    cd gestao-papelaria-api
    ```

2.  Use o Maven para compilar e rodar o projeto:
    ```bash
    mvn spring-boot:run
    ```

3.  A API estará disponível em `http://localhost:8080`.

---

### 3. Script de Carga (Opcional)

Para testar a API com dados prontos, um script SQL (`data-load-script.sql`) está incluído na raiz deste projeto. Você pode executá-lo no **DBeaver** (após configurar o banco) para popular as tabelas de `categorias` e `produtos` com dados de exemplo.

---

## Endpoints da API (Teste com Postman)

Você pode testar todos estes endpoints usando o **Postman** ou uma ferramenta similar.

### Produtos (`/produtos`)

| Método | Rota | Função |
| :--- | :--- | :--- |
| `GET` | `/produtos` | Lista todos os produtos. |
| `GET` | `/produtos/{id}` | Busca um produto pelo ID. |
| `POST` | `/produtos` | Cria um novo produto (envia um `ProdutoDTO`). |
| `PUT` | `/produtos/{id}` | Atualiza um produto (envia um `ProdutoDTO`). |
| `DELETE` | `/produtos/{id}` | Exclui um produto. |
| `PATCH` | `/produtos/{id}/adicionar-estoque` | Adiciona ao estoque (envia um `Integer`). |
| `PATCH` | `/produtos/{id}/remover-estoque` | Remove do estoque (envia um `Integer`). |

### Categorias (`/categorias`)

| Método | Rota | Função |
| :--- | :--- | :--- |
| `GET` | `/categorias` | Lista todas as categorias. |
| `POST` | `/categorias` | Cria uma nova categoria. |
| `PUT` | `/categorias/{id}` | Atualiza uma categoria. |
| `DELETE` | `/categorias/{id}` | Exclui uma categoria. |

### Vendas (`/vendas`)

| Método | Rota | Função |
| :--- | :--- | :--- |
| `POST` | `/vendas` | Realiza uma nova venda (envia `VendaRequestDTO`). |
