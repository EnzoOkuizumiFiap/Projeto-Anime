package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Temporada.Temporada;

import java.time.LocalDate;
import java.util.List;

public record AnimeResponse(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Categoria> categoria,
    List<Personagem> personagens,
    List<Temporada> temporadas
) {
    public static AnimeResponse fromEntity(Anime anime) {
        return new AnimeResponse(
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getLancamento(),
                anime.getCategoria(),
                anime.getPersonagens(),
                anime.getTemporadas()
        );
    }
}
