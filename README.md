# Checkpoint 1 - Projeto Anime

## Descrição
API REST desenvolvida em Spring Boot para o gerenciamento de um catálogo de animes, personagens e temporadas. A aplicação permite o gerenciamento completo de informações sobre obras, personagens e temporadas, incluindo filtros avançados, paginação e validações customizadas.

## Tecnologias
* **Linguagem**: Java 17
* **Framework**: Spring Boot 4.0.4
* **Banco de Dados**: H2 (em memória)
* **Mapeamento Objeto-Relacional**: Spring Data JPA
* **Validação**: Spring Boot Starter Validation (Bean Validation com Jakarta)
* **Produtividade**: Lombok, Spring Boot DevTools
* **Console H2**: spring-boot-h2console

## Estrutura das Entidades

### Anime
Armazena título, descrição, data de lançamento e categorias.
* Relacionamento **One-to-Many** com Personagem (cascade ALL)
* Relacionamento **One-to-Many** com Temporada (cascade ALL)

### Personagem
Armazena nome, personalidade e o vínculo **Many-to-One** com a entidade Anime.

### Temporada
Armazena número da temporada, quantidade de episódios, data de lançamento e o vínculo **Many-to-One** com a entidade Anime.

### Categorias (Enum)
`COMEDIA`, `ACAO`, `ROMANCE`, `DRAMA`, `FANTASIA`, `ESPORTE`, `MISTERIO`, `SCIFI`, `AVENTURA`, `TERROR`, `SUSPENSE`, `CYBERPUNK`, `MUSICAL`, `SLICEOFLIFE`

---

## Endpoints da API

### **Arquivo Insomnia:** [Insomnia Requisições](/Insomnia_2026-04-29.yaml)

### Comandos utilizados no Insomnia: [CRUD Projeto Anime](docs/CRUD_ProjetoAnime.txt)

> **Nota sobre Paginação**: Os endpoints de listagem e filtros que retornam coleções suportam paginação via parâmetros Spring Data Pageable. No Insomnia, adicione os parâmetros na aba **Query**:
> * `page` — número da página (inicia em 0)
> * `size` — quantidade de itens por página
> * `sort` — campo e direção (ex: `id,asc`, `titulo,desc`)

### Exemplos de Paginação no Insomnia

| Endpoint | URL de Exemplo |
|----------|---------------|
| `GET /animes` | `/animes?page=0&size=5&sort=id,asc` |
| `GET /animes/by-category/ACAO` | `/animes/by-category/ACAO?page=0&size=10&sort=lancamento,desc` |
| `GET /animes/by-date/2023-01-01` | `/animes/by-date/2023-01-01?page=0&size=5` |
| `GET /personagens` | `/personagens?page=0&size=10&sort=nome,asc` |
| `GET /personagens/anime?animeId=1` | `/personagens/anime?animeId=1&page=0&size=5` |
| `GET /temporadas` | `/temporadas?page=0&size=10&sort=lancamento,desc` |
| `GET /temporadas/by-anime/1` | `/temporadas/by-anime/1?page=0&size=5` |

### Animes (`/animes`)
* `GET /animes`: Lista todos os animes cadastrados (paginado).
* `GET /animes/by-title?titulo={titulo}`: Busca animes por título (containing, case-sensitive) (List).
* `GET /animes/by-category/{categories}`: Filtra animes por uma ou mais categorias (ex: `ACAO,DRAMA`) (paginado).
* `GET /animes/by-date/{date}`: Filtra animes por data de lançamento exata (`yyyy-MM-dd`) (paginado).
* `GET /animes/by-year-range/{from}/{to}`: Filtra animes por período de lançamento (`yyyy-MM-dd/yyyy-MM-dd`) (paginado).
* `GET /animes/{id}`: Recupera os dados de um anime por ID.
* `POST /animes`: Registra um novo anime.
* `PUT /animes/{id}`: Atualiza os dados de um anime existente.
* `DELETE /animes/{id}`: Remove um anime e os seus personagens e temporadas associados (cascade).

### Personagens (`/personagens`)
* `GET /personagens`: Lista todos os personagens cadastrados (paginado).
* `GET /personagens/anime?animeId={id}`: Lista todos os personagens vinculados a um anime específico (paginado). O parâmetro `animeId` é passado como query param.
* `GET /personagens/by-name?nome={nome}`: Busca personagens por nome (contendo o valor informado) (List).
* `GET /personagens/{id}`: Recupera os dados de um personagem por ID.
* `POST /personagens`: Registra um novo personagem.
* `PUT /personagens/{id}`: Atualiza os dados de um personagem existente.
* `DELETE /personagens/{id}`: Remove um personagem do sistema.

### Temporadas (`/temporadas`)
* `GET /temporadas`: Lista todas as temporadas cadastradas (paginado).
* `GET /temporadas/{id}`: Recupera os dados de uma temporada por ID.
* `GET /temporadas/by-anime/{animeId}`: Lista todas as temporadas vinculadas a um anime específico (paginado).
* `POST /temporadas`: Registra uma nova temporada.
* `PUT /temporadas/{id}`: Atualiza os dados de uma temporada existente.
* `DELETE /temporadas/{id}`: Remove uma temporada do sistema.

