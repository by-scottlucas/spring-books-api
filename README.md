# Spring - Books API

## 📌 Introdução

O **Spring - Books API** é uma **API RESTful** desenvolvida em **Java** com **Spring Boot**, focada no gerenciamento de usuários, autenticação com **JWT** e registro de livros lidos.
O projeto implementa operações de **CRUD** e autenticação segura, utilizando **Spring Security** e **BCrypt** para proteção de dados.

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para rodar o projeto localmente:

1. **Clone o repositório**

```bash
git clone https://github.com/by-scottlucas/spring-books-api.git
```

2. **Acesse o diretório do projeto**

```bash
cd spring-books-api
```

3. **Crie o arquivo `.env` com base no `.env.example`**

```env
DB_URL=jdbc:h2:mem:testdb              
DB_DRIVER=org.h2.Driver                
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password

H2_CONSOLE_ENABLED=true
H2_CONSOLE_PATH=/h2-console

JPA_PLATFORM=org.hibernate.dialect.H2Dialect   
JPA_DDL_AUTO=create                            

JWT_SECRET_KEY=YOUR_SECRET                    
JWT_EXPIRATION_IN_MS=86400000                 
```

4. **Instale as dependências**

```bash
./mvnw install
```

5. **Inicie a aplicação**

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 📂 Estrutura do Projeto

```bash
src/main/java/br/com/spring/books/
│
├── configs/         # Configurações da aplicação (CORS, segurança, etc.)
├── controllers/     # Controladores da API (endpoints)
├── exceptions/      # Classes para tratamento de erros e exceções personalizadas
├── models/          # Entidades e DTOs da aplicação
├── repositories/    # Interfaces de acesso ao banco de dados (Spring Data JPA)
└── services/        # Camada de regras de negócio e integração entre controller e repositório
```

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+** – Linguagem de programação
* **Spring Boot** – Framework para desenvolvimento rápido de APIs REST
* **Spring Data JPA** – Abstração para persistência de dados
* **Spring Security** – Implementação de autenticação e autorização
* **JWT (JSON Web Token)** – Autenticação segura via token
* **BCrypt** – Hashing seguro de senhas
* **Lombok** – Redução de boilerplate
* **H2 Database** – Banco de dados em memória para testes
* **Maven** – Gerenciador de dependências

---

## 📦 Endpoints da API

### Autenticação (`/api/v1/auth`)

* `POST /register` – Registra um novo usuário
* `POST /login` – Autentica um usuário e retorna o token JWT

### Usuários (`/api/v1/users`)

* `GET /` – Lista todos os usuários
* `GET /{userId}` – Retorna informações de um usuário
* `PUT /{userId}` – Atualiza os dados de um usuário
* `DELETE /{userId}` – Remove um usuário

### Livros Lidos (`/api/v1/books/read`)

* `GET /` – Lista todos os livros lidos pelo usuário autenticado
* `GET /{bookId}` – Obtém os detalhes de um livro lido
* `POST /` – Registra um novo livro lido
* `PUT /{bookId}` – Atualiza os dados de um livro lido
* `DELETE /{bookId}` – Remove um livro da lista de lidos

---

## 📜 Licença

Este projeto está licenciado sob a [**Licença MIT**](./LICENSE).

---

## 👨‍💻 Autor

Este projeto foi desenvolvido por **Lucas Santos Silva**, Desenvolvedor Full Stack, graduado pela **Escola Técnica do Estado de São Paulo (ETEC)** nos cursos de **Informática (Suporte)** e **Informática para Internet**.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge\&logo=linkedin\&logoColor=white)](https://www.linkedin.com/in/bylucasss/)
