package br.com.fiap.animes.Personagem.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Personagem.Personagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonagemRequest (
        @NotBlank
        String nome,

        @NotBlank
        String personalidade,

        @NotNull
        Long animeId
) {
    public Personagem toEntity(Anime anime) {
        return Personagem.builder()
                .nome(nome)
                .personalidade(personalidade)
                .anime(anime)
                .build();
    }
}
