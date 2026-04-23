package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;

import java.time.LocalDate;
import java.util.List;

public record AnimeRequest(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Categoria> categoria
) {
    public Anime toEntity() {
        return Anime.builder()
                .titulo(titulo)
                .descricao(descricao)
                .lancamento(lancamento)
                .categoria(categoria)
                .build();
    }
}
