# Projeto Java Spring Boot - API com Autenticação JWT
Este projeto é uma API RESTful desenvolvida com Spring Boot que utiliza autenticação JWT para proteger rotas e controlar o acesso com base em permissões de usuários (roles). O sistema implementa controle de acesso baseado em perfis de usuário: ADMIN e USER.

---
Matheus Magalhães Schorro 23086073-2
Matheus de Albuquerque 23123563-2
Robson Akagui 23105185-2
Paublo Basso Bessa 23167886-2
---

## 🔐 Autenticação e Autorização
A segurança é implementada com:

JWT (JSON Web Tokens) para autenticação

Spring Security para controle de acesso por rotas

Perfis de usuário: ADMIN, USER

---
## 📌 Rotas da API 
### 🔓 LIBERADAS (Acesso público, sem autenticação)
Método	Endpoint	Descrição
POST	/authorization/login	Login do usuário
POST	/authorization/register	Registro de novo usuário

### 🛡️ ADMIN (Requer autenticação com perfil ADMIN)
Método	Endpoint	Descrição
GET	/user	Listar todos os usuários
PUT	/user/update/{id}	Atualizar dados de um usuário
DELETE	/user/delete/{id}	Excluir um usuário específico

### 👤 USER (Requer autenticação, qualquer usuário logado)
Método	Endpoint	Descrição
GET	/user/self	Obter informações do próprio usuário
PUT	/user/self/update	Atualizar dados do próprio usuário

---

## ⚙️ Tecnologias Utilizadas
Java 17+

Spring Boot

Spring Security

JWT (Autenticação)

Maven

Banco de Dados Relacional H2

---

## ▶️ Como executar o projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/Magalzin/Prova-Eliel-ADSIS.git
   
   ```

2. Acesse o diretório do projeto:

   ```bash
   cd Prova-Eliel-ADSIS
   ```

3. Configure o `application.properties` ou `application.yml` com:

   * Dados do banco de dados
   * Chave secreta do JWT

4. Compile e rode:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧪 Testes com JWT

1. Faça um `POST /auth/login` com email e senha válidos.
2. Copie o token JWT retornado.
3. Use o token no `Authorization Header` como:

   ```
   Authorization: Bearer <seu-token>
   ```

---

