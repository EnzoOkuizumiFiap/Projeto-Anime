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
* **Categorias (Enum)**: Comedia,
  Acao, Romance, Drama, Scifi, Aventura, Terror, Suspense, Cyberpunk, Musical e SliceOfLife.

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

---

### Get Health Check

![Print da requisição Get Anime](docs/images/Get%20Health%20Check.png)

---

#### Comandos utilizados no Insomnia: [docs/CRUD_ProjetoAnime.txt](docs/CRUD_ProjetoAnime.txt)

## Prints do projeto funcionando

### Post ANIMES
![Print da requisição POST Anime](docs/images/Post%20Anime%20Shikimori.png)

![Print da requisição POST Anime](docs/images/Post%20Anime%20Mardock%20Scramble.png)

![Print da requisição POST Anime](docs/images/Post%20Anime%20Bocchi%20The%20Rock.png)

### Post PERSONAGENS

![Print da requisição POST Anime](docs/images/Post%20Personagem%20Shikimori.png)

![Print da requisição POST Anime](docs/images/Post%20Personagem%20Yuu%20Izumi.png)

![Print da requisição POST Anime](docs/images/Post%20Personagem%20Rune%20Balot.png)

![Print da requisição POST Anime](docs/images/Post%20Personagem%20Hitori%20Gotoh.png)


### Update ANIME e PERSONAGEM

![Print da requisição Update Anime](docs/images/Update%20Anime%20Shikimori.png)

![Print da requisição Update Anime](docs/images/Update%20Personagem%20Hitori%20Gotoh.png)

### Get ANIMES e PERSONAGENS

### Get All Animes
![Print da requisição Get Anime](docs/images/Get%20All%20Animes.png)

### Get Anime By Id
![Print da requisição Get Anime](docs/images/Get%20Anime%20By%20Id.png)

### Get Personagens by Anime Id

#### Shikimori's Not Just a Cutie
![Print da requisição Get Anime](docs/images/Get%20Personagens%20By%20Anime%20Id%20Shikimori.png)

#### Mardock Scramble 
![Print da requisição Get Anime](docs/images/Get%20Personagens%20By%20Anime%20Id%20Rune.png)

#### Bocchi The Rock
![Print da requisição Get Anime](docs/images/Get%20Personagens%20By%20Anime%20Id%20Hitori.png)

### Get Personagens By Id

![Print da requisição Get Anime](docs/images/Get%20Personagens%20By%20Id.png)

### Delete Anime e Personagem

#### Deletando Anime Mardock Scramble
![Print da requisição Delete Anime](docs/images/Delete%20Anime.png)

#### Get depois do Delete do Anime Mardock Scramble

#### Get All Animes
![Print da requisição Delete Anime](docs/images/Get%20depois%20do%20Delete%20do%20Anime%20Mardock%20Scramble.png)

#### Get All Personagens - Note que a Personagem Rune Balot também foi removida depois do Delete
![Print da requisição Delete Anime](docs/images/Get%20Personagens%20depois%20do%20Delete%20do%20Anime%20Mardock%20Scramble.png)

#### Deletando Personagem Micchon Shikimori 
![Print da requisição Delete Anime](docs/images/Delete%20Personagem.png)

#### Get depois do Delete da Personagem Shikimori
![Print da requisição Delete Anime](docs/images/Get%20depois%20do%20Delete%20da%20Personagem%20Shikimori.png)