---

## Regras de Validação

A API utiliza **Bean Validation (Jakarta)** com validadores customizados:

### Anime
| Campo | Regras |
|-------|--------|
| `titulo` | Obrigatório, não pode estar em branco, sem caracteres especiais, validação customizada `@Titulo` |
| `descricao` | Obrigatório, não pode estar em branco |
| `lancamento` | Obrigatório, data deve ser passada ou presente (`@PastOrPresent`) |
| `categoria` | Obrigatório, mínimo 1 categoria, valores devem corresponder ao enum `Categoria` |

### Personagem 
| Campo | Regras |
|-------|--------|
| `nome` | Obrigatório, não pode estar em branco, sem caracteres especiais, validação customizada `@NomePersonagem` |
| `personalidade` | Obrigatório, não pode estar em branco |
| `animeId` | Obrigatório, ID do anime deve existir |

### Temporada
| Campo | Regras |
|-------|--------|
| `numTemporada` | Obrigatório, deve seguir o padrão `Temporada X` (ex: `Temporada 1`) |
| `qtdEpisodio` | Obrigatório, valor positivo (`@Positive`) |
| `lancamento` | Obrigatório, data deve ser passada ou presente (`@PastOrPresent`) |
| `animeId` | Obrigatório, ID do anime deve existir |
| **Global** | Não pode existir mais de uma temporada com o mesmo `numTemporada` para o mesmo anime (`@UnicaTemporadaPorAnime`) |

---

## Configuração e Execução

A aplicação utiliza o banco de dados H2 configurado com:
* **URL**: `jdbc:h2:mem:animes`
* **Driver**: `org.h2.Driver`
* **Dialect**: `org.hibernate.dialect.H2Dialect`
* **DDL Auto**: `update`

O console H2 está habilitado via `spring-boot-h2console`.

Para executar a aplicação localmente:
```bash
mvn spring-boot:run
```

---

## Prints do projeto - CRUD Normal (POST, UPDATE e DELETE)

### Post ANIMES
![Print da requisição POST Anime](docs/images/Post%20Anime.png)

### Post PERSONAGENS
![Print da requisição POST Personagem](docs/images/Post%20Personagem.png)

### Post Temporada
![Print da requisição POST Temporada](docs/images/Post%20Temporada.png)

### Update Anime
![Print da requisição Update Anime](docs/images/Update%20Anime.png)

### Update Personagem
![Print da requisição Update Personagem](docs/images/Update%20Personagem.png)

### Update Temporada
![Print da requisição Update Temporada](docs/images/Update%20Temporada.png)

### Delete Anime
![Print da requisição Delete Anime](docs/images/Delete%20Anime.png)

### Delete Personagem
![Print da requisição Delete Personagem](docs/images/Delete%20Personagem.png)

### Delete Temporada
![Print da requisição Delete Temporada](docs/images/Delete%20Temporada.png)

---

### Inserindo os Animes, Personagens e Temporadas via H2
![Gif Inserindo Dados no H2 Console](docs/Inserindo%20Dados%20no%20H2%20Console.gif)

---

## Prints do projeto - Todos os Gets de Anime

### Get All Animes
![Print da requisição GET Anime](docs/images/Get%20All%20Animes.png)

### Get Animes By Id
![Print da requisição GET Anime](docs/images/Get%20Anime%20By%20Id.png)

### Get Animes By Titulo
![Print da requisição GET Anime](docs/images/Get%20Anime%20By%20Titulo.png)

### Get Animes By Lançamento
![Print da requisição GET Anime](docs/images/Get%20Anime%20By%20Lancamento.png)

### Get Animes By Lançamento Periodo
![Print da requisição GET Anime](docs/images/Get%20Anime%20By%20Lancamento%20Periodo.png)

### Get Animes By Categoria
![Print da requisição GET Anime](docs/images/Get%20Anime%20By%20Categoria.png)

## Prints do projeto - Todos os Gets de Personagem

### Get All Personagens
![Print da requisição GET Personagens](docs/images/Get%20All%20Personagens.png)

### Get Personagens By Id
![Print da requisição GET Personagens](docs/images/Get%20Personagens%20By%20Id.png)

### Get Personagens By AnimeId
![Print da requisição GET Personagens](docs/images/Get%20Personagens%20By%20AnimeId.png)

### Get Personagens By Name
![Print da requisição GET Personagens](docs/images/Get%20Personagens%20By%20Name.png)

## Prints do projeto - Todos os Gets de Temporada

### Get All Temporadas
![Print da requisição GET Temporadas](docs/images/Get%20All%20Temporadas.png)

### Get All Temporadas By AnimeId
![Print da requisição GET Temporadas](docs/images/Get%20All%20Temporadas%20By%20AnimeId.png)

### Get Temporada By Id
![Print da requisição GET Temporadas](docs/images/Get%20Temporada%20By%20Id.png)