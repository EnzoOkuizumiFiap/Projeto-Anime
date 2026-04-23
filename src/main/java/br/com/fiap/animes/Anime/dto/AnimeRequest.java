package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Temporada.Temporada;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record AnimeRequest(
        @NotBlank
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        @PastOrPresent
        LocalDate lancamento,

        @NotNull
        @Size(min = 1)
        List<Temporada> temporada,

        @NotNull
        @Size(min = 1)
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
