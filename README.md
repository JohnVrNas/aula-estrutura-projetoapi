# 🎬🍿 Plataforma de Lista de Filmes

## 📌 Sobre o Projeto
Este projeto é uma plataforma simples para registrar e avaliar filmes assistidos. Permite que os usuários adicionem filmes, atribuam notas e façam comentários.
O projeto foi feito usando a metodologia Kanban.
---

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Lombok**
- **Maven**
- **H2 Database**

---

## 📁 Endpoints

## Filme

### ➕ Criar um Filme

**POST** `/criarfilme`  
Cria um novo filme no sistema.

#### 🔸 Corpo da Requisição
```json
{
  "titulo": "Interestellar",
  "data": "14/03/2025",
  "comentarios": "Melhor filme da minha vida!",
  "nota": "5.0",
  "gostou": true
}
```
#### 🔸 Respostas
- 201 Created – Filme criado com sucesso.

- 409 Conflict – Filme já existe na lista.

### 📃 Listar Todos os Filmes
**GET** `/listafilme`  
Retorna todos os filmes cadastrados.

```
 {
    "id": "uuid",
    "titulo": "Interestellar",
    "data": "14/03/2025",
    "comentarios": "Melhor filme da minha vida!",
    "nota": "5.0",
    "gostou": true
  }
```

### 🗑️ Deletar um Filme
**DELETE** `/deletarfilme/{id}`  
Remove um filme a partir do ID.

🔸 Parâmetros
id (UUID): Identificador único do filme.

#### 🔸 Respostas
- 204 No Content – Filme deletado com sucesso.

- 404 Not Found – Filme não encontrado.


### 🔄 Atualizar um Filme
**PUT** `/atualizarfilme/{id}`

Atualiza os dados de um filme existente.

🔸 Parâmetros
id (UUID): Identificador único do filme.

🔸 Corpo da Requisição

```
{
  "titulo": "Interestellar",
  "data": "14/03/2025",
  "comentarios": "Melhor filme da minha vida!",
  "nota": "5.0",
  "gostou": true
}
```

#### 🔸Respostas

- *200 OK – Filme atualizado.*

- *404 Not Found – Filme não encontrado.*


## Usuario

### ➕ Criar um Usuario

**POST** `/user`  
Cria um novo user no sistema.

#### 🔸 Corpo da Requisição
```json
{
    "nome":"Joao Rodrigues",
    "username":"john",
    "senha":"123456",
    "telefone":"505",
    "email":"joao@gmail.com"
}
```

#### 🔸 Respostas
- 201 Created – User criado com sucesso.

- 409 Conflict – User já existe na lista.

### 📃 Listar Todos os Usários
**GET** `/usercadastrados`  
Retorna todos os filmes cadastrados.

```
{
    "id": UUID,
    "nome":"Joao Rodrigues",
    "username":"john",
    "senha":"123456",
    "telefone":"505",
    "email":"joao@gmail.com"
}
```

### 🗑️ Deletar um Usuário
**DELETE** `/deleteuser/{id}`  
Remove um User a partir do ID.

🔸 Parâmetros
id (UUID): Identificador único do Usuário.

#### 🔸 Respostas
- 204 No Content – User deletado com sucesso.

- 404 Not Found – User não encontrado.


### 🔄 Atualizar um User
**PUT** `/atualiza/{id}`

Atualiza os dados de um user existente.

🔸 Parâmetros
id (UUID): Identificador único do user.

🔸 Corpo da Requisição

```
{
    "id": UUID,
    "nome":"Joao Rodrigues",
    "username":"john",
    "senha":"123456",
    "telefone":"505",
    "email":"joao@gmail.com"
}
```

#### 🔸Respostas

- *200 OK – User atualizado.*

- *404 Not Found – User não encontrado.*


## 📌 Autor
**João Victor Rodrigues do Nascimento - RGM: 11232101507**

**Wellingto de Castro - RGM: 11232101354**


