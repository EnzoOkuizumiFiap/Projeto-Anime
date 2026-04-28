package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.validation.CategoriaValidation;
import br.com.fiap.animes.validation.SemCaractereEspecial;
import br.com.fiap.animes.validation.Titulo;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record AnimeRequest(
        @NotBlank
        @Titulo
        @SemCaractereEspecial
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        @PastOrPresent
        LocalDate lancamento,

        @NotNull
        @Size(min = 1)
        @CategoriaValidation(enumClass = Categoria.class)
        List<String> categoria
) {
    public Anime toEntity() {
        return Anime.builder()
                .titulo(titulo)
                .descricao(descricao)
                .lancamento(lancamento)
                .categoria(categoria.stream().map(Categoria::valueOf).toList())
                .build();
    }
}
