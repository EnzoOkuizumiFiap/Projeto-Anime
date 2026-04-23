package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;

import java.time.LocalDate;
import java.util.List;

public record AnimeResponse(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Categoria> categoria
) {
    public static AnimeResponse fromEntity(Anime anime) {
        return new AnimeResponse(
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getLancamento(),
                anime.getCategoria()
        );
    }
}
