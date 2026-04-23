package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Temporada.Temporada;

import java.time.LocalDate;
import java.util.List;

public record AnimeResponse(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Temporada> temporada,
    List<Categoria> categoria
) {
    public static AnimeResponse fromEntity(Anime anime) {
        return new AnimeResponse(
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getLancamento(),
                anime.getTemporada(),
                anime.getCategoria()
        );
    }
}
