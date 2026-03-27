# Checkpoint 1 - Projeto Anime

## Descrição
API REST desenvolvida em Spring Boot para o gerenciamento de um catálogo de animes e seus personagens. A aplicação permite o gerenciamento completo de informações sobre obras e personagens, incluindo filtros por associação.

## Tecnologias
* **Linguagem**: Java 17
* **Framework**: Spring Boot 4.0.4
* **Banco de Dados**: H2 (em memória)
* **Mapeamento Objeto-Relacional**: Spring Data JPA
* **Produtividade**: Lombok

## Estrutura das Entidades
* **Anime**: Armazena título, descrição, data de lançamento e categorias.
* **Personagem**: Armazena nome, personalidade e o vínculo Many-to-One com a entidade Anime.
* **Categorias (Enum)**: Comédia, Ação, Romance, Drama, SciFi, Aventura, Terror e Suspense.

## Endpoints da API

### Health Check
* `GET /`: Retorna o status de funcionamento da API.

### Animes (`/animes`)
* `GET /animes`: Lista todos os animes cadastrados.
* `GET /animes/{id}`: Recupera os dados de um anime por ID.
* `POST /animes`: Registra um novo anime.
* `PUT /animes/{id}`: Atualiza os dados de um anime existente.
* `DELETE /animes/{id}`: Remove um anime e os seus personagens do catálogo.

### Personagens (`/personagens`)
* `GET /personagens`: Lista todos os personagens cadastrados.
* `GET /personagens/{id}`: Recupera os dados de um personagem por ID.
* `GET /personagens/anime/{id}`: Lista todos os personagens vinculados a um anime específico.
* `POST /personagens`: Registra um novo personagem.
* `PUT /personagens/{id}`: Atualiza os dados de um personagem existente.
* `DELETE /personagens/{id}`: Remove um personagem do sistema.

## Configuração e Execução
A aplicação utiliza o banco de dados H2 configurado com a URL `jdbc:h2:mem:animes`.

## Prints do projeto funcionando

![Print da requisição POST Anime](docs/images/Post%20Anime%20Shikimori.png)

![Print da requisição POST Anime](docs/images/Post%20Anime%20Bocchi%20The%20Rock.png)

![Print da requisição POST Anime](docs/images/Post%20Anime%20Bocchi%20The%20Rock.png)

