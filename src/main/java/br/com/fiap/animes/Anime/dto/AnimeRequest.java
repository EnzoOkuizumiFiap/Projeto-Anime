package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Temporada.Temporada;
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
        List<String> categoria,

        @NotNull
        @Size(min = 1)
        List<Personagem> personagens,

        @NotNull
        @Size(min = 1)
        List<Temporada> temporadas
) {
    public Anime toEntity() {
        return Anime.builder()
                .titulo(titulo)
                .descricao(descricao)
                .lancamento(lancamento)
                .categoria(categoria.stream().map(c -> Categoria.valueOf(c.toUpperCase())).toList())
                .personagens(personagens)
                .temporadas(temporadas)
                .build();
    }
}
