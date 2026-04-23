package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Temporada.Temporada;

import java.time.LocalDate;
import java.util.List;

public record AnimeRequest(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Temporada> temporada,
    List<Categoria> categoria
) {
    public Anime toEntity() {
        return Anime.builder()
                .titulo(titulo)
                .descricao(descricao)
                .lancamento(lancamento)
                .temporada(temporada)
                .categoria(categoria)
                .build();
    }
}
