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
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

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

        List<Personagem> personagens,

        List<Temporada> temporadas
) {
    public Anime toEntity() {
        return Anime.builder()
                .titulo(titulo)
                .descricao(descricao)
                .lancamento(lancamento)
                .categoria(categoria.stream().map(c -> Categoria.valueOf(c.toUpperCase())).toList())
                .personagens(isNull(personagens)? new ArrayList<>() : personagens)
                .temporadas(isNull(temporadas)? new ArrayList<>() : temporadas)
                .build();
    }
}
