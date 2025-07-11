## School Management

## Pré-requisitos
- Java 17+
- PostgreSQL

## Setup

1. Crie o banco `school_db` no PostgreSQL
2. Ajuste `application.properties` com seu usuário e senha do banco
3. Rode o projeto no IntelliJ

## Rotas principais

- **Login:** `POST /api/auth/login`
    - body: `{ "username": "professor1", "password": "123" }`
    - retorna: `{ "token": "..." }`
- **Matricular aluno:** `POST /api/courses/{courseId}/enroll/{studentId}`
    - *Somente ROLE_ADMIN (professor logado)*

## Criando usuários iniciais
Inclua no banco:
```sql
INSERT INTO "user"(username, password, role)
VALUES ('professor1', '$2a$10$HASHDA SENHA', 'ROLE_ADMIN'); -- use senha criptografada BCrypt
INSERT INTO "user"(username, password, role)
VALUES ('aluno1', '$2a$10$HASHDA SENHA', 'ROLE_USER');



